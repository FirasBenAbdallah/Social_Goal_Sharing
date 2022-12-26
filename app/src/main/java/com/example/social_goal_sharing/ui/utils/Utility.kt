package com.example.social_goal_sharing.ui.utils

import android.app.AlertDialog
import android.content.Context

object Utility {
    val apiUrl: String = "http://192.168.1.9:3000"
    fun showAlert(
        context: Context, title: String = "",
        message: String = "", onYes: Runnable? = null, onNo: Runnable? = null
    ) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("yes") { dialog, which ->
            onYes?.run()
        }
        alertDialogBuilder.setNegativeButton("no") { dialog, which ->
            onNo?.run()
        }
        alertDialogBuilder.show()
    }

    fun showAlert1(
        context: Context, title: String = "",
        message: String = ""
    ) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.show()
    }
}