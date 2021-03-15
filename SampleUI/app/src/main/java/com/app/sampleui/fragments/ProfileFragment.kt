package com.app.sampleui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.app.sampleui.R
import com.app.sampleui.activities.CreatePostActivity
import com.app.sampleui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import io.github.kobakei.materialfabspeeddial.FabSpeedDial
import kotlin.math.roundToInt

class ProfileFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null
    var adapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ViewPagerAdapter(childFragmentManager)

        adapter!!.add(RequestsFragment())
        adapter!!.add(OfferingsFragment())
        adapter!!.add(RecommendsFragment())

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        (context as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
        (context as AppCompatActivity).supportActionBar?.title = "Profile settings"

        tabLayout = view.findViewById(R.id.tabLayout)
        pager = view.findViewById(R.id.viewPager)
        pager!!.layoutParams = getPagerParams()
        pager!!.adapter = adapter
        pager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager!!.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        val fab: FabSpeedDial = view.findViewById(R.id.fab)
        fab.addOnStateChangeListener { open ->
            if (open) {
                fab.setBackgroundColor(Color.BLACK)
                fab.alpha = 0.8f
            } else {
                fab.setBackgroundColor(Color.TRANSPARENT)
                fab.alpha = 1f
            }
        }
        fab.addOnMenuItemClickListener { fab, textview, itemid ->

            val intent: Intent = Intent(context, CreatePostActivity::class.java)
            startActivity(intent)

        }

        return view
    }

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    fun getPagerParams(): LinearLayout.LayoutParams {
        val screenHeight = resources.displayMetrics.heightPixels as Int
        val pagerHeight =
            screenHeight - resources.getDimension(R.dimen.tabLayoutHeight).roundToInt() -
                    resources.getDimension(R.dimen.bottomNavHeight).roundToInt() -
                    resources.getDimension(R.dimen.statusBarHeight).roundToInt()


        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(MATCH_PARENT, pagerHeight)

        Log.e("123", "$screenHeight $pagerHeight")
        return params
    }
}