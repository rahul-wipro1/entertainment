package com.task.presentation.viewmodel

import com.task.DispatcherRule
import com.task.core.domain.model.ChannelList
import com.task.core.domain.use_cases.ChannelListUseCase
import com.task.core.util.ResponseState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChannelListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val dispatcherRule = DispatcherRule()

    private lateinit var channelListUseCase: ChannelListUseCase
    private lateinit var channelListViewModel: ChannelListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        channelListUseCase = mockk(relaxed = true)
        channelListViewModel = ChannelListViewModel(channelListUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test loading state`() = runTest {
        coEvery { channelListUseCase.invoke() } returns flow {
            emit(ResponseState.Loading<List<ChannelList>>())
        }

        channelListViewModel.getAllChannelList()

        channelListViewModel.channelListState.collect {
            assertTrue(it.isLoading!!)
            assertNull(it.error)
            assertTrue(it.channelList.isNullOrEmpty())
        }
    }

    @Test
    fun `test fetchData success`() = runTest {
        val listData = listOf(
            ChannelList("11-12-2023", "Discription", "icon", "pic", "title"),
            "12-12-2023", "Discription2", "icon2", "pic2", "title2"
        )
        val successData = ResponseState.Success(listData)

        coEvery { channelListUseCase.invoke() } returns flow {
            emit(successData as ResponseState<List<ChannelList>>)
        }
        channelListViewModel.getAllChannelList()
        channelListViewModel.channelListState.collect {
            assertFalse(it.isLoading!!)
            assertEquals(successData, it)
            assertNull(it.error)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test for negative scenario `() = runTest {
        val errorMessage = "Error Occurred"
        //Mock the behaviour
        coEvery { channelListUseCase.invoke() } returns flow {
            emit(ResponseState.Error<List<ChannelList>>(errorMessage))
        }

        //Trigger the getAllChannelList to be tested
        channelListViewModel.getAllChannelList()

        //verify
        channelListViewModel.channelListState.collect {
            assertFalse(it.isLoading!!)
            assertEquals(errorMessage, it.error)
            assertTrue(it.channelList!!.isEmpty())
        }
    }
}

