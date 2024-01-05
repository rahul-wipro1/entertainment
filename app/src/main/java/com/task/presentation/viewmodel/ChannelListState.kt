package com.task.presentation.viewmodel

import com.task.domain.model.ChannelList

/** This data class is used to handle the isLoading
 * and channelList and error message in viewModel
 */
data class ChannelListState(
    val isLoading: Boolean? = false,
    val channelList: List<ChannelList>? = emptyList(),
    val error: String? = "",
)
