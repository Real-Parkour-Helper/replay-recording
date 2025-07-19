package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleHeldItemSlot(event: PacketEvent, recorder: ReplayRecorder) {
    val slot = event.packet.integers.read(0)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.HELD_ITEM_SLOT,
            data = mapOf(
                "slot" to slot,
            )
        )
    )
}
