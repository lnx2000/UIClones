package com.app.sampleui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.sampleui.R
import com.app.sampleui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var bottomNavigationView: BottomNavigationView? = null
    var feeds = "Feeds"
    var chats = "Chats"
    var alert = "Notifications"
    var profile = "Profile Settings"
    var profileFragment: ProfileFragment? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileFragment = ProfileFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView!!.setOnNavigationItemSelectedListener {
            Log.e("123", "aya aya ")
            when (it.itemId) {
                R.id.feeds -> {
                    Log.e("123", "feeds ")
                    supportActionBar?.title = feeds
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.chats -> {
                    Log.e("123", "chats ")
                    supportActionBar?.title = chats
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.alert -> {
                    Log.e("123", "alerts ")
                    supportActionBar?.title = alert
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    Log.e("123", "profile ")
                    loadFragment(profileFragment!!)
                    supportActionBar?.title = profile
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        bottomNavigationView?.selectedItemId = R.id.profile
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}