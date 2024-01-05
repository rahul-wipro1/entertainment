package com.task.core.data.data_source

import com.task.core.data.dto.ChannelsListDTO
import retrofit2.http.GET

interface NetworkApi {

    @GET("/channels.json")
    suspend fun getChannelsList(): ChannelsListDTO
}