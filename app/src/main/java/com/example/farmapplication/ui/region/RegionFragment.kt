package com.example.farmapplication.ui.region

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.ui.adapter.FarmAdapter
import com.example.farmapplication.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_region.*

/**
 * A simple [Fragment] subclass.
 */
class RegionFragment : Fragment() {

    private lateinit var farmAdapter: FarmAdapter
//    private lateinit var navController: NavController

    companion object {
        private var listFarm = listOf<FarmEntity>()
        fun newInstance(listData: List<FarmEntity>): RegionFragment {
            listFarm = listData
            return RegionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = Navigation.findNavController(view)

        farmAdapter = FarmAdapter(requireContext()) {
//            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
//            navController.navigate(direction)
        }
        rv_farm.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = farmAdapter
            setHasFixedSize(true)
        }

        farmAdapter.submitList(listFarm)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
