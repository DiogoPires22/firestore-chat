package diogo.com.chat_firestore.data

import com.google.firebase.firestore.DocumentSnapshot


interface Converter<T> {
    fun toDatabase(item: T): Map<String,Any>
    fun toMemory(item: DocumentSnapshot): T
}