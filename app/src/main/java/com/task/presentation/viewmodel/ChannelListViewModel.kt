package com.task.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.domain.use_cases.ChannelListUseCase
import com.task.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** This ChannelList View model is used to fetch
 * the data from the ChannelListUseCase
 */
@HiltViewModel
class ChannelListViewModel @Inject constructor(
    private val channelListUseCase: ChannelListUseCase,
) : ViewModel() {

    private val _channelListValue = MutableStateFlow(ChannelListState())
    var channelListState: StateFlow<ChannelListState> = _channelListValue

    fun getAllChannelList() = viewModelScope.launch(Dispatchers.IO) {
        channelListUseCase().collect {
            when (it) {
                is ResponseState.Loading -> {
                    _channelListValue.value = ChannelListState(isLoading = true)
                }

                is ResponseState.Success -> {
                    _channelListValue.value = ChannelListState(channelList = it.data ?: emptyList())
                }

                is ResponseState.Error -> {
                    _channelListValue.value =
                        ChannelListState(error = it.message ?: "Unexpected Error")
                }
            }
        }
    }
}