package com.app.sampleui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.app.sampleui.R


class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        title = "Create Post"

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        val lookingFor: TextView = findViewById(R.id.lookingfor)

        lookingFor.setOnClickListener {
            val popupmenu = PopupMenu(CreatePostActivity@ this, it)
            popupmenu.menuInflater.inflate(R.menu.looking_for_menu, popupmenu.menu)
            popupmenu.show()

            popupmenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                lookingFor.text = item.title
                true
            })
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}