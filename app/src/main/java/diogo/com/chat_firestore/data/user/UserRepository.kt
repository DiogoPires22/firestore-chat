package diogo.com.chat_firestore.data.user

import com.google.firebase.firestore.FirebaseFirestore
import diogo.com.chat_firestore.data.Converter
import diogo.com.chat_firestore.data.Repository

class UserRepository( override val converter: Converter<User>) : Repository<User> {

    override fun create(data: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findById(id: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}