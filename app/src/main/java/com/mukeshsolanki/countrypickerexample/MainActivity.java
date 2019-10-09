package com.mukeshsolanki.countrypickerexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.listeners.OnCountryPickerListener;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnCountryPickerListener {

  private Button pickCountryButton, findByNameButton, findBySimButton, findByLocaleButton,
      findByIsoButton;
  private CountryPicker countryPicker;
  private SwitchCompat themeSwitch, styleSwitch, useBottomSheet, searchSwitch;
  private RadioGroup sortByRadioGroup;
  private AppCompatEditText filteredIsoCodes;
  private int sortBy = CountryPicker.SORT_BY_NONE;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initialize();
    setListener();
  }

  private void setListener() {
    findByNameButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        findByName();
      }
    });
    findBySimButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        findBySim();
      }
    });
    findByLocaleButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        findByLocale();
      }
    });
    findByIsoButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        findByIson();
      }
    });
    sortByRadioGroup.setOnCheckedChangeListener(
        new RadioGroup.OnCheckedChangeListener() {
          @Override public void onCheckedChanged(RadioGroup radioGroup, int id) {
            switch (id) {
              case R.id.none_radio_button:
                sortBy = CountryPicker.SORT_BY_NONE;
                break;
              case R.id.name_radio_button:
                sortBy = CountryPicker.SORT_BY_NAME;
                break;
              case R.id.dial_code_radio_button:
                sortBy = CountryPicker.SORT_BY_DIAL_CODE;
                break;
              case R.id.iso_radio_button:
                sortBy = CountryPicker.SORT_BY_ISO;
                break;
              default:
                sortBy = CountryPicker.SORT_BY_NONE;
                break;
            }
          }
        });
    pickCountryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showPicker();
      }
    });
  }

  private void findByIson() {
    countryPicker =
        new CountryPicker.Builder().with(MainActivity.this)
            .listener(MainActivity.this).build();
    AlertDialog.Builder builder =
        new AlertDialog.Builder(this, R.style.Base_Theme_MaterialComponents_Dialog_Alert);
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    builder.setTitle("Country Code");
    builder.setView(input);
    builder.setCancelable(false);
    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Country country = countryPicker.getCountryByISO(input.getText().toString());
        if (country == null) {
          Toast.makeText(MainActivity.this, "Country not found", Toast.LENGTH_SHORT).show();
        } else {
          showResultActivity(country);
        }
      }
    });
    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.show();
  }

  private void findByLocale() {
    countryPicker =
        new CountryPicker.Builder().with(MainActivity.this)
            .listener(MainActivity.this).build();
    Country country = countryPicker.getCountryByLocale(Locale.getDefault());
    if (country == null) {
      Toast.makeText(MainActivity.this, "Country not found", Toast.LENGTH_SHORT).show();
    } else {
      showResultActivity(country);
    }
  }

  private void findBySim() {
    countryPicker =
        new CountryPicker.Builder().with(MainActivity.this)
            .listener(MainActivity.this).build();
    Country country = countryPicker.getCountryFromSIM();
    if (country == null) {
      Toast.makeText(MainActivity.this, "Country not found", Toast.LENGTH_SHORT).show();
    } else {
      showResultActivity(country);
    }
  }

  private void findByName() {
    countryPicker =
        new CountryPicker.Builder().with(MainActivity.this)
            .listener(MainActivity.this).build();
    AlertDialog.Builder builder =
        new AlertDialog.Builder(this, R.style.Base_Theme_MaterialComponents_Dialog_Alert);
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    builder.setTitle("Country Name");
    builder.setView(input);
    builder.setCancelable(false);
    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Country country = countryPicker.getCountryByName(input.getText().toString());
        if (country == null) {
          Toast.makeText(MainActivity.this, "Country not found", Toast.LENGTH_SHORT).show();
        } else {
          showResultActivity(country);
        }
      }
    });
    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.show();
  }

  private void showResultActivity(Country country) {
    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
    intent.putExtra(ResultActivity.BUNDLE_KEY_COUNTRY_NAME, country.getName());
    intent.putExtra(ResultActivity.BUNDLE_KEY_COUNTRY_CURRENCY, country.getCurrency());
    intent.putExtra(ResultActivity.BUNDLE_KEY_COUNTRY_DIAL_CODE, country.getDialCode());
    intent.putExtra(ResultActivity.BUNDLE_KEY_COUNTRY_ISO, country.getCode());
    intent.putExtra(ResultActivity.BUNDLE_KEY_COUNTRY_FLAG_IMAGE, country.getFlag());
    startActivity(intent);
  }

  private void showPicker() {
    CountryPicker.Builder builder =
        new CountryPicker.Builder().with(MainActivity.this)
            .listener(MainActivity.this);
    if (styleSwitch.isChecked()) {
      builder.style(R.style.CountryPickerStyle);
    }
    builder.theme(themeSwitch.isChecked() ? CountryPicker.THEME_NEW : CountryPicker.THEME_OLD);
    builder.canSearch(searchSwitch.isChecked());
    builder.sortBy(sortBy);
    if (filteredIsoCodes.getText() != null && !filteredIsoCodes.getText().toString().isEmpty()) {
      builder.isoCodesToInclude(Arrays.asList(filteredIsoCodes.getText().toString().split(",")));
    }
    countryPicker = builder.build();
    if (useBottomSheet.isChecked()) {
      countryPicker.showBottomSheet(MainActivity.this);
    } else {
      countryPicker.showDialog(MainActivity.this);
    }
  }

  private void initialize() {
    pickCountryButton = findViewById(R.id.country_picker_button);
    themeSwitch = findViewById(R.id.theme_toggle_switch);
    styleSwitch = findViewById(R.id.custom_style_toggle_switch);
    useBottomSheet = findViewById(R.id.bottom_sheet_switch);
    searchSwitch = findViewById(R.id.search_switch);
    sortByRadioGroup = findViewById(R.id.sort_by_radio_group);
    findByNameButton = findViewById(R.id.by_name_button);
    findBySimButton = findViewById(R.id.by_sim_button);
    findByLocaleButton = findViewById(R.id.by_local_button);
    findByIsoButton = findViewById(R.id.by_iso_button);
    filteredIsoCodes = findViewById(R.id.filter);
  }

  @Override
  public void onSelectCountry(Country country) {
    showResultActivity(country);
  }
}