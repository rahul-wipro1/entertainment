package com.task.core.data.repository

import com.task.core.MainDispatcherRule
import com.task.core.data.data_source.NetworkApi
import com.task.core.data.dto.ChannelsDTOItem
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

class ChannelRepositoryImplTest {

    private lateinit var networkApi: NetworkApi
    private lateinit var channelRepository: ChannelRepositoryImpl

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        networkApi = mockk()
        channelRepository = ChannelRepositoryImpl(networkApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getAllChannels`() = runTest {
        val mockList = listOf(ChannelsDTOItem("11-11-2023", "disc", "icon", "pic", "title"))

        coEvery { networkApi.getChannelsList() } returns mockList

        val result = channelRepository.getAllChannels()

        assertEquals(mockList, result)
    }

    @Test
    fun `getAllChannels with Empty response`() = runTest {

        coEvery { networkApi.getChannelsList() } returns emptyList()

        val result = channelRepository.getAllChannels()

        assertTrue(result.isEmpty())
    }

}