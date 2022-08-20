package com.examples.core.utils

import android.content.Context
import android.widget.Toast

/**
* Created by Shehab Elsarky.
*/
fun Context.showToast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}