package com.mukesh.countrypicker.listeners

import com.mukesh.countrypicker.Country

interface OnItemClickListener {
    fun onItemClicked(country: Country?)
}