package com.task.presentation.view

import android.os.Bundle
import android.view.View
import com.task.R
import com.task.databinding.FragmentChannelListBinding
import com.task.core.uicomponents.BaseFragment

/** This Fragment is used for
 * showing the list of channels Item
 */
class ChannelListFragment : BaseFragment<FragmentChannelListBinding>() {
    override fun getFragmentView() = R.layout.fragment_channel_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}