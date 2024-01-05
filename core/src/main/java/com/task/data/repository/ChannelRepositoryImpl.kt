package com.task.data.repository

import com.task.data.data_source.NetworkApi
import com.task.data.dto.ChannelsListDTO
import com.task.domain.repository.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
) : ChannelRepository {
    override suspend fun getAllChannels(): ChannelsListDTO {
        return networkApi.getChannelsList()
    }
}