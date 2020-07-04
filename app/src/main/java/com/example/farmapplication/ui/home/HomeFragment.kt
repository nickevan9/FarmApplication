package com.example.farmapplication.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.helper.ObjectBox
import com.example.farmapplication.ui.adapter.CENTRAL
import com.example.farmapplication.ui.adapter.HomePagerAdapter
import com.example.farmapplication.ui.adapter.NORTHERN
import com.example.farmapplication.ui.adapter.SOUTHWARD
import com.google.android.material.tabs.TabLayoutMediator
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.query.Query
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    private var farmQuery: Query<FarmEntity>? = null
    private var farmBox: Box<FarmEntity>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBox()

        initView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

    private fun initBox() {
        farmBox = ObjectBox.boxStore.boxFor()

    }

    private fun initView() {

        vp_home.adapter = HomePagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tab_home, vp_home) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addItemFragment)
        }
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            NORTHERN -> R.drawable.north_tab_selector
            CENTRAL -> R.drawable.central_tab_selector
            SOUTHWARD -> R.drawable.south_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            NORTHERN -> getString(R.string.northern)
            CENTRAL -> getString(R.string.central)
            SOUTHWARD -> getString(R.string.southward)
            else -> null
        }
    }


}
