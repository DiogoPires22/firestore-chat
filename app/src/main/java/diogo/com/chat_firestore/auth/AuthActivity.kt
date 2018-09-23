package diogo.com.chat_firestore.auth

import android.os.Bundle
import diogo.com.chat_firestore.AbstractActivity
import diogo.com.chat_firestore.BaseView
import diogo.com.chat_firestore.R

class AuthActivity : AbstractActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }


}
