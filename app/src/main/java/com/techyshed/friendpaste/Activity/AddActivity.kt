package com.techyshed.friendpaste.Activity

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.techyshed.friendpaste.Database.DbManager
import com.techyshed.friendpaste.R
import com.techyshed.volleyk.APIController
import com.techyshed.volleyk.ServiceVolley
import kotlinx.android.synthetic.main.activity_add.*
import org.json.JSONObject

class AddActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.add_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when(item!!.itemId){
            R.id.cancel ->
            {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun BuSubmit(view: View){

        progressBar.visibility = View.VISIBLE
        var dbManager = DbManager(applicationContext)
        var values = ContentValues()

        var title = etTitle.text.toString()
        var snippet = etSnippet.text.toString()
        var language = etLanguage.text.toString()

        val service = ServiceVolley()

        val apiController = APIController(service)

        val path = ""

        val params = JSONObject()

        params.put("title", title)

        params.put("snippet", snippet)

        params.put("language", language)


        apiController.post(path, params) { response ->

            // Parse the result
            //Log.i(path, "/post request OK! Response9999: $response")

            var c = response!!.getString("ok")

            if(c.equals("true"))
            {
                var url = response!!.getString("url")
                values.put("Title", etTitle.text.toString())
                values.put("Description", etSnippet.text.toString())
                values.put("Url", url)

                var ID = dbManager.Insert(values)

                if (ID > 0) {
                    Toast.makeText(this, "Paste is Added", Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.INVISIBLE
                    finish()
                } else {
                    Toast.makeText(this, "Cannot Add Paste", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this, "Cannot Add Paste", Toast.LENGTH_LONG).show()
            }

        }
    }
}
