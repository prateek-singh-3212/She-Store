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
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.ItemDetailAdapter
import com.example.shestore.Model.itemList
import com.example.shestore.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item.view.*
import kotlinx.android.synthetic.main.fragment_item_detail.*

class ItemDetail : Fragment() {

    private lateinit var buyNow: Button
    private lateinit var toolbar: Toolbar
    private lateinit var itemName: TextView
    private lateinit var itemSubname: TextView
    private lateinit var itemPrice: TextView
    private lateinit var itemImage: ImageView

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
            Picasso.get().load(bundle.getString("imageURL")?.toUri()).into(itemImage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_item_detail, container, false)
        setViews(view)
        setActionBar()

        return view
    }

    private fun setViews(view: View) {
        buyNow = view.findViewById(R.id.itemDetail_buynow)
        toolbar = view.findViewById(R.id.itemDetail_actionbar)
        itemName = view.findViewById(R.id.cardview_itemDetail_title)
        itemPrice = view.findViewById(R.id.cardview_itemDetail_price)
        itemSubname = view.findViewById(R.id.cardview_itemDetail_subname)
        itemImage = view.findViewById(R.id.cardview_itemDetail_imageview)
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
}