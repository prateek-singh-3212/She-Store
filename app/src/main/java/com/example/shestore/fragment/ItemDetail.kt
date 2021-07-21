package com.example.shestore.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shestore.Adapter.ImageSliderAdapter
import com.example.shestore.Adapter.ItemDetailAdapter
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.itemList
import com.example.shestore.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item.view.*
import kotlinx.android.synthetic.main.fragment_item_detail.*
// TODO : Pass slide in the imageURL list (ViewModel)
class ItemDetail : Fragment(), ItemData {

    private lateinit var buyNow: Button
    private lateinit var toolbar: Toolbar
    private lateinit var itemName: TextView
    private lateinit var itemSubname: TextView
    private lateinit var itemPrice: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** To display the actionbar in fragment it is nessary to set this true.
         * else actionbar menu will not be displayed
         */
        setHasOptionsMenu(true)

        /**
         * To set the enter container transformation we use this. We are not required to set the exit transition.
         * because material library will handle on its own but to be safer side you can use sharedElementReturnTransition
         * */

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300L
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.WHITE)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setFragmentResultListener("itemDetails") { requestKey, bundle ->

            itemDetail_continer.transitionName = bundle.getString("transitionName")

            itemName.text = bundle.getString("itemName")
            itemSubname.text = bundle.getString("itemSubName")
            itemPrice.text = "₹ 500.00"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_item_detail, container, false)
        setViews(view)
        setActionBar()

        val imageURL = listOf(
            "http://placehold.it/120x120&text=image4",
            "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/10207437/2019/12/13/12c817d6-99be-41d7-ad31-f1f08bb1fb331576236930328-W-Women-Orange-Self-Design-Maxi-Dress-7401576236928026-1.jpg",
            "http://shestore.unaux.com/wp-content/uploads/2021/07/0b8e7b6b-f912-4f82-9536-80544b9127631582784256862-Daniel-Klein-Women-Black-Analogue-Watch-DK11421-5-1721582784-1.jpg"
        )

        viewPager.adapter = context?.let { ImageSliderAdapter(it, imageURL, viewPager, this) }
        // syncronize the tabLayout with viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "${(position + 1)}"
        }.attach()

        return view
    }

    private fun setViews(view: View) {
        buyNow = view.findViewById(R.id.itemDetail_buynow)
        toolbar = view.findViewById(R.id.itemDetail_actionbar)
        itemName = view.findViewById(R.id.cardview_itemDetail_title)
        itemPrice = view.findViewById(R.id.cardview_itemDetail_price)
        itemSubname = view.findViewById(R.id.cardview_itemDetail_subname)
        viewPager = view.findViewById(R.id.cardview_itemDetail_viewpager)
        tabLayout = view.findViewById(R.id.cardview_itemDetail_tablayout)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_itemdetail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        buyNow.setOnClickListener {
            val bottomDialogue: BottomSheetDialog? =
                context?.let { it1 -> BottomSheetDialog(it1, R.style.BottomSheetDialogeTheme) }
            val bottomSheetInflater: View = LayoutInflater.from(activity?.applicationContext)
                .inflate(R.layout.bottom_sheet_checkout_options, null)

            bottomDialogue?.setContentView(bottomSheetInflater)
            bottomDialogue?.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Used When back pressed in ImageViewPager
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun setActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar

        // Set the property on back button of action bar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.back_button)

        // Hides the app name or title in actionbar
        actionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Back to previous Fragment
                activity?.onBackPressed()
                return true
            }
            R.id.action_bar_itemdetail_like -> {
                // TODO: Add the item to like cart
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelectedListener(bundle: Bundle) {
        setFragmentResult("itemSlideImage", bundle)

        exitTransition = MaterialElevationScale(false).apply {
            duration = 300L
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300L
        }
    }
}