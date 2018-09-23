package diogo.com.chat_firestore


abstract class AbstractPresenter(override var view: BaseView?) : BasePresenter<BaseView> {


    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        detachView()
    }

    override fun detachView() {
        view = null
    }

    override fun attachView(view: BaseView) {
        this.view = view
    }
}