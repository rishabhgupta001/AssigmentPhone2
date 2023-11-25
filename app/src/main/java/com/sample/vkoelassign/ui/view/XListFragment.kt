package com.sample.vkoelassign.ui.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.vkoelassign.R
import com.sample.vkoelassign.databinding.FragmentXListBinding
import com.sample.vkoelassign.network.StatusCode
import com.sample.vkoelassign.ui.model.Venues
import com.sample.vkoelassign.ui.view.adapter.MovieAdapter
import com.sample.vkoelassign.ui.viewmodel.XViewModel
import com.sample.vkoelassign.utility.toastShort

class XListFragment : Fragment() {
    private lateinit var binding: FragmentXListBinding
    private lateinit var viewModel: XViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var list: ArrayList<Venues> = ArrayList()
    private var dummyList: ArrayList<Venues> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(XViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentXListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun init() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getVenueList()
        observeResponse()
        initializeAdapter()

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString().toLowerCase())
            }
        })
    }

    private fun initializeAdapter() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            movieAdapter = MovieAdapter(
                list, dummyList
            )
            adapter = movieAdapter
        }
    }


    private fun setUpRecyclerViewData(data: ArrayList<Venues>?) {
        if (!data.isNullOrEmpty()) {
            list.clear()
            list.addAll(data)

            dummyList.clear()
            dummyList.addAll(data)
            movieAdapter.notifyDataSetChanged()
        }

    }


    fun filter(text: String) {
        val temp = ArrayList<Venues>()
        for (d in list) {
            if (d.name?.toLowerCase()?.contains(text) == true) {
                temp.add(d)
            }
        }
        movieAdapter.updateList(temp)
    }


    private fun observeResponse() {
        viewModel.venueListresponseData.removeObservers(this)
        viewModel.venueListresponseData.observe(viewLifecycleOwner, Observer { success ->
            when (success?.statusCode) {
                StatusCode.NETWORK_ERROR -> {
                    context?.toastShort(getString(R.string.text_make_sure_no_data_connection))
                }

                StatusCode.START -> {
                    binding.progressBar.visibility = View.VISIBLE
                    setUpRecyclerViewData(success.venues)
                }

                StatusCode.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                    context?.toastShort(success.msg)
                }

                StatusCode.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    context?.toastShort(success.msg)
                }
            }
            if (viewModel.venueListresponseData.value != null)
                viewModel.venueListresponseData.value = null
        })
    }

}