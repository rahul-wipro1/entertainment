package com.task.core.domain.use_cases

import com.task.core.domain.model.ChannelList
import com.task.core.domain.repository.ChannelRepository
import com.task.core.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChannelListUseCase @Inject constructor(
    private val channelRepository: ChannelRepository,
) {
    operator fun invoke(): Flow<ResponseState<List<ChannelList>>> = flow {
        try {
            emit(ResponseState.Loading<List<ChannelList>>())
            val channel = channelRepository.getAllChannels().map {
                it.toChanelList()
            }
            emit(ResponseState.Success<List<ChannelList>>(channel))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<ChannelList>>(e.localizedMessage ?: "Error Occurred"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<ChannelList>>("Error Occurred"))
        }
    }
}