package com.mukesh.countrypicker.listeners

import android.view.View

interface BottomSheetInteractionListener {
    fun initiateUi(view: View)
    fun setCustomStyle(view: View)
    fun setSearchEditText()
    fun setupRecyclerView(view: View)
}