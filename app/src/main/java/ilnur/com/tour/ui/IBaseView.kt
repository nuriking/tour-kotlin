package ilnur.com.tour.ui

import com.arellomobile.mvp.MvpView

interface IBaseView : MvpView {
    fun onShowLoading()

    fun onHideLoading()

    fun onError(errorText: String)
}
