package com.example.quantsapp.ui.webview

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quantsapp.R
import com.example.quantsapp.databinding.FragmentWebViewBinding
import com.example.quantsapp.util.extensions.openLink

class WebViewFragment : Fragment() {

    companion object {
        private val TAG = WebViewFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = WebViewFragment()
    }

    // Global params
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_web_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val url =
            "https://qapptemporary.s3.ap-south-1.amazonaws.com/ritesh/zip_files/44418/Annexure123456&7_FO.xls"
        binding.btnDownload.setOnClickListener { downloadWithChrome(url) }

    }

    private fun downloadWithChrome(url: String) {
        val uri = Uri.parse(url)
        openLink(uri)
    }

}