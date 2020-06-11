package com.example.farmapplication.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load

import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private var farmEntity: FarmEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        farmEntity = args.FARMENTITY as FarmEntity
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isToolbarShown = false
        plant_detail_scrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = scrollY > toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    toolbar_layout.isTitleEnabled = shouldShowToolbar
                }
            }
        )

        initToolbar()
        initView()

    }

    private fun initView(){
        plant_detail_name.text = farmEntity?.name
        plant_description.text = farmEntity?.description
        detail_image.load(farmEntity?.imagePath)

    }


    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    createShareIntent()
                    true
                }
                else -> false
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = farmEntity.let { plant ->
            if (plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }


}
