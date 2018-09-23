package diogo.com.chat_firestore

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class AbstractActivity : AppCompatActivity(), BaseView {
    override fun showMessage(message: String) = submitOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun submitOnUiThread(block: () -> Unit) = this.runOnUiThread {
        block()
    }

    fun submitSafeThread(block: () -> Unit) = Thread {
        block()
    }.start()
}