package com.eklavya.eRakt.activities

import android.app.Dialog
import android.content.Context
import com.eklavya.eRakt.R

class CustomPbar(context: Context) {
    private var dialog: Dialog = Dialog(context)

    init {
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
    }
    fun showPbar(){
        dialog.setContentView(R.layout.custom_pbar)
        dialog.show()
    }
    fun hidePbar(){
        dialog.dismiss()
    }

}