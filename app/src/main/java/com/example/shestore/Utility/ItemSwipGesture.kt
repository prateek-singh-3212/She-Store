package com.example.shestore.Utility

import android.content.Context
import android.graphics.Canvas
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * This callback is used when the user swiped the item to right to add item to wishlist.
 * TODO: Add spring effect to the card which is translating
 * */

class ItemSwipGesture(val context : Context, val drags : Int, val swipeDirection : Int) : ItemTouchHelper.SimpleCallback(drags, swipeDirection) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Toast.makeText(context, "Added ", Toast.LENGTH_SHORT).show()
    }

    /**
     * Added the canvas which shows when user swipes to any direction
     * TODO: Add canvas for left and right direction
     * */

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}