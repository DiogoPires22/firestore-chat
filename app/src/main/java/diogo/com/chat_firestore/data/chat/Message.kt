package diogo.com.chat_firestore.data.chat

import java.util.*


data class Message(
        val id: String,
        val message: String,
        val timestamp: Date
//        , val user: String
){


    companion object {
        const val FIELD_MESSAGE = "message"
        const val FIELD_TIMESTAMP = "timestamp"
        const val USER = "user"
    }
}