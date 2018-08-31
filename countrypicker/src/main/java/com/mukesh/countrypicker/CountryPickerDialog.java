package com.mukesh.countrypicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryPickerDialog extends DialogFragment implements OnItemClickListener {

  private static final String BUNDLE_KEY_STYLE = "style";
  // region Variables
  private CountryPickerDialogInteractionListener dialogInteractionListener;
  private LinearLayout rootView;
  private EditText searchEditText;
  private RecyclerView countriesRecyclerView;
  private CountriesAdapter adapter;
  private List<Country> searchResults;
  private OnCountryPickerListener listener;
  private int textColor;
  private int hintColor;
  private int backgroundColor;
  private int searchIcon;
  // endregion

  // region Constructors
  public static CountryPickerDialog newInstance(int style) {
    CountryPickerDialog countryPickerDialog = new CountryPickerDialog();
    Bundle args = new Bundle();
    args.putInt(BUNDLE_KEY_STYLE, style);
    countryPickerDialog.setArguments(args);
    return countryPickerDialog;
  }
  // endregion

  // region Lifecycle
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.country_picker, container);
    getDialog().setTitle(R.string.country_picker_header);
    initiateUi(view);
    setCustomStyle(view);
    setSearchEditText();
    setupRecyclerView();
    if (!dialogInteractionListener.canSearch()) {
      searchEditText.setVisibility(View.GONE);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    if (getDialog().getWindow() != null) {
      WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
      params.width = LinearLayout.LayoutParams.MATCH_PARENT;
      params.height = LinearLayout.LayoutParams.MATCH_PARENT;
      getDialog().getWindow().setAttributes(params);
    }
  }

  @Override public void onItemClicked(Country country) {
    if (listener != null) {
      listener.onSelectCountry(country);
    }
    dismiss();
  }
  // endregion

  // region Setter Methods
  public void setCountryPickerListener(OnCountryPickerListener listener) {
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

  private void setupRecyclerView() {
    searchResults = new ArrayList<>();
    searchResults.addAll(dialogInteractionListener.getAllCountries());
    adapter = new CountriesAdapter(getActivity(), searchResults, this, textColor);
    countriesRecyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    countriesRecyclerView.setLayoutManager(layoutManager);
    countriesRecyclerView.setAdapter(adapter);
  }

  private void setSearchEditText() {
    searchEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Intentionally Empty
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Intentionally Empty
      }

      @Override
      public void afterTextChanged(Editable searchQuery) {
        search(searchQuery.toString());
      }
    });
    searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        InputMethodManager imm = (InputMethodManager) searchEditText.getContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
          imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
        return true;
      }
    });
  }

  @SuppressWarnings("ResourceType")
  private void setCustomStyle(View view) {
    Bundle args = getArguments();
    if (args != null) {
      int style = args.getInt(BUNDLE_KEY_STYLE, 0);
      if (style != 0) {
        int[] attrs =
            {
                android.R.attr.textColor, android.R.attr.textColorHint, android.R.attr.background,
                android.R.attr.drawable
            };
        TypedArray ta = view.getContext().obtainStyledAttributes(style, attrs);
        textColor = ta.getColor(0, Color.BLACK);
        hintColor = ta.getColor(1, Color.GRAY);
        backgroundColor = ta.getColor(2, Color.WHITE);
        searchIcon = ta.getResourceId(3, R.drawable.ic_search);
        searchEditText.setTextColor(textColor);
        searchEditText.setHintTextColor(hintColor);
        Drawable icon = ContextCompat.getDrawable(searchEditText.getContext(), searchIcon);
        if (searchIcon == R.drawable.ic_search) {
          icon.setColorFilter(new PorterDuffColorFilter(hintColor, PorterDuff.Mode.SRC_ATOP));
        }
        searchEditText.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        rootView.setBackgroundColor(backgroundColor);
        ta.recycle();
      }
    }
  }

  private void initiateUi(View view) {
    searchEditText = view.findViewById(R.id.country_code_picker_search);
    countriesRecyclerView = view.findViewById(R.id.countries_recycler_view);
    rootView = view.findViewById(R.id.rootView);
  }
  // endregion

  //region Interface
  public interface CountryPickerDialogInteractionListener {
    List<Country> getAllCountries();

    void sortCountries(List<Country> searchResults);

    boolean canSearch();
  }
  // endregion
}
