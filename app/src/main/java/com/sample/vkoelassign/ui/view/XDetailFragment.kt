package com.sample.vkoelassign.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.vkoelassign.databinding.FragmentXDetailBinding
import com.sample.vkoelassign.ui.model.Venues
import com.sample.vkoelassign.utility.Utils

class XDetailFragment : Fragment() {
    private lateinit var binding: FragmentXDetailBinding
    private var itemData: Venues? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentXDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        arguments?.let {
            itemData = XDetailFragmentArgs.fromBundle(it).venues

            binding.titleTxtView
            Utils.showFadeInAnimOnText(
                requireContext(),
                binding.titleTxtView,
                itemData?.name!!
            )
        }


    }
}