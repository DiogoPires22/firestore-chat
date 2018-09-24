package diogo.com.chat_firestore.data.user

import com.google.firebase.firestore.FirebaseFirestore
import diogo.com.chat_firestore.data.Converter
import diogo.com.chat_firestore.data.Repository
import java.util.concurrent.CountDownLatch


interface UserRepository : Repository<User> {
    fun login(username: String, onComplete: (id: String) -> Unit, onError: () -> Unit)
    fun findByName(userName: String): User?
}

class UserRepositoryImpl(override val converter: Converter<User>) : UserRepository {

    private var db: FirebaseFirestore

    init {
        db = FirebaseFirestore.getInstance()
    }

    override fun login(username: String, onComplete: (id: String) -> Unit, onError: () -> Unit) {
        //Its a POC!!!! Because of that i used a bad Validation
        var user = findByName(username)

        if (user == null) {
            user = User(name = username)
            create(user)?.let {
                onComplete(it)
            } ?: kotlin.run {
                onError()
            }
        } else {
            onComplete(user.id)
        }
    }

    override fun create(data: User): String? {
        val latch = CountDownLatch(1)
        val map = converter.toDatabase(data)
        var id: String? = null
        db.collection(Repository.USER_COLLECTION).add(map)
                .addOnSuccessListener({ documentReference ->
                    id = documentReference.id
                    latch.countDown()
                })
                .addOnFailureListener({
                    latch.countDown()
                })


        latch.await()
        return id
    }

    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        val latch = CountDownLatch(1)
        db.collection(Repository.USER_COLLECTION)
                .get()
                .addOnSuccessListener {
                    try {
                        it.forEach({
                            users.add(converter.toMemory(it))
                        })
                        latch.countDown()
                    } catch (ex: Exception) {
                        latch.countDown()
                    }
                }

        latch.await()
        return users
    }

    override fun findById(id: String): User? {
        val latch = CountDownLatch(1)
        var user: User? = null
        db.collection(Repository.USER_COLLECTION)
                .document(id)
                .get()
                .addOnSuccessListener { documents ->
                    user = converter.toMemory(documents)
                    latch.countDown()
                }
                .addOnFailureListener { e ->

                    latch.countDown()
                }

        latch.await()
        return user
    }

    override fun findByName(userName: String): User? {
        val latch = CountDownLatch(1)
        var user: User? = null
        db.collection(Repository.USER_COLLECTION)
                .whereEqualTo(User.FIELD_USERNAME, userName)
                .get()

                .addOnSuccessListener { documents ->
                    documents.first()?.let {
                        user = converter.toMemory(it)
                    }

                    latch.countDown()
                }
                .addOnFailureListener { e ->

                    latch.countDown()
                }

        latch.await()
        return user
    }

}