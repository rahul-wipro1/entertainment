package com.task.core.domain.repository

import com.task.core.data.dto.ChannelsDTOItem

interface ChannelRepository {
    suspend fun getAllChannels(): List<ChannelsDTOItem>
}