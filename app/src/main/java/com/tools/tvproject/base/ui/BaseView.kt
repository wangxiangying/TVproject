package base.ui

/**
 * Created by iamwxy on 2020/6/20 20:17
 * description:
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(errorCode: String, error: String)
}