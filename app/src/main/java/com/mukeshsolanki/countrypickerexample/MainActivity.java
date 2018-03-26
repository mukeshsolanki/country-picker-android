package com.mukeshsolanki.countrypickerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements CountryPickerListener {

  private TextView mCountryNameTextView, mCountryIsoCodeTextView, mCountryDialCodeTextView,
      mSelectedCountryCurrency;
  private ImageView mCountryFlagImageView;
  private Button mPickCountryButton;
  private CountryPicker mCountryPicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initialize();
    setListener();
  }

  private void setListener() {
    mPickCountryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCountryPicker.showDialog(getSupportFragmentManager());
      }
    });
    getUserCountryInfo();
  }

  private void initialize() {
    mCountryNameTextView = findViewById(R.id.selected_country_name_text_view);
    mCountryIsoCodeTextView = findViewById(R.id.selected_country_iso_text_view);
    mCountryDialCodeTextView = findViewById(R.id.selected_country_dial_code_text_view);
    mPickCountryButton = findViewById(R.id.country_picker_button);
    mCountryFlagImageView = findViewById(R.id.selected_country_flag_image_view);
    mSelectedCountryCurrency = findViewById(R.id.selected_country_currency);
    mCountryPicker =
        new CountryPicker.Builder().with(this).sortBy(CountryPicker.SORT_BY_DIAL_CODE).build();
    //mCountryPicker = CountryPicker.buolder("Select Country");
    //// You can limit the displayed countries
    //ArrayList<Country> nc = new ArrayList<>();
    //for (Country c : Country.getAllCountries()) {
    //  if (c.getDialCode().endsWith("0")) {
    //    nc.add(c);
    //  }
    //}
    //// and decide, in which order they will be displayed
    //Collections.reverse(nc);
    //mCountryPicker.setCountriesList(nc);
  }

  private void getUserCountryInfo() {
    //Country country = Country.getCountryFromSIM(getApplicationContext());
    //if (country != null) {
    //  mCountryFlagImageView.setImageResource(country.getFlag());
    //  mCountryDialCodeTextView.setText(country.getDialCode());
    //  mCountryIsoCodeTextView.setText(country.getCode());
    //  mCountryNameTextView.setText(country.getName());
    //}
  }

  @Override
  public void onSelectCountry(Country country) {
    mCountryFlagImageView.setImageResource(country.getFlag());
    mCountryDialCodeTextView.setText(country.getDialCode());
    mCountryIsoCodeTextView.setText(country.getCode());
    mCountryNameTextView.setText(country.getName());
    mSelectedCountryCurrency.setText(country.getCurrency());
  }
}