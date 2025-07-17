package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handlePositionLook(event: PacketEvent, recorder: ReplayRecorder) {
    val p = event.packet

    val x = p.doubles.read(0)
    val feetY = p.doubles.read(1)
    val z = p.doubles.read(2)

    val yaw = p.float.read(0)
    val pitch = p.float.read(1)

    val onGround = p.booleans.read(0)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.POSITION,
            data = mapOf(
                "x" to x,
                "feetY" to feetY,
                "z" to z,
                "yaw" to yaw,
                "pitch" to pitch,
                "onGround" to onGround
            )
        )
    )
}
