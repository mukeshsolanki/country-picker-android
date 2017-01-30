package com.mukesh.countrypicker.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.mukesh.countrypicker.R;
import com.mukesh.countrypicker.adapters.CountryListAdapter;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;
import com.mukesh.countrypicker.models.Country;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by mukesh on 25/04/16.
 */
public class CountryPicker extends DialogFragment {

  private EditText searchEditText;
  private ListView countryListView;
  private CountryListAdapter adapter;
  private List<Country> countriesList = new ArrayList<>();
  private List<Country> selectedCountriesList = new ArrayList<>();
  private CountryPickerListener listener;
  private Context context;

  public void setListener(CountryPickerListener listener) {
    this.listener = listener;
  }

  public EditText getSearchEditText() {
    return searchEditText;
  }

  public ListView getCountryListView() {
    return countryListView;
  }

  public static Currency getCurrencyCode(String countryCode) {
    try {
      return Currency.getInstance(new Locale("en", countryCode));
    } catch (Exception ignored) {
    }
    return null;
  }


  public void setCountriesList(List<Country> newCountries) {
    this.countriesList.clear();
    this.countriesList.addAll(newCountries);
  }

  /**
   * To support show as dialog
   */
  public static CountryPicker newInstance(String dialogTitle) {
    CountryPicker picker = new CountryPicker();
    Bundle bundle = new Bundle();
    bundle.putString("dialogTitle", dialogTitle);
    picker.setArguments(bundle);
    return picker;
  }

  public CountryPicker() {
    setCountriesList(Country.getAllCountries());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.country_picker, null);
    Bundle args = getArguments();
    if (args != null) {
      String dialogTitle = args.getString("dialogTitle");
      getDialog().setTitle(dialogTitle);

      int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
      int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
      getDialog().getWindow().setLayout(width, height);
    }
    searchEditText = (EditText) view.findViewById(R.id.country_code_picker_search);
    countryListView = (ListView) view.findViewById(R.id.country_code_picker_listview);

    selectedCountriesList = new ArrayList<>(countriesList.size());
    selectedCountriesList.addAll(countriesList);

    adapter = new CountryListAdapter(getActivity(), selectedCountriesList);
    countryListView.setAdapter(adapter);

    countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
          Country country = selectedCountriesList.get(position);
          listener.onSelectCountry(country.getName(), country.getCode(), country.getDialCode(),
              country.getFlag());
        }
      }
    });

    searchEditText.addTextChangedListener(new TextWatcher() {

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        search(s.toString());
      }
    });

    return view;
  }

  @SuppressLint("DefaultLocale")
  private void search(String text) {
    selectedCountriesList.clear();
    for (Country country : countriesList) {
      if (country.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
        selectedCountriesList.add(country);
      }
    }
    adapter.notifyDataSetChanged();
  }

  public Country getUserCountryInfo(Context context) {
    this.context = context;
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if (!(telephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT)) {
      return getCountry(telephonyManager.getSimCountryIso());
    }
    return null;
  }

  public Country getCountryByLocale(Context context, Locale locale) {
    this.context = context;
    String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
    return getCountry(countryIsoCode);
  }

  public Country getCountryByName(Context context, String countryName) {
    this.context = context;
    Map<String, String> countries = new HashMap<>();
    for (String iso : Locale.getISOCountries()) {
      Locale l = new Locale("", iso);
      countries.put(l.getDisplayCountry(), iso);
    }

    String countryIsoCode = countries.get(countryName);
    if (countryIsoCode != null) {
      return getCountry(countryIsoCode);
    }
    return null;
  }

  private Country getCountry(String countryIsoCode) {
    for (int i = 0; i < Country.getAllCountries().size(); i++) {
      Country country = Country.getAllCountries().get(i);
      if (country.getCode().equalsIgnoreCase(countryIsoCode)) {
        country.setFlag(getFlagResId(country.getCode()));       // TODO this should be moved, the function should be pure
        return country;
      }
    }
    return null;
  }

  private int getFlagResId(String drawable) {
    try {
      return context.getResources()
          .getIdentifier("flag_" + drawable.toLowerCase(Locale.ENGLISH), "drawable",
              context.getPackageName());
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
}
