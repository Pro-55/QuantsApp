package com.example.quantsapp.ui.feed

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quantsapp.R
import com.example.quantsapp.databinding.FragmentFeedBinding
import com.example.quantsapp.models.Data
import com.example.quantsapp.models.FeedItem
import com.example.quantsapp.models.Resource
import com.example.quantsapp.models.Status
import com.example.quantsapp.util.Constants
import com.example.quantsapp.util.extensions.getMainViewModel
import com.example.quantsapp.util.extensions.glide
import com.example.quantsapp.util.extensions.openLink
import com.example.quantsapp.util.extensions.showShortSnackBar

class FeedFragment : Fragment() {

    companion object {
        private val TAG = FeedFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = FeedFragment()
    }

    // Global params
    private lateinit var binding: FragmentFeedBinding
    private val viewModel by lazy { requireActivity().getMainViewModel() }
    private var adapter: FeedAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.fetchFeed().observe(viewLifecycleOwner, Observer { bindData(it) })
    }

    private fun setupRecyclerView() {
        adapter = FeedAdapter(glide())
        adapter?.listener = object : FeedAdapter.Listener {

            override fun onClick(feedItem: FeedItem) {
                val url = feedItem.url?.trim()
                if (!url.isNullOrEmpty()) {
                    val uri = Uri.parse(url)
                    openLink(uri)
                } else {
                    showShortSnackBar(Constants.REQUEST_FAILED_MESSAGE)
                }
            }

        }

        binding.recyclerFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFeed.adapter = adapter

    }

    private fun bindData(resource: Resource<Data>) {
        when (resource.status) {
            Status.LOADING -> Log.d(TAG, "TestLog: ${resource.status}")
            Status.ERROR -> showShortSnackBar(resource.message)
            Status.SUCCESS -> {

                val feed = resource.data?.feed ?: listOf()

                adapter?.swapData(feed)

            }
        }
    }

}