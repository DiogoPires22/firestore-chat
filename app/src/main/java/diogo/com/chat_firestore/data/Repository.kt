package diogo.com.chat_firestore.data


interface Repository<T> {
    val converter: Converter<T>
    fun create(data: T): Boolean
    fun getAll(): List<T>
    fun findById(id: String): T


    companion object {
        const val USER_COLLECTION: String = "users"
        const val CHAT_COLLECTION: String = "chat"
    }
}