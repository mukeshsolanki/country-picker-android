package com.mukesh.countrypicker;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 * Updated 01/11/17 by @stom79
 */

public class Country {
  private static final Country[] COUNTRIES = {
      new Country("AD", "Andorra", "+376", "ca", R.drawable.flag_ad),
      new Country("AE", "United Arab Emirates", "+971","ar", R.drawable.flag_ae),
      new Country("AL", "Albania", "+355","sq", R.drawable.flag_al),
      new Country("AM", "Armenia", "+374","hy", R.drawable.flag_am),
      new Country("AZ", "Azerbaijan", "+994", "az", R.drawable.flag_az),
      new Country("BA", "Bosnia and Herzegovina", "bs", "+387", R.drawable.flag_ba),
      new Country("BD", "Bangladesh", "+880", "bn", R.drawable.flag_bd),
      new Country("BG", "Bulgaria", "+359", "bg", R.drawable.flag_bg),
      new Country("CN", "China", "+86", "zh", R.drawable.flag_cn),
      new Country("CZ", "Czech Republic", "+420","cs", R.drawable.flag_cz),
      new Country("DE", "Germany", "+49", "de", R.drawable.flag_de),
      new Country("DK", "Denmark", "+45", "da", R.drawable.flag_dk),
      new Country("ES", "Spain", "+34","es", R.drawable.flag_es),
      new Country("ET", "Ethiopia", "+251","am", R.drawable.flag_et),
      new Country("FI", "Finland", "+358", "sv", R.drawable.flag_fi),
      new Country("FR", "France", "+33", "fr", R.drawable.flag_fr),
      new Country("GB", "United Kingdom", "+44", "en", R.drawable.flag_gb),
      new Country("GE", "Georgia", "+995","ka", R.drawable.flag_ge),
      new Country("GR", "Greece", "+30", "el", R.drawable.flag_gr),
      new Country("HR", "Croatia", "+385","hr", R.drawable.flag_hr),
      new Country("HU", "Hungary", "+36", "hu", R.drawable.flag_hu),
      new Country("ID", "Indonesia", "+62", "id", R.drawable.flag_id),
      new Country("IL", "Israel", "+972", "he", R.drawable.flag_il),
      new Country("IN", "India", "+91", "hi", R.drawable.flag_in),
      new Country("IT", "Italy", "+39","it", R.drawable.flag_it),
      new Country("JP", "Japan", "+81", "ja", R.drawable.flag_jp),
      new Country("KR", "South Korea", "+82","ko", R.drawable.flag_kr),
      new Country("LT", "Lithuania", "+370","lt", R.drawable.flag_lt),
      new Country("LV", "Latvia", "+371", "lv", R.drawable.flag_lv),
      new Country("MN", "Mongolia", "+976", "mn", R.drawable.flag_mn),
      new Country("MY", "Malaysia", "+60","ms", R.drawable.flag_my),
      new Country("NL", "Netherlands", "+31", "nl", R.drawable.flag_nl),
      new Country("NO", "Norway", "+47", "no", R.drawable.flag_no),
      new Country("PL", "Poland", "+48","pl", R.drawable.flag_pl),
      new Country("PT", "Portugal", "+351", "pt", R.drawable.flag_pt),
      new Country("RO", "Romania", "+40", "ro", R.drawable.flag_ro),
      new Country("RS", "Serbia", "+381", "sr", R.drawable.flag_rs),
      new Country("RU", "Russia", "+7","ru", R.drawable.flag_ru),
      new Country("SE", "Sweden", "+46", "sv", R.drawable.flag_se),
      new Country("SK", "Slovakia", "+421", "sk", R.drawable.flag_sk),
      new Country("TH", "Thailand", "+66", "th", R.drawable.flag_th),
      new Country("TR", "Turkey", "+90", "tr", R.drawable.flag_tr),
      new Country("UA", "Ukraine", "+380", "uk", R.drawable.flag_ua),
      new Country("VN", "Viet Nam", "+84", "vi", R.drawable.flag_vn),
  };

  private String code;
  private String name;
  private String dialCode;
  private String locale;
  private int flag = -1;

  private Country(String code, String name, String dialCode, String locale, int flag) {
    this.code = code;
    this.name = name;
    this.dialCode = dialCode;
    this.locale = locale;
    this.flag = flag;
  }

  private Country() {
  }



  public String getDialCode() {
    return dialCode;
  }

  public void setDialCode(String dialCode) {
    this.dialCode = dialCode;
  }

  public String getCode() {
    return code;
  }

  private void setCode(String code) {
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

  void loadFlagByCode(Context context) {
    if (this.flag != -1)
      return;

    try {
      this.flag = context.getResources()
          .getIdentifier("flag_" + this.code.toLowerCase(Locale.ENGLISH), "drawable",
              context.getPackageName());
    } catch (Exception e) {
      e.printStackTrace();
      this.flag = -1;
    }
  }


    /*
     *      GENERIC STATIC FUNCTIONS
     */

  private static List<Country> allCountriesList;

  public static List<Country> getAllCountries() {
    if (allCountriesList == null) {
      allCountriesList = Arrays.asList(COUNTRIES);
    }
    return allCountriesList;
  }

  private static Country getCountryByISO(String countryIsoCode) {
    countryIsoCode = countryIsoCode.toUpperCase();

    Country c = new Country();
    c.setCode(countryIsoCode);

    int i = Arrays.binarySearch(COUNTRIES, c, new ISOCodeComparator());

    if (i < 0) {
      return null;
    } else {
      return COUNTRIES[i];
    }
  }

  public static Country getCountryByName(String countryName) {
    // Because the data we have is sorted by ISO codes and not by names, we must check all
    // countries one by one

    for (Country c : COUNTRIES) {
      if (countryName.equals(c.getName())) {
        return c;
      }
    }
    return null;
  }

  public static Country getCountryByLocale(Locale locale) {
    String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
    return Country.getCountryByISO(countryIsoCode);
  }

  public static Country getCountryFromSIM(Context context) {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    assert telephonyManager != null;
    if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
      return Country.getCountryByISO(telephonyManager.getSimCountryIso());
    }
    return null;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

    /*
     * COMPARATORS
     */

  public static class ISOCodeComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country t1) {
      return country.code.compareTo(t1.code);
    }
  }


  public static class NameComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country t1) {
      return country.name.compareTo(t1.name);
    }
  }
}