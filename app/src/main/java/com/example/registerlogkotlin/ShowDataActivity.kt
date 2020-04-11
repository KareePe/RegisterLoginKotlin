package com.example.registerlogkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ShowDataActivity : AppCompatActivity() {

    private var etVinno: TextView? = null
    private var etParkinglot: TextView? = null
    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        preferenceHelper = PreferenceHelper(this)

        etVinno = findViewById<EditText>(R.id.etVinno) as EditText
        etParkinglot = findViewById<EditText>(R.id.etParkinglot) as EditText

        etVinno!!.text = preferenceHelper!!.getVinno()
        etParkinglot!!.text = preferenceHelper!!.getparkinglot()

        //MR0JB8DDX03145587 HAVE
        //MR2BC3F3801320463 NOT HAVE
    }
}
