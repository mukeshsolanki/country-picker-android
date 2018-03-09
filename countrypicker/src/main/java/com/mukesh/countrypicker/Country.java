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
 */

public class Country {
    private String code;
    private String name;
    private String dialCode;
    private int flag = -1;

    public Country(String code, String name, String dialCode, int flag) {
        this.code = code;
        this.name = name;
        this.dialCode = dialCode;
        this.flag = flag;
    }

    public Country() {
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

    public void loadFlagByCode(Context context) {
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

    public static List<Country> getAllCountries(String defaultLanguage) {
        if (allCountriesList == null) {
            CountryConstants.setCountriesBasedOnLanguage(defaultLanguage);
            allCountriesList = Arrays.asList(CountryConstants.COUNTRIES);
        }
        return allCountriesList;
    }

    public static Country getCountryByISO(String countryIsoCode) {
        countryIsoCode = countryIsoCode.toUpperCase();

        Country c = new Country();
        c.setCode(countryIsoCode);

        int i = Arrays.binarySearch(CountryConstants.COUNTRIES, c, new ISOCodeComparator());

        if (i < 0) {
            return null;
        } else {
            return CountryConstants.COUNTRIES[i];
        }
    }

    public static Country getCountryByName(String countryName) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // countries one by one

        for (Country c : CountryConstants.COUNTRIES) {
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
        if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
            return Country.getCountryByISO(telephonyManager.getSimCountryIso());
        }
        return null;
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