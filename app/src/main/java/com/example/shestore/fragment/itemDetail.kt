package com.example.shestore.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.shestore.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item_detail.*

class itemDetail : Fragment() {

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var subName: TextView
    private lateinit var buyNow: Button
    private lateinit var toolbar: Toolbar
    private val TAG = "itemDetail"
    private var transition: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** To display the actionbar in fragment it is nessary to set this true.
        else actionbar menu will not be displayed */
        setHasOptionsMenu(true)

        Log.d(TAG, "onCreate: ${transition}")

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300L
            drawingViewId = R.id.itemDetail_continer
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(resources.getColor(R.color.white))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: Attached...")
        setFragmentResultListener("itemDetails") { requestKey, bundle ->
            Picasso.get().load(
                bundle.getString("imageURL")
                    ?: "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14175098/2021/6/29/9c6db6de-33df-4266-8830-b6528d5611861624949166875-ALDO-Dark-Green-Solid-Structured-Satchel-5961624949166180-1.jpg"
            ).into(image)
            Log.d(TAG, "onAttach: Listening...")
            title.text = bundle.getString("itemName") ?: "ABC"
            subName.text = bundle.getString("itemSubName") ?: "DEF"
            itemDetail_continer.transitionName = bundle.getString("transitionName") ?: "abc"
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
        image = view.findViewById(R.id.itemDetail_imageview)
        title = view.findViewById(R.id.itemDetail_title)
        subName = view.findViewById(R.id.itemDetail_subname)
        buyNow = view.findViewById(R.id.itemDetail_buynow)
        toolbar = view.findViewById(R.id.itemDetail_actionbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_itemdetail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        buyNow.setOnClickListener {
            val bottomDialogue : BottomSheetDialog? = context?.let { it1 -> BottomSheetDialog(it1, R.style.BottomSheetDialogeTheme) }
            val bottomSheetInflater : View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.bottom_sheet_checkout_options, null)

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