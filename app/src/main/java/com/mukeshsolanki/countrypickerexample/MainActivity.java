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
  }

  private void initialize() {
    mCountryNameTextView = findViewById(R.id.selected_country_name_text_view);
    mCountryIsoCodeTextView = findViewById(R.id.selected_country_iso_text_view);
    mCountryDialCodeTextView = findViewById(R.id.selected_country_dial_code_text_view);
    mPickCountryButton = findViewById(R.id.country_picker_button);
    mCountryFlagImageView = findViewById(R.id.selected_country_flag_image_view);
    mSelectedCountryCurrency = findViewById(R.id.selected_country_currency);
    mCountryPicker =
        new CountryPicker.Builder().with(this)
            .listener(this)
            .sortBy(CountryPicker.SORT_BY_DIAL_CODE)
            .build();
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