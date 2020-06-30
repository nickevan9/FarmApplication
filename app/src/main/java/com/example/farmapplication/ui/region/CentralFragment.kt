package com.example.farmapplication.ui.region

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmapplication.R
import com.example.farmapplication.extension.beGone
import com.example.farmapplication.extension.beVisible
import com.example.farmapplication.ui.adapter.FarmAdapter
import com.example.farmapplication.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_region.*

class CentralFragment : Fragment() {

    private lateinit var farmAdapter: FarmAdapter

    private lateinit var viewModel: HomeViewModel
//    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    companion object {
        fun newInstance(): CentralFragment = CentralFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getListItem("trai-cay-mien-trung", 1)

        viewModel.mListItemCentral.observe(viewLifecycleOwner, Observer {
            farmAdapter.submitList(it.listEntity)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                pb_farm.beVisible()
            } else {
                pb_farm.beGone()
            }
        })
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
