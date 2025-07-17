package org.realparkourhelper

import java.util.UUID

class ReplayRecordingApi(private val recorder: ReplayRecorder) {
    private val fileVersion: Int = 1
    fun start(gameId: String, mapName: String, playerNames: Map<UUID, String>) {
        if (recorder.getStatus()) throw IllegalStateException("Already started. Disable first to start again")
        recorder.updateHeader(KryoHeader(
                version = fileVersion,
                gameId = gameId,
                createdAt = System.currentTimeMillis(),
                mapName = mapName,
                playerNames = playerNames
            )
        )
        recorder.clear()
        recorder.enable()
    }

    fun stop() {
        recorder.disable()
    }

    fun getStatus(): Boolean {
        return recorder.getStatus()
    }

    fun save(filePath: String) {
        if (recorder.getStatus()) throw IllegalStateException("ReplayRecording still running. Call stop() before saving")
        recorder.saveToFile(filePath)
        recorder.clear()
    }
}