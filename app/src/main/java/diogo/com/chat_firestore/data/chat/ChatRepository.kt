package diogo.com.chat_firestore.data.chat

import com.google.firebase.firestore.*
import diogo.com.chat_firestore.data.Converter
import diogo.com.chat_firestore.data.Repository
import java.util.concurrent.CountDownLatch


interface ChatRepository : Repository<Message> {
    fun getAndSubscribe(onNext: (item: Message) -> Unit, onError: (throwable: Throwable) -> Unit)
}


class ChatRepositoryImpl(override val converter: Converter<Message>) : ChatRepository {
    private var db: FirebaseFirestore

    init {
        db = FirebaseFirestore.getInstance()
    }

    override fun create(data: Message): String? {

        val latch = CountDownLatch(1)
        val map = converter.toDatabase(data)
        var id: String? = null
        db.collection(Repository.CHAT_COLLECTION).add(map)
                .addOnSuccessListener({ documentReference ->
                    id = documentReference.id
                    latch.countDown()
                })
                .addOnFailureListener( {
                    latch.countDown()
                })


        latch.await()
        return id
    }

    override fun getAll(): List<Message> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findById(id: String): Message {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAndSubscribe(onNext: (items: Message) -> Unit, onError: (throwable: Throwable) -> Unit) {
        db.collection(Repository.CHAT_COLLECTION)
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(value: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                        try {
                            value?.let {
                                for (doc in value) {
                                    onNext(converter.toMemory(doc))
                                }
                            }
                        } catch (ex: Exception) {
                            onError(ex)
                        }
                    }

                })
    }
}