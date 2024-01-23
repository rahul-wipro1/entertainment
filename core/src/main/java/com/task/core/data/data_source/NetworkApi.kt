package com.task.core.data.data_source

import com.task.core.data.dto.ChannelsDTOItem
import retrofit2.http.GET

interface NetworkApi {

    @GET("/channels.json")
    suspend fun getChannelsList(): List<ChannelsDTOItem>
}