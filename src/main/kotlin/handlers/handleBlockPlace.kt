package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleBlockPlace(event: PacketEvent, recorder: ReplayRecorder) {
    val p = event.packet

    val x = p.integers.read(0)
    val y = p.integers.read(1)
    val z = p.integers.read(2)

    val face = p.integers.read(3)

    val item = p.itemModifier.read(0)

    val cursorX = p.float.read(0)
    val cursorY = p.float.read(1)
    val cursorZ = p.float.read(2)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.BLOCK_PLACE,
            data = mapOf(
                "x" to x,
                "y" to y,
                "z" to z,

                "face" to face,

                "item" to item,

                "cursorX" to cursorX,
                "cursorY" to cursorY,
                "cursorZ" to cursorZ
            )
        )
    )
}
