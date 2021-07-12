package com.example.shestore.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.example.shestore.R
import com.google.android.material.transition.MaterialContainerTransform

class AllCategories : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Sets the enter transition for the fragment. Transition used ( nav_to_fragment.xml)
         * */
        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.nav_to_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_categories, container, false)
    }
}