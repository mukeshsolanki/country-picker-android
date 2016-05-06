package com.mukesh.countrypicker.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.mukesh.countrypicker.R;
import com.mukesh.countrypicker.adapters.CountryListAdapter;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;
import com.mukesh.countrypicker.models.Country;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mukesh on 25/04/16.
 */
public class CountryPicker extends DialogFragment implements Comparator<Country> {

  private EditText searchEditText;
  private ListView countryListView;
  private CountryListAdapter adapter;
  private List<Country> allCountriesList;
  private List<Country> selectedCountriesList;
  private CountryPickerListener listener;
  public static final String USER_COUNTRY_CODE = "country_code";
  public static final String USER_COUNTRY_FLAG = "flag_res_id";
  public static final String USER_COUNTRY_DIAL_CODE = "country_dial_code";

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

  private List<Country> getAllCountries() {
    if (allCountriesList == null) {
      try {
        allCountriesList = new ArrayList<Country>();

        String allCountriesCode = readEncodedJsonString(getActivity());

        JSONArray countrArray = new JSONArray(allCountriesCode);

        for (int i = 0; i < countrArray.length(); i++) {
          JSONObject jsonObject = countrArray.getJSONObject(i);
          String countryName = jsonObject.getString("name");
          String countryDialCode = jsonObject.getString("dial_code");
          String countryCode = jsonObject.getString("code");

          Country country = new Country();
          country.setCode(countryCode);
          country.setName(countryName);
          country.setDialCode(countryDialCode);
          allCountriesList.add(country);
        }

        Collections.sort(allCountriesList, this);

        selectedCountriesList = new ArrayList<Country>();
        selectedCountriesList.addAll(allCountriesList);

        // Return
        return allCountriesList;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private static String readEncodedJsonString(Context context) throws java.io.IOException {
    String base64 = context.getResources().getString(R.string.countries_code);
    byte[] data = Base64.decode(base64, Base64.DEFAULT);
    return new String(data, "UTF-8");
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

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.country_picker, null);

    getAllCountries();

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

    adapter = new CountryListAdapter(getActivity(), selectedCountriesList);
    countryListView.setAdapter(adapter);

    countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
          Country country = selectedCountriesList.get(position);
          listener.onSelectCountry(country.getName(), country.getCode(), country.getDialCode(),
              country.getFlag());
        }
      }
    });

    searchEditText.addTextChangedListener(new TextWatcher() {

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override public void afterTextChanged(Editable s) {
        search(s.toString());
      }
    });

    return view;
  }

  @SuppressLint("DefaultLocale") private void search(String text) {
    selectedCountriesList.clear();
    for (Country country : allCountriesList) {
      if (country.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
        selectedCountriesList.add(country);
      }
    }
    adapter.notifyDataSetChanged();
  }

  @Override public int compare(Country lhs, Country rhs) {
    return lhs.getName().compareTo(rhs.getName());
  }

  public Bundle getUserCountryInfo(Context context) {
    String countryIsoCode, countryDialCode;
    int flagRes;
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if (!(telephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT)) {
      countryIsoCode = telephonyManager.getSimCountryIso();
      countryDialCode = telephonyManager.getLine1Number();
    } else {
      Toast.makeText(context, R.string.no_sim_detected, Toast.LENGTH_SHORT).show();
      countryIsoCode = "AF";
      countryDialCode = "93";
    }
    int flagResId = getFlagResId(countryIsoCode);
    if (flagResId != 0) {
      flagRes = flagResId;
    } else {
      flagRes = R.drawable.flag_af;
    }
    Bundle userSimBundle = new Bundle();
    userSimBundle.putString(USER_COUNTRY_CODE, countryIsoCode);
    userSimBundle.putInt(USER_COUNTRY_FLAG, flagRes);
    userSimBundle.putString(USER_COUNTRY_DIAL_CODE, countryDialCode);
    return userSimBundle;
  }

  private int getFlagResId(String drawable) {
    try {
      return getResources().getIdentifier("flag_" + drawable.toLowerCase(Locale.ENGLISH),
          "drawable", getContext().getPackageName());
    } catch (Exception e) {
      return 0;
    }
  }
}
