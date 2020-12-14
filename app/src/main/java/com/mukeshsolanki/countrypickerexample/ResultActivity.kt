package com.mukeshsolanki.countrypickerexample

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private var countryNameTextView: TextView? = null
    private var countryIsoCodeTextView: TextView? = null
    private var countryDialCodeTextView: TextView? = null
    private var selectedCountryCurrency: TextView? = null
    private var countryFlagImageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initialize()
        showData()
    }

    private fun showData() {
        val bundle = intent.extras
        if (bundle != null) {
            countryNameTextView!!.text = bundle.getString(BUNDLE_KEY_COUNTRY_NAME)
            countryIsoCodeTextView!!.text = bundle.getString(BUNDLE_KEY_COUNTRY_ISO)
            countryDialCodeTextView!!.text = bundle.getString(BUNDLE_KEY_COUNTRY_DIAL_CODE)
            selectedCountryCurrency!!.text = bundle.getString(BUNDLE_KEY_COUNTRY_CURRENCY)
            countryFlagImageView!!.setImageResource(bundle.getInt(BUNDLE_KEY_COUNTRY_FLAG_IMAGE))
        }
    }

    private fun initialize() {
        countryNameTextView = findViewById(R.id.selected_country_name_text_view)
        countryIsoCodeTextView = findViewById(R.id.selected_country_iso_text_view)
        countryDialCodeTextView = findViewById(R.id.selected_country_dial_code_text_view)
        countryFlagImageView = findViewById(R.id.selected_country_flag_image_view)
        selectedCountryCurrency = findViewById(R.id.selected_country_currency)
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener { finish() }
    }

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "country_name"
        const val BUNDLE_KEY_COUNTRY_ISO = "country_iso"
        const val BUNDLE_KEY_COUNTRY_DIAL_CODE = "dial_code"
        const val BUNDLE_KEY_COUNTRY_CURRENCY = "currency"
        const val BUNDLE_KEY_COUNTRY_FLAG_IMAGE = "flag_image"
    }
}