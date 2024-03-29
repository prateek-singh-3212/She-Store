package com.example.shestore.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.shestore.Adapter.ImageSliderAdapter
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Database.Entity.WishlistEntity
import com.example.shestore.Interface.FeedbackType
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.R
import com.example.shestore.Utility.Constants
import com.example.shestore.Utility.HtmlParser
import com.example.shestore.Utility.RoundImageviewCorner
import com.example.shestore.ViewModel.ItemDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

class ItemDetail : Fragment() {

    private lateinit var buyNow: Button
    private lateinit var toolbar: Toolbar
    private lateinit var itemName: TextView
    private lateinit var itemSubname: TextView
    private lateinit var itemPrice: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var itemDetailVM: ItemDetailViewModel
    private lateinit var description: TextView
    private lateinit var tagsChipGroup: ChipGroup
    private lateinit var ratingBar: RatingBar
    private lateinit var headerRating: TextView
    private lateinit var itemDetailContainer: ConstraintLayout

    // BottomSheet View.
    private lateinit var bsItemImage: ImageView
    private lateinit var bsItemSubname: TextView
    private lateinit var bsItemName: TextView
    private lateinit var bsPrice: TextView
    private lateinit var bsSizeChip: ChipGroup
    private lateinit var bsAddQuantity: ImageButton
    private lateinit var bsRemoveQuantity: ImageButton
    private lateinit var bsQuantity: TextView
    private lateinit var bsAddToCart: Button
    private lateinit var bsCheckout: Button

    private val mainScope: CoroutineScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** To display the actionbar in fragment it is necessary to set this true.
         * else actionbar menu will not be displayed
         */
        setHasOptionsMenu(true)

        /**
         * To set the enter container transformation we use this. We are not required to set the exit transition.
         * because material library will handle on its own but to be safer side you can use sharedElementReturnTransition
         * */

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = Constants.TRANSITION_TIME
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.WHITE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_item_detail, container, false)
        setViews(view)
        setActionBar()

        itemDetailVM = ViewModelProvider(requireActivity()).get(ItemDetailViewModel::class.java)

        Log.d(
            "FragmentTransition",
            "Fragment: $parentFragmentManager ItemDataViewModel: $itemDetailVM"
        )

        itemDetailVM.getItemDetail().observe(requireActivity()) {
            setDataInFragment(it)
        }

        itemDetailVM.getTransitionName().observe(requireActivity()) {
            itemDetailContainer.transitionName = it
        }

        return view
    }

    private fun setDataInFragment(data: WooCommerceItemsDetail) {
        // Header
        itemName.text = data.name
        itemPrice.text = HtmlParser.htmlToSpannedString(data.price_html)
        itemSubname.text = HtmlParser.htmlToSpannedString(data.short_description).toString()
        headerRating.text = String.format("%s ★", data.average_rating)

        // Description
        description.text = HtmlParser.htmlToSpannedString(data.description)

        // Tags
        mainScope.launch {
            for (tag in data.tags) {
                val chip = Chip(requireContext())
                chip.text = tag.name
                chip.setTextColor(ResourcesCompat.getColor(resources, R.color.font_main, null))
                chip.setChipBackgroundColorResource(R.color.pink_primary)
                tagsChipGroup.addView(chip)
            }
        }

        // Images In Slide Show
        mainScope.launch {
            val imageURL = mutableListOf<String>()

            for (image in data.images) {
                imageURL.add(image.src)
            }

            viewPager.adapter =
                ImageSliderAdapter(requireActivity(), imageURL, object : ItemData {
                    override fun onItemSelectedListener(bundle: Bundle) {
                        setFragmentResult(Constants.KEY_ITEM_SLIDE_IMAGE_FRAGMENT_DATA, bundle)

                        exitTransition = MaterialElevationScale(false).apply {
                            duration = Constants.TRANSITION_TIME
                        }
                        reenterTransition = MaterialElevationScale(true).apply {
                            duration = Constants.TRANSITION_TIME
                        }
                    }

                })

            // synchronize the tabLayout with viewpager
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = "${(position + 1)}"
            }.attach()
        }

        // Reviews
        ratingBar.rating = data.average_rating.toFloat()
    }

    private fun setViews(view: View) {
        buyNow = view.findViewById(R.id.itemDetail_buynow)
        toolbar = view.findViewById(R.id.itemDetail_actionbar)
        itemDetailContainer = view.findViewById(R.id.itemDetail_continer)

        // Header
        itemName = view.findViewById(R.id.cardview_itemDetail_title)
        itemPrice = view.findViewById(R.id.cardview_itemDetail_price)
        itemSubname = view.findViewById(R.id.cardview_itemDetail_subname)
        viewPager = view.findViewById(R.id.cardview_itemDetail_viewpager)
        tabLayout = view.findViewById(R.id.cardview_itemDetail_tablayout)
        headerRating = view.findViewById(R.id.cardview_itemDetail_itemRateing)


        //Description
        description = view.findViewById(R.id.itemDetail_product_description)

        //Tags
        tagsChipGroup = view.findViewById(R.id.itemDetail_tags_chipgroup)

        //Rating
        ratingBar = view.findViewById(R.id.itemDetail_ratingBar)
    }


    override fun onStart() {
        super.onStart()

        buyNow.setOnClickListener {
            setUpBottomSheet()
        }
    }

    private fun setUpBottomSheet() {
        val bottomDialogue: BottomSheetDialog? =
            context?.let { it1 -> BottomSheetDialog(it1, R.style.BottomSheetDialogeTheme) }
        val bottomSheetInflater: View = LayoutInflater.from(activity?.applicationContext)
            .inflate(R.layout.bottom_sheet_checkout_options, null)

        setBottomSheetViews(bottomSheetInflater)

        itemDetailVM.getItemDetail().observe(requireActivity()) {
            setDataInBottomSheet(it)
            itemDetailVM.checkCartStatus(bottomSheetInflater.context, it.id).observe(this) {
                if (it == 0) {
                    bsAddToCart.text = getString(R.string.add_to_cart)
                } else {
                    bsAddToCart.text = getString(R.string.added_to_cart)
                }
            }
        }

        bsAddQuantity.setOnClickListener {
            val currentQuantity: Int = bsQuantity.text.toString().toInt()
            bsQuantity.text = (currentQuantity + 1).toString()
        }

        bsRemoveQuantity.setOnClickListener {
            val currentQuantity: Int = bsQuantity.text.toString().toInt()
            if (currentQuantity <= 1) {
                bsQuantity.text = "1"
            } else {
                bsQuantity.text = (currentQuantity - 1).toString()
            }
        }

        bsAddToCart.setOnClickListener {
            //Implemented Add to cart functionality
            var selectedChipText : String? = null

            // TODO : WARNING!!! NEED TO CHANGE IN FUTURE. GET DATA FROM DATABSE AND CHECK THE STATUS
            val checkButtonText : Boolean = bsAddToCart.text.toString()
                    .lowercase(Locale.getDefault())
                    .equals(getString(R.string.add_to_cart).lowercase(Locale.getDefault()))

            if (bsSizeChip.visibility != View.GONE && bsSizeChip.checkedChipId == View.NO_ID && checkButtonText) {
                Snackbar.make(bottomSheetInflater, "Select Size", Snackbar.LENGTH_SHORT).apply {
                    anchorView = bottomSheetInflater
                    animationMode = Snackbar.ANIMATION_MODE_SLIDE
                    setBackgroundTint(ResourcesCompat.getColor(resources,R.color.pink_200, null))
                    setTextColor(ResourcesCompat.getColor(resources,R.color.font_main, null))
                }.show()
                return@setOnClickListener
            }

            if (bsSizeChip.visibility != View.GONE && checkButtonText) {
                selectedChipText = bsSizeChip.findViewById<Chip>(bsSizeChip.checkedChipId).text.toString()
            }

            itemDetailVM.getItemDetail().observe(this) {
                val data = CartEntity(
                    it.id,
                    it.name,
                    System.currentTimeMillis(),
                    it.status,
                    bsQuantity.text.toString().toInt(),
                    selectedChipText ?: "NR",
                    it.permalink
                )
                itemDetailVM.addToCart(requireContext(), data)
            }
        }


        bsCheckout.setOnClickListener {
            //TODO: Implement Checkout functionality
        }

        bottomDialogue?.setContentView(bottomSheetInflater)
        bottomDialogue?.show()
    }

    private fun setBottomSheetViews(view: View) {
        bsItemImage = view.findViewById(R.id.bottom_sheet_image)
        bsItemName = view.findViewById(R.id.bottom_sheet_item_name)
        bsItemSubname = view.findViewById(R.id.bottom_sheet_item_subname)
        bsPrice = view.findViewById(R.id.bottom_sheet_item_price)
        bsSizeChip = view.findViewById(R.id.bottom_sheet_size_selector)
        bsAddQuantity = view.findViewById(R.id.bottom_sheet_additem)
        bsRemoveQuantity = view.findViewById(R.id.bottom_sheet_removeitem)
        bsQuantity = view.findViewById(R.id.bottom_sheet_item_quantity)
        bsAddToCart = view.findViewById(R.id.bottom_sheet_add_to_cart)
        bsCheckout = view.findViewById(R.id.bottom_sheet_checkout)
    }


    @SuppressLint("ResourceType")
    private fun setDataInBottomSheet(data: WooCommerceItemsDetail) {

        Picasso.get().load(data.images[0].src).placeholder(R.drawable.close_icon)
            .error(R.drawable.ic_launcher_background).transform(RoundImageviewCorner(20, 0))
            .into(bsItemImage)

        bsItemName.text = data.name
        bsPrice.text = HtmlParser.htmlToSpannedString(data.price_html)
        bsItemSubname.text = HtmlParser.htmlToSpannedString(data.short_description).toString()

        // Don' add any other operation below this. If doesn't contains  the set it invisible
        if (data.attributes.isEmpty()) {
            bsSizeChip.visibility = View.GONE
            return
        }

        if (!data.attributes[0].name.equals(Constants.ATTRIBUTE_SIZE)) {
            bsSizeChip.visibility = View.GONE
            return
        }

        mainScope.launch {
            for (size in data.attributes[0].options) {
                val chip = Chip(requireContext())
                chip.text = size
                chip.setTextColor(ResourcesCompat.getColor(resources, R.color.font_main, null))
                chip.isCheckable = true
                chip.isClickable = true
                chip.setChipBackgroundColorResource(R.drawable.chip_background)
                bsSizeChip.addView(chip)
            }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_itemdetail, menu)

        /** Listen and updates the icon of wishlist*/
        itemDetailVM.getItemDetail().observe(this) { data ->
            itemDetailVM.checkWishlistStatus(requireContext(), data.id).observe(this) {
                if (it == 0) {
                    menu.getItem(0).icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.favorite_border, null)
                } else {
                    menu.getItem(0).icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_heart, null)
                }
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Back to previous Fragment
                activity?.onBackPressed()
                return true
            }
            R.id.action_bar_itemdetail_like -> {
                itemDetailVM.getItemDetail().observe(this) {
                    val data = WishlistEntity(
                        it.id,
                        it.name,
                        System.currentTimeMillis(),
                        it.status,
                        it.permalink
                    )

                    itemDetailVM.addToWishlist(this.requireContext(), data)

                }
                // Showing SnackBar
                itemDetailVM.getFeedback().observe(this) {

                    // If message is not of given type then return
                    if (!it.containsKey(FeedbackType.WISHLIST)) {
                        return@observe
                    }

                    Snackbar.make(requireView(), it.get(FeedbackType.WISHLIST)!!, Snackbar.LENGTH_SHORT)
                        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                        .setAnchorView(buyNow)
                        .show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        /** Cancel the coroutine scope whenever the activity destroys else it show context not found when clicked on item 2nd time*/
        mainScope.cancel("ItemDetail Fragment Closed")
    }
}
