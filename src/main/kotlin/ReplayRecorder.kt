package org.realparkourhelper

import java.util.UUID
import java.io.File
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

class ReplayRecorder(private val header: KryoHeader) {
    private val events = mutableListOf<ReplayEvent>()

    fun record(event: ReplayEvent) {
        events.add(event)
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

    fun getEvents(): List<ReplayEvent> = events.toList()
}
