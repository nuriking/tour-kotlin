package ilnur.com.tour.ui

import com.arellomobile.mvp.MvpPresenter

open class BasePresenter<T : IBaseView> : MvpPresenter<T>() {

    override fun detachView(view: T) {
        super.detachView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun getView(): T {
        return getViewState()
    }
}
