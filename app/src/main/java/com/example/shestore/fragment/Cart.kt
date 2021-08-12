package com.example.shestore.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.shestore.Adapter.CartAdapter
import com.example.shestore.Checkout
import com.example.shestore.Model.QuantitySizeModel
import com.example.shestore.R
import com.example.shestore.ViewModel.CartViewModel

class Cart : Fragment() {

    private lateinit  var cartrv : RecyclerView
    private lateinit var cartToolbar : Toolbar
    private lateinit var cartCheckout: Button
    private lateinit var cartVM: CartViewModel
    private lateinit var lottie_loading: LinearLayout
    private lateinit var lottie_error: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.add_cart)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_cart, container, false)

        cartVM = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        setViews(view)
        setActionbar()

        return view
    }

    override fun onStart() {
        super.onStart()
        // Set's Recyclerview
        cartVM.loadCartItems().observe(this) {
            // TODO Find Better Implementation in future
            cartVM.getSizeAndQuantity().observe(this){ SQData ->
                val dataMap = HashMap<Int, QuantitySizeModel>()
                for (item in SQData) {
                    dataMap.put(item.product_id, QuantitySizeModel(item.size, item.quantity))
                }
                cartrv.adapter = CartAdapter(it, dataMap)
                cartrv.layoutManager = LinearLayoutManager(context)
            }
        }

        cartVM.isDataLoading().observe(this) {
            if (it) {
                cartrv.visibility = View.INVISIBLE
                lottie_loading.visibility = View.VISIBLE
            } else {
                cartrv.visibility = View.VISIBLE
                lottie_loading.visibility = View.INVISIBLE
            }
        }

        cartVM.getError().observe(this){
            cartrv.visibility = View.INVISIBLE
            lottie_error.visibility = View.VISIBLE
            val error: TextView = lottie_error.findViewById(R.id.error_message)
            error.text = it
        }

        cartCheckout.setOnClickListener {
            val intent : Intent = Intent(requireContext(), Checkout::class.java)
            startActivity(intent)
        }
    }

    private fun setViews(view: View) {
        cartrv = view.findViewById(R.id.cart_rv)
        cartToolbar = view.findViewById(R.id.cart_toolbar)
        cartCheckout = view.findViewById(R.id.cart_checkout)
        lottie_loading = view.findViewById(R.id.cart_loading)
        lottie_error = view.findViewById(R.id.cart_error)
    }

    private fun setActionbar() {
        (context as AppCompatActivity).setSupportActionBar(cartToolbar)
        val actionBar : ActionBar? = (context as AppCompatActivity).supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.close_icon)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}