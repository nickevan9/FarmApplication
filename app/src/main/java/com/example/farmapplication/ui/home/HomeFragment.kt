package com.example.farmapplication.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.data.model.FarmEntity_
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

    private var listFarmNorth = mutableListOf<FarmEntity>()
    private var listFarmCentral = mutableListOf<FarmEntity>()
    private var listFarmSouth = mutableListOf<FarmEntity>()

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
        // TODO: Use the ViewModel
    }

    private fun initBox() {
        farmBox = ObjectBox.boxStore.boxFor()

        listFarmNorth = farmBox!!.query().equal(FarmEntity_.region, 0).build().find()

        listFarmCentral = farmBox!!.query().equal(FarmEntity_.region, 1).build().find()

        listFarmSouth = farmBox!!.query().equal(FarmEntity_.region, 2).build().find()
    }

    private fun initView() {
        initList()

        vp_home.adapter = HomePagerAdapter(this, listFarmNorth, listFarmCentral, listFarmSouth)

        TabLayoutMediator(tab_home, vp_home) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()
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

    private fun initList(){
        listFarmNorth.add(FarmEntity(0,"Tomato","https://upload.wikimedia.org/wikipedia/commons/1/17/Cherry_tomatoes_red_and_green_2009_16x9.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Pear","https://upload.wikimedia.org/wikipedia/commons/1/13/More_pears.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Orange","https://upload.wikimedia.org/wikipedia/commons/2/22/Apfelsinenbaum--Orange_tree.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Beet","https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Avocado","https://upload.wikimedia.org/wikipedia/commons/e/e4/Branch_and_fruit_of_the_Maluma_avocado_cultivar.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Sunflower","https://upload.wikimedia.org/wikipedia/commons/a/aa/Sunflowers_in_field_flower.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Mango","https://upload.wikimedia.org/wikipedia/commons/6/67/Mangos_criollos_y_pera.JPG","",0))
        listFarmNorth.add(FarmEntity(0,"Yulan Magnolia","https://upload.wikimedia.org/wikipedia/commons/1/13/Yulan_magnolia_%28Magnolia_denudata%29_%2816953983745%29.jpg","",0))
        listFarmNorth.add(FarmEntity(0,"Hibiscus","https://upload.wikimedia.org/wikipedia/commons/8/82/Hibiscus_rosa-sinensis_flower_2.JPG","",0))
        listFarmNorth.add(FarmEntity(0,"Eggplant","https://upload.wikimedia.org/wikipedia/commons/e/e5/Eggplant_display.JPG","",0))


        listFarmCentral.add(FarmEntity(0,"Tomato","https://upload.wikimedia.org/wikipedia/commons/1/17/Cherry_tomatoes_red_and_green_2009_16x9.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Pear","https://upload.wikimedia.org/wikipedia/commons/1/13/More_pears.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Orange","https://upload.wikimedia.org/wikipedia/commons/2/22/Apfelsinenbaum--Orange_tree.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Beet","https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Avocado","https://upload.wikimedia.org/wikipedia/commons/e/e4/Branch_and_fruit_of_the_Maluma_avocado_cultivar.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Sunflower","https://upload.wikimedia.org/wikipedia/commons/a/aa/Sunflowers_in_field_flower.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Mango","https://upload.wikimedia.org/wikipedia/commons/6/67/Mangos_criollos_y_pera.JPG","",0))
        listFarmCentral.add(FarmEntity(0,"Yulan Magnolia","https://upload.wikimedia.org/wikipedia/commons/1/13/Yulan_magnolia_%28Magnolia_denudata%29_%2816953983745%29.jpg","",0))
        listFarmCentral.add(FarmEntity(0,"Hibiscus","https://upload.wikimedia.org/wikipedia/commons/8/82/Hibiscus_rosa-sinensis_flower_2.JPG","",0))
        listFarmCentral.add(FarmEntity(0,"Eggplant","https://upload.wikimedia.org/wikipedia/commons/e/e5/Eggplant_display.JPG","",0))

        listFarmSouth.add(FarmEntity(0,"Tomato","https://upload.wikimedia.org/wikipedia/commons/1/17/Cherry_tomatoes_red_and_green_2009_16x9.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Pear","https://upload.wikimedia.org/wikipedia/commons/1/13/More_pears.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Orange","https://upload.wikimedia.org/wikipedia/commons/2/22/Apfelsinenbaum--Orange_tree.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Beet","https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Avocado","https://upload.wikimedia.org/wikipedia/commons/e/e4/Branch_and_fruit_of_the_Maluma_avocado_cultivar.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Sunflower","https://upload.wikimedia.org/wikipedia/commons/a/aa/Sunflowers_in_field_flower.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Mango","https://upload.wikimedia.org/wikipedia/commons/6/67/Mangos_criollos_y_pera.JPG","",0))
        listFarmSouth.add(FarmEntity(0,"Yulan Magnolia","https://upload.wikimedia.org/wikipedia/commons/1/13/Yulan_magnolia_%28Magnolia_denudata%29_%2816953983745%29.jpg","",0))
        listFarmSouth.add(FarmEntity(0,"Hibiscus","https://upload.wikimedia.org/wikipedia/commons/8/82/Hibiscus_rosa-sinensis_flower_2.JPG","",0))
        listFarmSouth.add(FarmEntity(0,"Eggplant","https://upload.wikimedia.org/wikipedia/commons/e/e5/Eggplant_display.JPG","",0))
    }


}
