package com.mukesh.countrypicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryPickerDialog extends DialogFragment {

  // region Variables
  private CountryPickerDialogInteractionListener dialogInteractionListener;
  private EditText searchEditText;
  private ListView countryListView;
  private CountryListAdapter adapter;
  private List<Country> searchResults;
  private CountryPickerListener listener;
  // endregion

  // region Constructors
  public static CountryPickerDialog newInstance() {
    return new CountryPickerDialog();
  }
  // endregion

  // region Lifecycle
  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.country_picker, null);
    getDialog().setTitle("Select country");
    searchEditText = view.findViewById(R.id.country_code_picker_search);
    countryListView = view.findViewById(R.id.country_code_picker_listview);

    searchResults = new ArrayList<>();
    searchResults.addAll(dialogInteractionListener.getAllCountries());

    adapter = new CountryListAdapter(getActivity(), searchResults);
    countryListView.setAdapter(adapter);

    countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
          Country country = searchResults.get(position);
          listener.onSelectCountry(country);
          dismiss();
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
      public void afterTextChanged(Editable searchQuery) {
        search(searchQuery.toString());
      }
    });

    return view;
  }
  // endregion

  // region Setter Methods
  public void setCountryPickerListener(CountryPickerListener listener) {
    this.listener = listener;
  }

  public void setDialogInteractionListener(
      CountryPickerDialogInteractionListener dialogInteractionListener) {
    this.dialogInteractionListener = dialogInteractionListener;
  }
  // endregion

  // region Utility Methods
  private void search(String searchQuery) {
    searchResults.clear();
    for (Country country : dialogInteractionListener.getAllCountries()) {
      if (country.getName().toLowerCase(Locale.ENGLISH).contains(searchQuery.toLowerCase())) {
        searchResults.add(country);
      }
    }
    dialogInteractionListener.sortCountries(searchResults);
    adapter.notifyDataSetChanged();
  }
  // endregion

  //region Interface
  public interface CountryPickerDialogInteractionListener {
    List<Country> getAllCountries();

    void sortCountries(List<Country> searchResults);
  }
  // endregion
}
