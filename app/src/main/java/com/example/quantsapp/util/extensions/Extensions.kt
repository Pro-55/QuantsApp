package com.example.quantsapp.util.extensions

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quantsapp.ui.MainViewModel
import com.example.quantsapp.util.Constants
import com.example.quantsapp.util.CustomTabHelper
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun FragmentActivity.getMainViewModel(): MainViewModel = ViewModelProvider(this)
    .get(MainViewModel::class.java)

fun Fragment.openLink(uri: Uri) {
    try {
        val chromeTabs = CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()
            .setShowTitle(true)
            .build()

        val chromePackageName = CustomTabHelper.getPackageNameToUse(requireContext(), uri)
        if (chromePackageName == null) {
            // Chrome not installed
            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            if (browserIntent.resolveActivity(requireContext().packageManager) != null)
                startActivity(browserIntent)
        } else {
            chromeTabs.intent.setPackage(chromePackageName)
            chromeTabs.launchUrl(requireContext(), uri)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        showShortSnackBar(Constants.REQUEST_FAILED_MESSAGE)
    }
}

fun Fragment.showShortSnackBar(message: String?) = requireActivity().showShortSnackBar(message)

fun FragmentActivity.showShortSnackBar(message: String?) {
    Snackbar.make(
        findViewById(android.R.id.content),
        message ?: "Something went wrong!",
        Snackbar.LENGTH_SHORT
    ).show()
}

fun String.formatTieStamp(): String {

    return try {
        val ts = this.toLong()
        val dateFormat = "dd-MM-yyyy hh:mm aaa"
        // Parse Date
        val sdf = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        sdf.format(ts)
    } catch (e: Exception) {
        ""
    }

}
