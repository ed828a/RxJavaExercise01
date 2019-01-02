package com.example.edward.vogellaexe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java_simple.*

class RxJavaSimpleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    var value = 0

    private val serverDownloadObservable = Observable.create<Int> { emitter ->
        SystemClock.sleep(10000)
        emitter.onNext(5)
        emitter.onComplete()
    }!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_simple)

        button.setOnClickListener {
            it.isEnabled = false
            val subscribe = serverDownloadObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { integer ->
                    updateTheUserInterface(integer)
                    it.isEnabled = true
                }
            compositeDisposable.add(subscribe)
        }

        toastbutton.setOnClickListener {
            val t = Toast.makeText(this, "Still active ${value++}", Toast.LENGTH_SHORT)
            t.setGravity(Gravity.CENTER, 0, 0)
            t.show()
        }
    }

    private fun updateTheUserInterface(integer: Int) {
        resultView.text = integer.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
