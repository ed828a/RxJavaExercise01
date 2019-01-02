package com.example.edward.vogellaexe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_scheduler.*
import java.util.concurrent.Callable

class SchedulerActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduler)

        configureLayout()
    }

    private fun configureLayout() {
        scheduleLongRunningOperation.setOnClickListener {
            val disposable = Observable.fromCallable {
                SystemClock.sleep(10000)
                "Hello"
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressBar.visibility = View.VISIBLE
                    scheduleLongRunningOperation.isEnabled = true
                    val text = "${messageArea.text}\n Progressbar set visible"
                    messageArea.text = text
                }
                .subscribe(object : DisposableObserver<String>() {
                    override fun onComplete() {
                        val text = messageArea.text.toString() + "\n" + "OnComplete"
                        messageArea.text = text
                        progressBar.visibility = View.INVISIBLE
                        scheduleLongRunningOperation.isEnabled = true
                        val text2 = messageArea.text.toString() + "\n" + "Hidding Progressbar"
                        messageArea.text = text2
                    }

                    override fun onNext(message: String) {
                        val text = messageArea.text.toString() + "\n" + "onNext " + message
                        messageArea.text = text
                    }

                    override fun onError(e: Throwable) {
                        val text = messageArea.text.toString() + "\n" + "OnError"
                        messageArea.text = text
                        progressBar.visibility = View.INVISIBLE
                        scheduleLongRunningOperation.isEnabled = true
                        val text2 = messageArea.text.toString() + "\n" + "Hidding Progressbar"
                        messageArea.text = text2
                    }
                })
        }
    }
}
