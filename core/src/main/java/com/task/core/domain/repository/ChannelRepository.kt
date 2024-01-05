package com.task.core.domain.repository

import com.task.core.data.dto.ChannelsListDTO

interface ChannelRepository {
    suspend fun getAllChannels(): ChannelsListDTO
}