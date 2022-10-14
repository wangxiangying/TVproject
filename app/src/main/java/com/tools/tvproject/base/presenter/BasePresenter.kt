package base.presenter

import android.annotation.SuppressLint

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by iamwxy on 2020/6/21 12:01
 * description:
 */
@SuppressLint("RestrictedApi")
abstract class BasePresenter<T>(protected val mView: T) : LifecycleEventObserver {
    val TAG = this::class.java.simpleName

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.destroy()
        }
    }

    // 需要在该方法中，取消掉未完成的异步任务，调用usecase.dispose
    abstract fun destroy()
}