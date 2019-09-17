package com.xMitternachtx.ecwid.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xMitternachtx.ecwid.R
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.reflect.KClass

fun <T : ViewModel> FragmentActivity.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
    return ViewModelProviders.of(this, factory).get(model.java)
}

@SuppressLint("NewApi")
fun Activity.circularUnRevealed(mView: View, revealX: Int, revealY: Int) {
    val finalRadius = (Math.max(mView.width, mView.height) * 1.1)
    val circularReveal = ViewAnimationUtils.createCircularReveal(
            mView, revealX, revealY, finalRadius.toFloat(), 0f)

    circularReveal.duration = 400
    circularReveal.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            View.INVISIBLE
            finish()
            overridePendingTransition(0, 0)
        }
    })
    circularReveal.start()
}

@SuppressLint("NewApi")
fun Activity.circularRevealed(view: View, x: Int, y: Int) {
        val finalRadius = (Math.max(view.width, view.height) * 1.1)
        val circularReveal = ViewAnimationUtils.createCircularReveal(view, x, y, 0f, finalRadius.toFloat())
        circularReveal.duration = 400
        circularReveal.interpolator = AccelerateInterpolator()
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        view.visibility = View.VISIBLE
        circularReveal.start()
}


//Why Implement full anko if we can use extension
fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        task: AsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = AsyncContext(WeakReference(this))
    return BackgroundExecutor.submit {
        return@submit try {
            context.task()
        } catch (thr: Throwable) {
            val result = exceptionHandler?.invoke(thr)
            if (result != null) {
                result
            } else {
                Unit
            }
        }
    }
}
class AsyncContext<T>(val weakRef: WeakReference<T>)
private val crashLogger = { throwable : Throwable -> throwable.printStackTrace() }
internal object BackgroundExecutor {
    var executor: ExecutorService =
            Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())
    fun <T> submit(task: () -> T): Future<T> = executor.submit(task)
}