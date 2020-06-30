package com.example.farmapplication.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.ui.region.CentralFragment
import com.example.farmapplication.ui.region.NorthFragment
import com.example.farmapplication.ui.region.SouthFragment

const val NORTHERN = 0
const val CENTRAL = 1
const val SOUTHWARD = 2

class HomePagerAdapter(
    fm: FragmentManager,
    lifeCycle : Lifecycle
) :
    FragmentStateAdapter(fm,lifeCycle) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SouthFragment.newInstance()
            }
            1 -> {
                CentralFragment.newInstance()
            }

            2 -> {
                NorthFragment.newInstance()
            }
            else -> {
                SouthFragment.newInstance()
            }
        }
    }

}