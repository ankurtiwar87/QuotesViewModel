package com.ankur.quotemvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val quotesText:TextView
       get() = findViewById(R.id.quoteText)
    private val quotesAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuotes(mainViewModel.getQuote())
    }

    private fun setQuotes(quote: Quotes) {

        quotesText.text=quote.text
        quotesAuthor.text=quote.author
    }



    fun onPrevious(view: View) {
        setQuotes(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuotes(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}