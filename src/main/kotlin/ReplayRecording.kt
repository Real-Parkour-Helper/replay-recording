package org.realparkourhelper

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

import org.realparkourhelper.handlers.handleChat
import org.realparkourhelper.ReplayRecorder

class ReplayRecording: JavaPlugin() {

    private lateinit var protocolManager: ProtocolManager
    private lateinit var recorder: ReplayRecorder

    private val packetsToSave = listOf(
        PacketType.Play.Client.CHAT,
        PacketType.Play.Client.FLYING,
        PacketType.Play.Client.POSITION,
        PacketType.Play.Client.LOOK,
        PacketType.Play.Client.POSITION_LOOK,
        PacketType.Play.Client.BLOCK_DIG,
        PacketType.Play.Client.HELD_ITEM_SLOT,
        PacketType.Play.Client.ARM_ANIMATION,
        PacketType.Play.Client.ENTITY_ACTION,
        PacketType.Play.Client.BLOCK_PLACE
    )


    override fun onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager()

        // Register the packet listener
        protocolManager.addPacketListener(object : PacketAdapter(this, packetsToSave) {
            override fun onPacketReceiving(event: PacketEvent) {
                val type = event.packet.type

                when (event.packet.type) {
                    PacketType.Play.Client.CHAT -> handleChat(event, recorder)
                    PacketType.Play.Client.FLYING -> handleFlying(event, recorder)
                    PacketType.Play.Client.POSITION -> handlePosition(event, recorder)
                    PacketType.Play.Client.LOOK -> handleLook(event, recorder)
                    PacketType.Play.Client.POSITION_LOOK -> handlePositionLook(event, recorder)
                    PacketType.Play.Client.BLOCK_DIG -> handleBlockDig(event, recorder)
                    PacketType.Play.Client.HELD_ITEM_SLOT -> handleHeldItemChange(event, recorder)
                    PacketType.Play.Client.ARM_ANIMATION -> handleArmAnimation(event, recorder)
                    PacketType.Play.Client.ENTITY_ACTION -> handleEntityAction(event, recorder)
                    PacketType.Play.Client.BLOCK_PLACE -> handleBlockPlace(event, recorder)
                    else -> {}
                }
            }
        })

        logger.info("ReplayRecorder enabled with packet listener.")
    }

    private fun savePacket(player: String, packetType: String, data: String) {
        // Replace this with actual saving logic (file, db, etc.)
        logger.info("Saving packet from $player: $packetType -> $data")
    }

    override fun onDisable() {
        protocolManager.removePacketListeners(this)
    }
}