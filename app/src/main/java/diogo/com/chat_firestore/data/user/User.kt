package diogo.com.chat_firestore.data.user

data class User(
        val id: String ="",
        val name: String
){


    companion object {
        const val FIELD_USERNAME = "name"
        const val FIELD_ID = "id"
    }
}