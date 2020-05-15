package com.example.farmapplication.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.ui.region.RegionFragment

const val NORTHERN = 0
const val CENTRAL = 1
const val SOUTHWARD = 2

class HomePagerAdapter(fragment: Fragment, listFarmNorth: List<FarmEntity>, listFarmCentral: List<FarmEntity>, listFarmSouth: List<FarmEntity>) :
    FragmentStateAdapter(fragment) {

    private val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        NORTHERN to { RegionFragment.newInstance(listFarmNorth) },
        CENTRAL to { RegionFragment.newInstance(listFarmCentral) },
        SOUTHWARD to { RegionFragment.newInstance(listFarmSouth) }
    )

    override fun getItemCount(): Int = tabFragmentCreator.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}