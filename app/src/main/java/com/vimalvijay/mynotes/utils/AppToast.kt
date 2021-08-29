package com.vimalvijay.mynotes.utils

import android.content.Context
import android.widget.Toast

fun Context.showSmallToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}