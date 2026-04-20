package com.training.ecommercetrainingproject.ui.common.views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.training.ecommercetrainingproject.R
import kotlin.random.Random

class ProgressDialogManager(private val context: Context) {
    private var dialog: Dialog?=null
    fun showDialog(){
        if (dialog == null){
            dialog = Dialog(context).apply {
                val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null)
                setContentView(view)
                window?.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT)
                )
                setCancelable(false)
            }
        }
        if (dialog?.isShowing() == false){
            dialog?.show()
        }
    }

    fun dismissDialog(){
        if (dialog?.isShowing() == true){
            dialog?.dismiss()
        }
    }

    //    companion object {
//        fun createProgressDialog(context: Context): Dialog {
//            val dialog = Dialog(context)
//            val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog_layout, null)
//            dialog.setContentView(view)
//            //dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//            dialog.window?.setBackgroundDrawable(
//                ColorDrawable(Color.TRANSPARENT)
//            )
//            dialog.setCancelable(false)
//            return dialog
//        }
//    }
}
