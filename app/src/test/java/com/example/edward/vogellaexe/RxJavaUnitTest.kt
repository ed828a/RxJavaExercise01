package com.example.edward.vogellaexe

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.observers.TestObserver
import org.junit.Test

import junit.framework.Assert.assertTrue

/**
 * Created by Edward on 1/2/2019.
 */
class RxJavaUnitTest {
    var result = ""

    @Test
    fun returnOneValue(){
        result = ""
        val observable = Observable.just("Hello")
        observable.subscribe{s ->
            result = s
        }

        assertTrue(result == "Hello")
    }

    @Test
    fun expectNPE(){
        val todoObservable = Observable.create<String>(object : ObservableOnSubscribe<String>{
            override fun subscribe(emitter: ObservableEmitter<String>) {
                try {
                    val todos = this@RxJavaUnitTest.getTodos()
                    todos ?: throw NullPointerException("todos was null")
                    for (todo in todos){
                        emitter.onNext(todo)
                    }
                    emitter.onComplete()
                }catch (e: Exception){
                    emitter.onError(e)
                }
            }

        })

        val testObserver = TestObserver<Any>()
        todoObservable.subscribe(testObserver)

        testObserver.assertError(java.lang.NullPointerException::class.java)
    }

    fun getTodos(): List<String>?{
        return null
    }
}