package com.mukeshsolanki.countrypickerexample

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.mukesh.countrypicker.Country
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.listeners.OnCountryPickerListener
import java.util.*

class MainActivity : AppCompatActivity(), OnCountryPickerListener {
    private var pickCountryButton: Button? = null
    private var findByNameButton: Button? = null
    private var findBySimButton: Button? = null
    private var findByLocaleButton: Button? = null
    private var findByIsoButton: Button? = null
    private lateinit var countryPicker: CountryPicker
    private var themeSwitch: SwitchCompat? = null
    private var styleSwitch: SwitchCompat? = null
    private var useBottomSheet: SwitchCompat? = null
    private var searchSwitch: SwitchCompat? = null
    private var sortByRadioGroup: RadioGroup? = null
    private var sortBy = CountryPicker.SORT_BY_NONE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setListener()
    }

    private fun setListener() {
        findByNameButton!!.setOnClickListener { findByName() }
        findBySimButton!!.setOnClickListener { findBySim() }
        findByLocaleButton!!.setOnClickListener { findByLocale() }
        findByIsoButton!!.setOnClickListener { findByIson() }
        sortByRadioGroup!!.setOnCheckedChangeListener { radioGroup, id ->
            sortBy = when (id) {
                R.id.none_radio_button -> CountryPicker.SORT_BY_NONE
                R.id.name_radio_button -> CountryPicker.SORT_BY_NAME
                R.id.dial_code_radio_button -> CountryPicker.SORT_BY_DIAL_CODE
                R.id.iso_radio_button -> CountryPicker.SORT_BY_ISO
                else -> CountryPicker.SORT_BY_NONE
            }
        }
        pickCountryButton!!.setOnClickListener { showPicker() }
    }

    private fun findByIson() {
        countryPicker = CountryPicker.Builder().with(this@MainActivity)
                .listener(this@MainActivity).build()
        val builder = AlertDialog.Builder(this, R.style.Base_Theme_MaterialComponents_Dialog_Alert)
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setTitle("Country Code")
        builder.setView(input)
        builder.setCancelable(false)
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            val country = countryPicker.getCountryByISO(input.text.toString())
            if (country == null) {
                Toast.makeText(this@MainActivity, "Country not found", Toast.LENGTH_SHORT).show()
            } else {
                showResultActivity(country)
            }
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    private fun findByLocale() {
        countryPicker = CountryPicker.Builder().with(this@MainActivity)
                .listener(this@MainActivity).build()
        val country = countryPicker.getCountryByLocale(Locale.getDefault())
        if (country == null) {
            Toast.makeText(this@MainActivity, "Country not found", Toast.LENGTH_SHORT).show()
        } else {
            showResultActivity(country)
        }
    }

    private fun findBySim() {
        countryPicker = CountryPicker.Builder().with(this@MainActivity)
                .listener(this@MainActivity).build()
        val country = countryPicker.getCountryFromSIM()
        if (country == null) {
            Toast.makeText(this@MainActivity, "Country not found", Toast.LENGTH_SHORT).show()
        } else {
            showResultActivity(country)
        }
    }

    private fun findByName() {
        countryPicker = CountryPicker.Builder().with(this@MainActivity)
                .listener(this@MainActivity).build()
        val builder = AlertDialog.Builder(this, R.style.Base_Theme_MaterialComponents_Dialog_Alert)
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setTitle("Country Name")
        builder.setView(input)
        builder.setCancelable(false)
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            val country = countryPicker.getCountryByName(input.text.toString())
            if (country == null) {
                Toast.makeText(this@MainActivity, "Country not found", Toast.LENGTH_SHORT).show()
            } else {
                showResultActivity(country)
            }
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    private fun showResultActivity(country: Country?) {
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        intent.putExtra(ResultActivity.Companion.BUNDLE_KEY_COUNTRY_NAME, country!!.name)
        intent.putExtra(ResultActivity.Companion.BUNDLE_KEY_COUNTRY_CURRENCY, country.currency)
        intent.putExtra(ResultActivity.Companion.BUNDLE_KEY_COUNTRY_DIAL_CODE, country.dialCode)
        intent.putExtra(ResultActivity.Companion.BUNDLE_KEY_COUNTRY_ISO, country.getCode())
        intent.putExtra(ResultActivity.Companion.BUNDLE_KEY_COUNTRY_FLAG_IMAGE, country.flag)
        startActivity(intent)
    }

    private fun showPicker() {
        val builder = CountryPicker.Builder().with(this@MainActivity)
                .listener(this@MainActivity)
        if (styleSwitch!!.isChecked) {
            builder.style(R.style.CountryPickerStyle)
        }
        builder.theme(if (themeSwitch!!.isChecked) CountryPicker.THEME_NEW else CountryPicker.THEME_OLD)
        builder.canSearch(searchSwitch!!.isChecked)
        builder.sortBy(sortBy)
        countryPicker = builder.build()
        if (useBottomSheet!!.isChecked) {
            countryPicker.showBottomSheet(this@MainActivity)
        } else {
            countryPicker.showDialog(this@MainActivity)
        }
    }

    private fun initialize() {
        pickCountryButton = findViewById(R.id.country_picker_button)
        themeSwitch = findViewById(R.id.theme_toggle_switch)
        styleSwitch = findViewById(R.id.custom_style_toggle_switch)
        useBottomSheet = findViewById(R.id.bottom_sheet_switch)
        searchSwitch = findViewById(R.id.search_switch)
        sortByRadioGroup = findViewById(R.id.sort_by_radio_group)
        findByNameButton = findViewById(R.id.by_name_button)
        findBySimButton = findViewById(R.id.by_sim_button)
        findByLocaleButton = findViewById(R.id.by_local_button)
        findByIsoButton = findViewById(R.id.by_iso_button)
    }

    override fun onSelectCountry(country: Country?) {
        showResultActivity(country)
    }
}