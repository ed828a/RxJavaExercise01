package com.example.edward.vogellaexe

import android.arch.lifecycle.Transformations.map
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Created by Edward on 1/2/2019.
 */

fun main(args: Array<String>) {
    val source = Observable.create<String> { emitter ->
        try {
            emitter.onNext("Alpha")
            emitter.onNext("Beta")
            emitter.onNext("Gamma")
            emitter.onNext("Delta")
            emitter.onNext("Epsilon")
            emitter.onComplete()
        } catch (e: Throwable) {
            emitter.onError(e)
        }
    }

    val onNext = Consumer<Int>{i -> println("Received: $i")}

    val onError = Consumer<Throwable> {error ->  error.printStackTrace() }

    val onComplete = Action { println("Complete!")}

    val observer = object : Observer<Int>{
        override fun onComplete() {
            println("it's complete.")
        }

        override fun onSubscribe(d: Disposable) {
            println("what's this onSubscribe")
        }

        override fun onNext(t: Int) {
            println("Received: $t")
        }

        override fun onError(e: Throwable) {
            println("Owe My God! Error!")
        }
    }

    val disposable = Observable
        .just<String>("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
        .map(String::length)
        .filter { i -> i >= 5 }
        .subscribe(
            {i -> println("Received: $i")},  // onNext
            {e-> e.printStackTrace()}, // onError
            { println("Complete!") })             // onComplete

    disposable.dispose()

    val d1 =source.subscribe { s->
        println(s)
    }

    val d2 = source.subscribe { t->
        println(t)
    }

    d1.dispose()
    d2.dispose()
}