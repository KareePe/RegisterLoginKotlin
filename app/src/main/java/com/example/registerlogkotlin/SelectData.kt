package com.example.registerlogkotlin

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class SelectData : AppCompatActivity() {

    internal var SelectDataURL = "http://10.4.22.209/registerdemo/selectdata.php"
    private var etText: EditText? = null
    private var btnSerach: Button? = null
    private var preferenceHelper: PreferenceHelper? = null
    private val SelectTask = 1
    private var mProcessDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_data)

        preferenceHelper = PreferenceHelper(this)

        etText = findViewById(R.id.etText) as EditText
        btnSerach = findViewById(R.id.btnSerach) as Button

        btnSerach!!.setOnClickListener {
            try {
                selectdata()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }

    }
    @Throws(IOException::class, JSONException::class)
    private fun selectdata(){
        showSimpleProgressDialog(this@SelectData,null,"Loading...",false)
        try{
            Fuel.post(SelectDataURL, listOf("vinno" to etText!!.text.toString())).responseJson {
                    request, response, result ->
                    Log.d("plzzz",result.get().content)
                    onTaskCompleted(result.get().content,SelectTask)
            }
        }catch (e: Exception){

        }finally {

        }
    }

    private fun onTaskCompleted(response: String,task: Int){
        Log.d("responsejson",response)
        removeSimpleProgressDialog()
        when(task){
            SelectTask -> if(isSuccess(response)){
                saveInfo(response)
                Toast.makeText(this@SelectData,"Found Data!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SelectData,ShowDataActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                this.finish()
            }else{
                Toast.makeText(this@SelectData, getErrorMessage(response), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveInfo(response: String){
        preferenceHelper!!.putIsLogin(true)
        try{
            val jsonObject = JSONObject(response)
            if(jsonObject.getString("status") == "true"){
                val dataArray = jsonObject.getJSONArray("data")
                for(i in 0 until dataArray.length()){
                    val dataobj = dataArray.getJSONObject(i)
                    preferenceHelper!!.putvinno(dataobj.getString("vin_no")) //Column IN DATABASE
                    preferenceHelper!!.putparkinglot(dataobj.getString("parkinglot"))
                }
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
    }

    fun isSuccess(response: String): Boolean{
        try{
            val jsonObject = JSONObject(response)
            return if(jsonObject.optString("status") == "true"){
                true
            }else{
                false
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return false
    }

    fun getErrorMessage(response: String) : String{
        try{
            val jsonObject = JSONObject(response)
            return jsonObject.getString("message")
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return "No Data"
    }

    fun showSimpleProgressDialog(context: Context,title: String?,msg: String,isCancelable: Boolean){
        try{
            if(mProcessDialog == null){
                mProcessDialog = ProgressDialog.show(context,title,msg)
                mProcessDialog!!.setCancelable(isCancelable)
            }
            if(!mProcessDialog!!.isShowing){
                mProcessDialog!!.show()
            }
        }catch (ie: IllegalArgumentException){
            ie.printStackTrace()
        }catch (re: RuntimeException){
            re.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun removeSimpleProgressDialog(){
        try{
            if(mProcessDialog != null){
                if(mProcessDialog!!.isShowing){
                    mProcessDialog!!.dismiss()
                    mProcessDialog = null
                }
            }
        }catch (ie: IllegalArgumentException){
            ie.printStackTrace()
        }catch (re: RuntimeException){
            re.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
