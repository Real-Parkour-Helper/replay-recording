package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleLook(event: PacketEvent, recorder: ReplayRecorder) {
    val p = event.packet

    val yaw = p.float.read(0)
    val pitch = p.float.read(1)


    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.LOOK,
            data = mapOf(
                "yaw" to yaw,
                "pitch" to pitch,
            )
        )
    )
}
