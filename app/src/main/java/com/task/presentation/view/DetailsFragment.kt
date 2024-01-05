package com.task.presentation.view

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.task.R
import com.task.databinding.FragmentDetailsBinding
import com.task.core.uicomponents.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/** This Fragment is used for
 * showing the details of selected item
 */
@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun getFragmentView() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            loadDetails(it)
        }
    }

    /** This is to load the data which
     * we got from the recycler view
     */
    private fun loadDetails(it: Bundle) {
        val title = it.getString("title")
        val image = it.getString("image")
        val desc = it.getString("desc")
        binding.textTitle.text = title
        binding.textDescription.text = desc
        Picasso.get().load(image)
            .placeholder(R.drawable.icon_wait24)
            .error(R.drawable.baseline_error_outline_24)
            .into(binding.channelImage)
    }
}