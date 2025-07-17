package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleFlying(event: PacketEvent, recorder: ReplayRecorder) {
    val onGround = event.packet.booleans.read(0)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.FLYING,
            data = mapOf("onGround" to onGround)
        )
    )
}
