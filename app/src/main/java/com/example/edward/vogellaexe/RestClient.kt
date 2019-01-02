package com.example.edward.vogellaexe

import android.content.Context
import android.os.SystemClock


/**
 * Created by Edward on 1/2/2019.
 */
class RestClient(val context: Context) {

    fun getFavoriteBooks(): List<String> {
        SystemClock.sleep(8000)// "Simulate" the delay of network.
        return createBooks()
    }

    fun getFavoriteBooksWithException(): List<String> {
        SystemClock.sleep(8000)// "Simulate" the delay of network.
        throw RuntimeException("Failed to load")
    }

    private fun createBooks(): List<String> {
        val books = ArrayList<String>()
        books.add("Lord of the Rings")
        books.add("The dark elf")
        books.add("Eclipse Introduction")
        books.add("History book")
        books.add("Der kleine Prinz")
        books.add("7 habits of highly effective people")
        books.add("Other book 1")
        books.add("Other book 2")
        books.add("Other book 3")
        books.add("Other book 4")
        books.add("Other book 5")
        books.add("Other book 6")
        return books
    }
}