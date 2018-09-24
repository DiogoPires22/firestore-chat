package diogo.com.chat_firestore.data.user

import com.google.firebase.firestore.DocumentSnapshot
import diogo.com.chat_firestore.data.Converter


class AuthConverter : Converter<User> {
    override fun toDatabase(item: User): Map<String, Any> {
        val map = mutableMapOf<String, Any>()

        with(map) {
            put(User.FIELD_USERNAME, item.name)

        }

        return map
    }

    override fun toMemory(item: DocumentSnapshot): User {
        return User(item.id, item.get(User.FIELD_USERNAME) as String)
    }


}