package com.example.edward.vogellaexe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    lateinit var restClient: RestClient
    lateinit var stringsAdapter: SimpleStringAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        restClient = RestClient(this)
        configureLayout()
        createObservable()
    }

    private fun configureLayout() {
        stringsAdapter = SimpleStringAdapter(this)
        booksList.adapter = stringsAdapter
    }

    private fun createObservable() {
        val disposable = Observable.fromCallable {
            restClient.getFavoriteBooks()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { strings ->
                displayBooks(strings)
            }
        compositeDisposable.add(disposable)
    }

    private fun displayBooks(books: List<String>) {
        stringsAdapter.setStrings(books)
        loader.visibility = View.GONE
        booksList.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
