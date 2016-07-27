package com.cypressworks.kotlintypealiasicbug

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.Observable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncRx {
            val flag = true
            Observable.just("").awaitFirst()
            bar(flag)
        }
    }

    fun bar(flag: Boolean) {
    }

}

fun asyncRx(coroutine c: RxController.() -> Continuation<Unit>) {
    RxController().c().resume(Unit)
}

class RxController {

    suspend fun <V> Observable<V>.awaitFirst(x: Continuation<V>) {
        this.first().subscribeWithContinuation(x)
    }

    private fun <V> Observable<V>.subscribeWithContinuation(x: Continuation<V>) {
        subscribe(x::resume, x::resumeWithException)
    }
}
