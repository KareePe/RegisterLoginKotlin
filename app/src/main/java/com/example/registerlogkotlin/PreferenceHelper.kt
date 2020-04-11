package com.example.registerlogkotlin

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(private val context: Context) {

    private val INTRO = "intro"
    private val USERNAME = "username"
    private val FIRSTNAME = "first_name"
    private val VINNO = "vinno"
    private val PARKINGLOT = "parkinglot"
    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences(
            "shared",
            Context.MODE_PRIVATE
        )
    }

    fun putIsLogin(loginorout: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(INTRO, loginorout)
        edit.commit()
    }

    fun getIsLogin(): Boolean {
        return app_prefs.getBoolean(INTRO, false)
    }

    fun putName(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(USERNAME, loginorout)
        edit.commit()
    }

    fun getNames(): String? {
        return app_prefs.getString(USERNAME, "")
    }

    fun putHobby(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(FIRSTNAME, loginorout)
        edit.commit()
    }

    fun getHobbys(): String? {
        return app_prefs.getString(FIRSTNAME, "")
    }

    fun putvinno(loginorout: String){
        val edit = app_prefs.edit()
        edit.putString(VINNO,loginorout)
        edit.commit()
    }

    fun getVinno(): String?{
        return app_prefs.getString(VINNO,"")
    }

    fun putparkinglot(loginorout: String){
        val edit = app_prefs.edit()
        edit.putString(PARKINGLOT,loginorout)
        edit.commit()
    }

    fun getparkinglot(): String?{
        return app_prefs.getString(PARKINGLOT,"")
    }

}