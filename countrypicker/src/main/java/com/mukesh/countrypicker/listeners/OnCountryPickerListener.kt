package com.mukesh.countrypicker.listeners

import com.mukesh.countrypicker.Country

interface OnCountryPickerListener {
    fun onSelectCountry(country: Country?)
}