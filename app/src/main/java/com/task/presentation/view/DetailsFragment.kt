package com.task.presentation.view

import android.os.Bundle
import android.view.View
import com.task.R
import com.task.databinding.FragmentDetailsBinding
import com.task.core.uicomponents.BaseFragment

/** This Fragment is used for
 * showing the details of selected item
 */
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override fun getFragmentView() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}