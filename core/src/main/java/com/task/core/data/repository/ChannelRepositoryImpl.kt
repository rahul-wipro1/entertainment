package com.task.core.data.repository

import com.task.core.data.data_source.NetworkApi
import com.task.core.data.dto.ChannelsListDTO
import com.task.core.domain.repository.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
) : ChannelRepository {
    override suspend fun getAllChannels(): ChannelsListDTO {
        return networkApi.getChannelsList()
    }
}