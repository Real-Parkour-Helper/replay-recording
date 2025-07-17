package org.realparkourhelper.handlers

import com.comphenix.protocol.events.PacketEvent
import org.realparkourhelper.PacketTypeEnum
import org.realparkourhelper.ReplayRecorder
import org.realparkourhelper.ReplayEvent

fun handleEntityAction(event: PacketEvent, recorder: ReplayRecorder) {
    val p = event.packet

    val entityID = p.integers.read(0)
    val actionID = p.integers.read(1)
    val actionParameter = p.integers.read(2)

    recorder.record(
        ReplayEvent(
            timestamp = System.currentTimeMillis(),
            playerId = event.player.uniqueId,
            type = PacketTypeEnum.ENTITY_ACTION,
            data = mapOf(
                "entityID" to entityID,
                "actionID" to actionID,
                "actionParameter" to actionParameter
            )
        )
    )
}
