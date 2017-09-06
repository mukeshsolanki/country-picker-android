package com.mukesh.countrypicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    if(countriesList.size() == 0) {
      setCountriesList(Country.getAllCountries(getContext()));
    }

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

  public void setListener(CountryPickerListener listener) {
    this.listener = listener;
  }

  private void search(String text) {
    selectedCountriesList.clear();
    text = text.toLowerCase(Locale.getDefault());
    final String regexPunct = "[\\p{ASCII}]";
    String textNorm = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll(regexPunct, "");
    String countryName;
    for (Country country : countriesList) {
      countryName = country.getName().toLowerCase(Locale.getDefault());
      if (countryName.contains(text)) {
        selectedCountriesList.add(country);
        continue;
      }
      countryName = Normalizer.normalize(countryName, Normalizer.Form.NFD).replaceAll(regexPunct, "");
      if (countryName.contains(textNorm))
        selectedCountriesList.add(country);
    }
    adapter.notifyDataSetChanged();
  }

  public void setCountriesList(List<Country> newCountries) {
    this.countriesList.clear();
    this.countriesList.addAll(newCountries);
    this.selectedCountriesList = new ArrayList<>(countriesList);

    if(adapter!=null)
      adapter.setItems(this.selectedCountriesList);
  }

}
