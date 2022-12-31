package com.example.social_goal_sharing.ui.main.interfaces

import android.view.View

interface AttachmentInterface {
    fun onDownload(view: View)
    fun onDownloaded(path: String)
}