package com.examples.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.examples.core.R

fun getDrawableBackground(context: Context, @DrawableRes drawableId: Int): Drawable {
    return ContextCompat.getDrawable(context, drawableId)!!
}

fun View.setMargins(
    leftMarginDp: Int? = null,
    topMarginDp: Int? = null,
    rightMarginDp: Int? = null,
    bottomMarginDp: Int? = null
) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        leftMarginDp?.run { params.leftMargin = this.dpToPx(context) }
        topMarginDp?.run { params.topMargin = this.dpToPx(context) }
        rightMarginDp?.run { params.rightMargin = this.dpToPx(context) }
        bottomMarginDp?.run { params.bottomMargin = this.dpToPx(context) }
        requestLayout()
    }
}

fun Int.dpToPx(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}

fun ImageView.loadImg(imgUrl: String, placeHolder: Int? = null) {
    Glide.with(context)
        .load(imgUrl)
        .placeholder(placeHolder ?: R.drawable.placeholder)
        .error(placeHolder ?: R.drawable.placeholder)
        .fallback(placeHolder ?: R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadSVGImg(imgUrl: String, @DrawableRes placeHolder: Int? = null) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .placeholder(placeHolder ?: R.drawable.placeholder)
        .error(placeHolder ?: R.drawable.placeholder)
        .data(imgUrl)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}


fun TextView.addImageAtEnd(
    atText: String,
    @DrawableRes imgSrc: Int,
    imgWidth: Int,
    imgHeight: Int
) {
    val ssb = SpannableStringBuilder(atText.plus("  "))

    val drawable = ContextCompat.getDrawable(this.context, imgSrc) ?: return
    drawable.mutate()
    drawable.setBounds(
        0, 0,
        imgWidth,
        imgHeight
    )
    ssb.setSpan(
        VerticalImageSpan(drawable),
        ssb.length - 1,
        ssb.length,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
    )
    this.setText(ssb, TextView.BufferType.SPANNABLE)
}

fun TextView.setClickableHtmlText(htmlText: String) {
    movementMethod = LinkMovementMethod.getInstance()
    text =
        HtmlCompat.fromHtml(
            htmlText,
            HtmlCompat.FROM_HTML_MODE_LEGACY,
            GlideImageGetter(this),
            null
        )
}

fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}