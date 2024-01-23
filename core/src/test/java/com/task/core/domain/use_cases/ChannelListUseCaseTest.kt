package com.task.core.domain.use_cases

import com.task.core.MainDispatcherRule
import com.task.core.data.dto.ChannelsDTOItem
import com.task.core.domain.model.ChannelList
import com.task.core.domain.repository.ChannelRepository
import com.task.core.util.ResponseState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ChannelListUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var channelRepository: ChannelRepository
    private lateinit var channelListUseCase: ChannelListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        channelRepository = mockk()
        channelListUseCase = ChannelListUseCase(channelRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `invoke loading by success`() = runTest{
        coEvery { channelRepository.getAllChannels()} returns listOf(ChannelsDTOItem("11-11-2023","disc","icon","pic","title"))

        val resultFlow = channelListUseCase()

        val result = mutableListOf<ResponseState<List<ChannelList>>>()
        resultFlow.collect{
            result.add(it)
        }
        assertTrue(result[0] is ResponseState.Loading)
        assertTrue(result[1] is ResponseState.Success)
        val successState =  result[1] as ResponseState.Success
        assertEquals(1,successState.data?.size)
    }

    @Test
    fun `invoke loading by error`() = runTest{
        coEvery { channelRepository.getAllChannels()} throws IOException()
        val resultFlow = channelListUseCase()

        val result = mutableListOf<ResponseState<List<ChannelList>>>()
        resultFlow.collect{
            result.add(it)
        }
        assertTrue(result[0] is ResponseState.Loading)
        assertTrue(result[1] is ResponseState.Error)
        val errorState =  result[1] as ResponseState.Error
        assertEquals("Error Occurred",errorState.message)
    }
}