package com.mukeshsolanki.countrypickerexample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
  public static final String BUNDLE_KEY_COUNTRY_NAME = "country_name";
  public static final String BUNDLE_KEY_COUNTRY_ISO = "country_iso";
  public static final String BUNDLE_KEY_COUNTRY_DIAL_CODE = "dial_code";
  public static final String BUNDLE_KEY_COUNTRY_CURRENCY = "currency";
  public static final String BUNDLE_KEY_COUNTRY_FLAG_IMAGE = "flag_image";

  private TextView countryNameTextView, countryIsoCodeTextView, countryDialCodeTextView,
      selectedCountryCurrency;
  private ImageView countryFlagImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    initialize();
    showData();
  }

  private void showData() {
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      countryNameTextView.setText(bundle.getString(BUNDLE_KEY_COUNTRY_NAME));
      countryIsoCodeTextView.setText(bundle.getString(BUNDLE_KEY_COUNTRY_ISO));
      countryDialCodeTextView.setText(bundle.getString(BUNDLE_KEY_COUNTRY_DIAL_CODE));
      selectedCountryCurrency.setText(bundle.getString(BUNDLE_KEY_COUNTRY_CURRENCY));
      countryFlagImageView.setImageResource(bundle.getInt(BUNDLE_KEY_COUNTRY_FLAG_IMAGE));
    }
  }

  private void initialize() {
    countryNameTextView = findViewById(R.id.selected_country_name_text_view);
    countryIsoCodeTextView = findViewById(R.id.selected_country_iso_text_view);
    countryDialCodeTextView = findViewById(R.id.selected_country_dial_code_text_view);
    countryFlagImageView = findViewById(R.id.selected_country_flag_image_view);
    selectedCountryCurrency = findViewById(R.id.selected_country_currency);
    ImageButton backButton = findViewById(R.id.back_button);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        finish();
      }
    });
  }
}
