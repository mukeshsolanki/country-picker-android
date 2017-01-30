package com.mukesh.countrypicker.models;

import android.text.TextUtils;
import android.util.Base64;

import com.mukesh.countrypicker.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 */

public class Country {
  private String code;
  private String name;
  private String dialCode;
  private int flag;

  public String getDialCode() {
    return dialCode;
  }

  public void setDialCode(String dialCode) {
    this.dialCode = dialCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
    if (TextUtils.isEmpty(name)) {
      name = new Locale("", code).getDisplayName();
    }
  }

  public String getName() {
    return name;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public static class NameComparator implements java.util.Comparator<Country> {
    @Override
    public int compare(Country lhs, Country rhs) {
      return lhs.getName().compareTo(rhs.getName());
    }
  }

  public static class ISOCodeComparator implements java.util.Comparator<Country> {
    @Override
    public int compare(Country lhs, Country rhs) {
      return lhs.getCode().compareTo(rhs.getCode());
    }
  }


  private static List<Country> allCountriesList;

  public static List<Country> getAllCountries() {
    if (allCountriesList == null) {
      try {
        allCountriesList = new ArrayList<>();
        String allCountriesCode = new String(Base64.decode(Constants.ENCODED_COUNTRY_CODE, Base64.DEFAULT), "UTF-8");
        JSONArray countryArray = new JSONArray(allCountriesCode);
        for (int i = 0; i < countryArray.length(); i++) {
          JSONObject jsonObject = countryArray.getJSONObject(i);
          String countryDialCode = jsonObject.getString("dial_code");
          String countryCode = jsonObject.getString("code");
          Country country = new Country();
          country.setCode(countryCode);
          country.setDialCode(countryDialCode);
          allCountriesList.add(country);
        }
        Collections.sort(allCountriesList, new Country.NameComparator());
        return allCountriesList;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return allCountriesList;
  }
}