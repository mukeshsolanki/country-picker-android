package com.mukeshsolanki.countrypickerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;

public class MainActivity extends AppCompatActivity implements OnCountryPickerListener {

  private TextView countryNameTextView, countryIsoCodeTextView, countryDialCodeTextView,
      selectedCountryCurrency;
  private ImageView countryFlagImageView;
  private Button pickCountryButton;
  private CountryPicker countryPicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initialize();
    setListener();
  }

  private void setListener() {
    pickCountryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        countryPicker.showDialog(getSupportFragmentManager());
      }
    });
  }

  private void initialize() {
    countryNameTextView = findViewById(R.id.selected_country_name_text_view);
    countryIsoCodeTextView = findViewById(R.id.selected_country_iso_text_view);
    countryDialCodeTextView = findViewById(R.id.selected_country_dial_code_text_view);
    pickCountryButton = findViewById(R.id.country_picker_button);
    countryFlagImageView = findViewById(R.id.selected_country_flag_image_view);
    selectedCountryCurrency = findViewById(R.id.selected_country_currency);
    countryPicker =
        new CountryPicker.Builder().with(this)
            .listener(this)
            .build();
  }

  @Override
  public void onSelectCountry(Country country) {
    countryFlagImageView.setImageResource(country.getFlag());
    countryDialCodeTextView.setText(country.getDialCode());
    countryIsoCodeTextView.setText(country.getCode());
    countryNameTextView.setText(country.getName());
    selectedCountryCurrency.setText(country.getCurrency());
  }
}