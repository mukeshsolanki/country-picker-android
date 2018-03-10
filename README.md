

# Country Picker for Android



[![](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9) [![](https://jitpack.io/v/mukeshsolanki/country-picker-android.svg)](https://jitpack.io/#mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/Android%20Arsenal-Country%20Picker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/3561) [![](https://travis-ci.org/mukeshsolanki/country-picker-android.svg?branch=master)](https://travis-ci.org/mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/paypal-donate-yellow.svg)](https://www.paypal.me/mukeshsolanki)

CountryPicker is a simple library that can be show a country picker. See the example to see more detail.

![](https://github.com/bashayerAlsalman/country-picker-android/blob/master/Screenshot_1520674262.png?raw=true)

## How to use

### Integration

Integrating the project is simple a refined all you need to do is follow the below steps

Step 1\. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

Step 2\. Add the dependency

```java
dependencies {
        compile 'com.github.mukeshsolanki:country-picker-android:<latest-version>'
}
```

### Usage

Once the project has been added to gradle, the dialog can be easily used.

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
picker.setListener(new CountryPickerListener() {
    @Override
    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
        // Implement your code here
    }
});
picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
```

That's it, all done.

### Generic operations with countries

```java
List<Country> countries = Country.getAllCountriesList(); //List of all countries
Country[] countries = Country.COUNTRIES; //Array of all countries sorted by ISO code
Country country = Country.getCountryFromSIM(context); //Get user country based on SIM card
Country country = Country.getCountryByLocale(locale); //Get country based on Locale
Country country = Country.getCountryByName(countryName); //Get country by its name


String name = country.getName();
String code = country.getCode();
int flag = country.getFlag();  // returns android resource id of flag or -1, if none is associated
String dialCode = country.getDialCode();

country.loadFlagByCode();  // attempts to associate flag to country based on its ISO code. Used if you create your own instance of Country.class
```

All Credit goes to mukeshsolanki | https://github.com/mukeshsolanki/country-picker-android
Only arabic language is added in this version and some enhancements due the addition of the language.
