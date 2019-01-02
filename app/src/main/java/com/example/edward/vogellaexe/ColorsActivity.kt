package com.example.edward.vogellaexe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {

    lateinit var simpleStringAdapter: SimpleStringAdapter

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        configureLayout()
        createObservable()

    }

    private fun configureLayout() {
        simpleStringAdapter = SimpleStringAdapter(this)
        colorList.adapter = simpleStringAdapter
    }

    private fun getColorList(): List<String> {
        val colors = ArrayList<String>()
        colors.add("red")
        colors.add("green")
        colors.add("blue")
        colors.add("pink")
        colors.add("brown")
        return colors
    }
    private fun createObservable() {
        val listObservable = Observable.just(getColorList())
        val disposable = listObservable.subscribe { colors ->
            simpleStringAdapter.setStrings(colors)
        }
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
