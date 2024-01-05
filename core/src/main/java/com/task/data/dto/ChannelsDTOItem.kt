package com.task.data.dto

import com.task.domain.model.ChannelList

data class ChannelsDTOItem(
    val date: String? = "",
    val discription: String? = "",
    val icon: String? = "",
    val pic: String? = "",
    val title: String? = "",
){
    fun toChanelList(): ChannelList {
        return ChannelList(
            date = date,
            discription = discription,
            icon = icon,
            pic = pic,
            title = title
        )
    }
}