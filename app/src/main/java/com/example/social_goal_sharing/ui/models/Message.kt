package com.example.social_goal_sharing.ui.models

class Message {
    var id: String = ""
    var message: String = ""
    var sender: User = User()
    var receiver: User = User()
    var attachment: String = ""
    var extension: String = ""
    var attachmentName: String = ""
    var createdAt: Double = 0.0
}