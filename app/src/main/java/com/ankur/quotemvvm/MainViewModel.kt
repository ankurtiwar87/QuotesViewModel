package com.ankur.quotemvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context):ViewModel() {


    private var quoteList :Array<Quotes> = emptyArray()
    private var index= 0

    init {
       quoteList=LoadQuotesFromAssets()
    }

//    This is used to read any file

    private fun LoadQuotesFromAssets(): Array<Quotes> {

        val inputStream =context.assets.open("quotes.json")
        val size=inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson =Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)

    }


    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index % quoteList.size]

    fun previousQuote() = quoteList[(--index + quoteList.size) % quoteList.size]


}