package com.example.stm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stm.databinding.ActivityMainBinding
import com.example.todolist.Note
import com.google.firebase.database.*
import java.util.*


class NotesActivity : AppCompatActivity() {
    var databaseReference: DatabaseReference? = null
    var eventListener: ValueEventListener? = null
    private lateinit var dataList: ArrayList<Note>
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this@NotesActivity, 1)
        binding.recyclerView.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(this@NotesActivity)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = MyAdapter(this@NotesActivity, dataList, supportFragmentManager )
        binding.recyclerView.adapter = adapter
        dialog.show()


        dataList.clear()
        for (item  in Note.data) {
            val dataClass = item
            if (dataClass != null) {
                dataList.add(dataClass)
            }
        }
        adapter.notifyDataSetChanged()
        dialog.dismiss()




        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@NotesActivity, UploadActivity::class.java)
            startActivity(intent)
        })


    }

}