package com.techyshed.friendpaste

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import com.techyshed.friendpaste.Activity.AddActivity
import com.techyshed.friendpaste.Activity.ViewActivity
import com.techyshed.friendpaste.Database.DbManager
import com.techyshed.volleyk.UserData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_items.view.*

class MainActivity : AppCompatActivity() {

    var listPaste=ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadQuery("%")

        listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, l ->

            var item = listView.getItemAtPosition(position) as UserData
            GoTOUpdate(item)

        })
    }

    fun GoTOUpdate(data:UserData)
    {
        var intent = Intent(applicationContext, ViewActivity::class.java)
        intent.putExtra("title",data.title)
        intent.putExtra("id",data.id)
        intent.putExtra("des",data.des)
        intent.putExtra("url",data.url)
        Log.i("id---",data.title)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    fun LoadQuery(title:String){
        var dbManager = DbManager(applicationContext)
        var projection = arrayOf("ID","Title","Description","Url")
        var selectionArgs = arrayOf(title)
        var cursor = dbManager.Query(projection,"Title like ?",selectionArgs,"ID"+" DESC")
        listPaste.clear()

        if(cursor.moveToFirst())
        {
            do{

                var ID = cursor.getInt(cursor.getColumnIndex("ID"))
                var Title = cursor.getString(cursor.getColumnIndex("Title"))
                var Description = cursor.getString(cursor.getColumnIndex("Description"))
                var Url = cursor.getString(cursor.getColumnIndex("Url"))

                listPaste.add(UserData(ID,Title,Description,Url))

            }while(cursor.moveToNext())
        }

        var myNotesAdapter = MyListAdapter(this,listPaste)
        listView.adapter = myNotesAdapter
    }

    inner class MyListAdapter: BaseAdapter {

        var context: Context?=null
        var myList:ArrayList<UserData>?=null
        //var myImageList:ArrayList<String>?=null

        constructor(context: Context,dataList : ArrayList<UserData>):super(){
            this.context = context
            this.myList = dataList
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var myView = layoutInflater.inflate(R.layout.list_items,null)
            var item = myList!![p0]
            myView.ID.setText("ID : " + item.id!!.toString())
            myView.title.setText("Title : " + item.title!!.toString())
            myView.url.setText("Snippet : " + item.des!!.toString())

            return myView
        }

        override fun getItem(p0: Int): Any {
            return myList!![p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return myList!!.size
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)

        val sv: SearchView = menu!!.findItem(R.id.search).actionView as SearchView

        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                LoadQuery("%" + query+ "%")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when(item!!.itemId){
            R.id.addPaste ->
            {
                var i = Intent(this, AddActivity::class.java)
                startActivity(i)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

