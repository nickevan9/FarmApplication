package com.example.farmapplication.ui.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity

/**
 * A simple [Fragment] subclass.
 */
class RegionFragment : Fragment() {



    companion object {
        private var listFarm = listOf<FarmEntity>()
        fun newInstance(listData: List<FarmEntity>): RegionFragment {
            listFarm = listData
            return RegionFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
