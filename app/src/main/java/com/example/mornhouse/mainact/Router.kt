package com.example.mornhouse.mainact

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mornhouse.R

class Router {

    fun openFragment(mainActivity: MainActivity, pointFrag: Fragment) {
        val manager: FragmentManager = mainActivity.supportFragmentManager
        manager.beginTransaction()
            .replace(R.id.pointNav, pointFrag)
            .commit()
    }

    fun openFragmentChosen(
        mainActivity: MainActivity,
        pointfragmentClass: Class<out Fragment>,
        bundle: Bundle,
    ) {
        val manager: FragmentManager = mainActivity.supportFragmentManager
        manager.beginTransaction()
            .replace(R.id.pointNav, pointfragmentClass, bundle)
            .addToBackStack("goback")
            .commit()
    }

}