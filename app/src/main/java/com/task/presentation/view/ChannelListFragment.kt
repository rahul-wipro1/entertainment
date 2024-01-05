package com.task.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.R
import com.task.core.domain.model.ChannelList
import com.task.core.uicomponents.BaseFragment
import com.task.databinding.FragmentChannelListBinding
import com.task.presentation.adapter.ChannelListAdapter
import com.task.presentation.viewmodel.ChannelListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/** This Fragment is used for
 * showing the list of channels Item*/
@AndroidEntryPoint
class ChannelListFragment : BaseFragment<FragmentChannelListBinding>() {
    private val viewModel: ChannelListViewModel by viewModels()
    private lateinit var recyclerAdapter: ChannelListAdapter
    override fun getFragmentView() = R.layout.fragment_channel_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeData()
        callApi()
    }

    /** This is to set up the recyclerview */
    private fun setUpRecyclerView() {
        recyclerAdapter = ChannelListAdapter(requireContext(), arrayListOf())
        binding.channelListRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
    }

    /** This function is used to observe the data
     * from view model flows*/
    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.channelListState.collectLatest {
                when {
                    it.isLoading == true -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    it.error?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    it.channelList?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                        recyclerAdapter.updateChannelList(it.channelList as ArrayList<ChannelList>)
                    }
                }
            }
        }
    }

    /** This function is used to call the api
     * and get the all channel list*/
    private fun callApi() {
        viewModel.getAllChannelList()
    }
}