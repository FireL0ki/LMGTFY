package com.example.lmgtfy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var searchText: EditText
    private lateinit var searchButton: Button
    private lateinit var searchConfirmation: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.enter_search_text)
        searchButton = findViewById(R.id.search_button)
        searchConfirmation = findViewById(R.id.show_search_text)

        searchText.doAfterTextChanged {
           echoUserSearchTerm()
        }

        searchButton.setOnClickListener {
            launchSearch()
        }
    }

    private fun googleSearch(text: String) {
        val webAddress = "https://www.google.com/search?=$text"
        // uri -- uniform resource indicator
        val uri = Uri.parse(webAddress)
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(browserIntent)
    }

    private fun launchSearch() {
        val text = searchText.text
        // isBlank() checks for spaces and also counts that as blank. isEmpty() would not
        // count spaces as empty
        if (text.isBlank()) {
            // TODO show user a message to enter text
            // this refers to this activity, make sure to add .show() or the Toast will not show up
            Toast.makeText(this, getString(R.string.no_text_error_message), Toast.LENGTH_SHORT).show()
        } else {
            // TODO show a Toast confirmation
            Toast.makeText(this, getString(R.string.searching_confirmation_message, text), Toast.LENGTH_LONG).show()
            // TODO launch web browser to search Google

            googleSearch(text.toString())
        }
    }

    private fun echoUserSearchTerm() {
        val text = searchText.text
        if (text.isNotBlank()) {
            // gets the string resource and then uses the second argument to replace/set the
            // placeholder we created in the string resource (to show message: 'searching for (insert text)'
            searchConfirmation.text = getString(R.string.search_display_text, text)
        } else {
            // this will display the string 'searching for' + "..." --- could also just replace with empty string
            searchConfirmation.text = getString(R.string.search_display_text, "...")
        }
    }
}