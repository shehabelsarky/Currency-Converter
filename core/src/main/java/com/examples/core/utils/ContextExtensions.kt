package com.examples.core.utils

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.examples.core.R

/**
 * Created by Nader Nabil on 5/11/2020.
 */


fun Context.getStringByIdName(idName: String?): String? {
    val res: Resources = resources
    return res.getString(res.getIdentifier(idName, "string", packageName))
}

fun Context.startBrowserIntent(url: String) {
    if (url.isEmpty()) {
        return
    }
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this.startActivity(i)
}

/**
 * Extension function to get int value of color from color resource.
 *
 * @receiver Context
 * @param resourceId Int the color res.
 * @return Int value of the color.
 */
fun Context.getColorCompat(@ColorRes resourceId: Int) = ContextCompat.getColor(this, resourceId)

/**
 * Extension function to get drawable from drawable resource.
 *
 * @receiver Context
 * @param resourceId Int the drawable res
 * @return Drawable? the instance of drawable
 */
fun Context.getDrawableCompat(@DrawableRes resourceId: Int) =
    ContextCompat.getDrawable(this, resourceId)

fun Context.makeCall(activity: Activity, phoneNumber: String, callPhonePermissionRequestCode: Int) {
    val callIntent = Intent(Intent.ACTION_CALL)
    callIntent.data = Uri.parse("tel:$phoneNumber")
    if (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        )
        != PackageManager.PERMISSION_GRANTED
    ) {

        activity.requestPermissions(
            arrayOf(Manifest.permission.CALL_PHONE),
            callPhonePermissionRequestCode
        )

    } else {
        startActivity(callIntent)
    }
}

fun Context.sendMail(emailAddress: String) {
    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data = Uri.parse("mailto:")
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
    startActivity(mIntent)
}

fun Context.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.setPrimaryClip(clip)
    showToast(getString(R.string.link_copied))
}
