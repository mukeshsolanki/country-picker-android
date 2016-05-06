package com.mukeshsolanki.countrypickerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mukesh.countrypicker.fragments.CountryPicker;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;

public class MainActivity extends AppCompatActivity {
  private Button mPickCountry;
  private TextView mCountryName, mCountryIso, mCountryDialCode;
  private ImageView mFlag;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mPickCountry = (Button) findViewById(R.id.pick_country_button);
    mCountryName = (TextView) findViewById(R.id.country_name_text_view);
    mCountryIso = (TextView) findViewById(R.id.country_iso_text_view);
    mCountryDialCode = (TextView) findViewById(R.id.country_dial_code_text_view);
    mFlag = (ImageView) findViewById(R.id.country_flag_image_view);
    final CountryPicker picker = CountryPicker.newInstance("Select Country");
    mPickCountry.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
      }
    });
    picker.setListener(new CountryPickerListener() {
      @Override public void onSelectCountry(String name, String code, String dialCode,
          int flagDrawableResID) {
        mCountryName.setText(name);
        mCountryIso.setText(code);
        mCountryDialCode.setText(dialCode);
        mFlag.setImageResource(flagDrawableResID);
        picker.dismiss();
      }
    });

    Log.d("User Info=>", picker.getUserCountryInfo(this).getName());
  }
}
