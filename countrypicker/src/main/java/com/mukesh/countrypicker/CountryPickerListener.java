package com.mukesh.countrypicker;

/**
 * Created by mukesh on 25/04/16.
 * Updated on 1/11/17 by @stom79
 */
public interface CountryPickerListener {
  void onSelectCountry(String name, String code, String dialCode, String locale, int flagDrawableResID);
}
