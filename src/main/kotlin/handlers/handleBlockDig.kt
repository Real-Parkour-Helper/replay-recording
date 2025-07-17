package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleBlockDig(event: PacketEvent, recorder: ReplayRecorder) {
    val p = event.packet

    val status = p.bytes.read(0)

    val x = p.integers.read(0)
    val y = p.integers.read(1)
    val z = p.integers.read(2)

    val face = p.bytes.read(1)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.BLOCK_DIG,
            data = mapOf(
                "status" to status,
                "x" to x,
                "y" to y,
                "z" to z,
                "face" to face
            )
        )
    )
}
