package com.example.edward.vogellaexe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val intent =
        when (view.id) {
            R.id.first -> Intent(this, RxJavaSimpleActivity::class.java)

            R.id.second -> Intent(this, ColorsActivity::class.java)

            R.id.third -> Intent(this, BooksActivity::class.java)

            R.id.fourth -> Intent(this, SchedulerActivity::class.java)
            else -> Intent(this, SchedulerActivity::class.java)
        }

        startActivity(intent)
    }

}
