

# Country Picker for Android



[![](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9) [![](https://jitpack.io/v/mukeshsolanki/country-picker-android.svg)](https://jitpack.io/#mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/Android%20Arsenal-Country%20Picker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/3561) [![](https://travis-ci.org/mukeshsolanki/country-picker-android.svg?branch=master)](https://travis-ci.org/mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/paypal-donate-yellow.svg)](https://www.paypal.me/mukeshsolanki)

CountryPicker is a simple library that can be show a country picker. See the example to see more detail.

![](https://raw.githubusercontent.com/mukeshsolanki/country-picker-android/master/Screenshot_20160506-152951.png)

## How to use

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
        compile 'com.github.mukeshsolanki:country-picker-android:1.1.7'
}
```

Once the project has been added to gradle the user can implement this with easy.

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
picker.setListener(new CountryPickerListener() {
    @Override
    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
        // Implement your code here
    }
});
```

That's it your all done.

### Operations

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
List<Country> countries = Country.getAllCountriesList(); //List of all countries
Country[] countries = Country.COUNTRIES; //Array of all countries sorted by ISO code
Country country = Country.getCountryFromSIM(context); //Get user country based on sim
Country country = Country.getCountryByLocale(locale); //Get country based on Locale
Country country = Country.getCountryByName( country_name); //Get country by country name


String name = country.getName();
String code = country.getCode();
int flag = country.getFlag();  // returns android resource id of flag or -1, if none is associated
country.loadFlagByCode();  // attempts to associate flag to country based on its ISO code
String dialCode = country.getDialCode();
```
