package org.realparkourhelper

import java.util.UUID
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import java.io.FileOutputStream


data class ReplayEvent(
    val timestamp: Long,
    val playerId: UUID,
    val type: PacketTypeEnum,
    val data: Map<String, Any>
)

data class KryoHeader(
    val version: Int,
    val gameId: String,
    val createdAt: Long,
    val mapName: String,
    val playerNames: Map<UUID, String>,
)

class ReplayRecorder {
    private var events = mutableListOf<ReplayEvent>()
    private var header = KryoHeader(
        version = 0,
        gameId = "default",
        createdAt = 0,
        mapName = "default",
        playerNames = emptyMap()
    )
    private var enabled: Boolean = false

    fun record(event: ReplayEvent) {
        if (enabled) events.add(event)
    }

    fun updateHeader(newHeader: KryoHeader) {
        header = newHeader
    }

    fun saveToFile(filePath: String) {
        val kryo = Kryo()
        val output = Output(FileOutputStream(filePath))

        kryo.register(KryoHeader::class.java)
        kryo.register(ReplayEvent::class.java)
        kryo.register(UUID::class.java)
        kryo.register(PacketTypeEnum::class.java)
        kryo.register(HashMap::class.java)
        kryo.register(ArrayList::class.java)
        kryo.register(String::class.java)
        kryo.register(Int::class.java)
        kryo.register(Long::class.java)
        kryo.register(Double::class.java)

        kryo.writeObject(output, header)
        kryo.writeObject(output, events)
        output.close()
    }

    fun clear() {
        events.clear()
    }

    fun enable() {
        enabled = true
    }

    fun disable() {
        enabled = false
    }

    fun getStatus(): Boolean {
        return enabled
    }
}
