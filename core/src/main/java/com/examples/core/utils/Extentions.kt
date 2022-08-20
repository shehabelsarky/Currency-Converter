package com.examples.core.utils

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun Bundle.putEnum(key: String, enum: Enum<*>) {
    this.putString(key, enum.name)
}

inline fun <reified T : Enum<*>> Bundle.getEnum(key: String): T {
    return enumValueOf(getString(key) ?: "")
}

fun View.showPopup(menuRes: Int, onItemClickAction: (Int) -> Boolean) {
    val popup = PopupMenu(this.context, this)
    popup.setOnMenuItemClickListener {
        onItemClickAction(it.itemId)
    }
    val inflater = popup.menuInflater
    inflater.inflate(menuRes, popup.menu)
    popup.show()
}

fun Fragment.showDialog(dialog: DialogFragment) {
    dialog.show(childFragmentManager, dialog::class.java.name)
}

fun Fragment.getIntent(): Intent? {
    return requireActivity().intent
}

inline fun <reified T> Any.mapTo(defaultValue: T): T =
    try {
        GsonBuilder().create().run {
            fromJson(toJson(this@mapTo), T::class.java)
        }
    } catch (e: Exception) {
        defaultValue
    }


inline fun <T, R> T.convertTo(transform: (T) -> R): R {
    return transform(this)
}

inline fun <reified T> List<T>.copyList(): ArrayList<T> {
    val gsonList = Gson().toJson(this)
    return Gson().fromJson<ArrayList<T>>(gsonList)
}

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
