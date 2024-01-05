package com.task.domain.repository

import com.task.data.dto.ChannelsListDTO

interface ChannelRepository {

    suspend fun getAllChannels(): ChannelsListDTO
}