package diogo.com.chat_firestore.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import diogo.com.chat_firestore.AbstractActivity
import diogo.com.chat_firestore.BaseView
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.auth.presenter.AuthPresenter
import diogo.com.chat_firestore.auth.view.AuthView
import diogo.com.chat_firestore.chat.view.ChatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AuthActivity : AbstractActivity(), AuthView {


    private val presenter: AuthPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        configLoginFlow()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun getBaseView(): BaseView {
        return this
    }

    override fun changeLoadingStatus(show: Boolean) = submitOnUiThread {
        loading_view.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun finishLogin() {
        val intent = Intent(applicationContext, ChatActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        applicationContext.startActivity(intent)
    }

    private fun configLoginFlow() {
        btn_auth.setOnClickListener({
            if (!user_box.text.isNullOrEmpty()) {
                val user = user_box.text.toString()


                submitSafeThread {
                    presenter.login(user, {
                        finishLogin()
                    }, {
                        showMessage(it.message ?: it.localizedMessage)
                    })
                }
            }
        })
    }


}
