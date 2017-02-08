<h1 align="center">Country Picker for Android</h1>
<p align="center">
  <a href="https://android-arsenal.com/api?level=9"> <img src="https://img.shields.io/badge/API-9%2B-blue.svg?style=flat" /></a>
  <a href="https://jitpack.io/#mukeshsolanki/country-picker-android"> <img src="https://jitpack.io/v/mukeshsolanki/country-picker-android.svg" /></a>
  <a href="http://android-arsenal.com/details/3/3561"> <img src="https://img.shields.io/badge/Android%20Arsenal-Country%20Picker-brightgreen.svg?style=flat" /></a>
  <a href="https://travis-ci.org/mukeshsolanki/country-picker-android"> <img src="https://travis-ci.org/mukeshsolanki/country-picker-android.svg?branch=master" /></a>
  <a href="https://www.paypal.me/mukeshsolanki"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a>
  <br /><br />CountryPicker is a simple library that can be show a country picker. See the example to see more detail.
</p>


<img src="https://raw.githubusercontent.com/mukeshsolanki/country-picker-android/master/Screenshot_20160506-152951.png" width="480" height="800" />

## How to use

Integrating the project is simple a refined all you need to do is follow the below steps

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Step 2. Add the dependency
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

The following code will get the current users country details based on sim.

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
List<Country> countries = picker.getAllCountries(); //Get all countries
Country country = picker.getUserCountryInfo(this); //Get user country based on sim
Country country = picker.getCountryByLocale(context , local); //Get country based on Locale
Country country = picker.getCountryByName(context , country_name); //Get country by country name

//TODO use the country object
```

### Get country from english name

```java
  public Country getCountryByName ( Context context, String countryName ) {
    this.context = context;
    Map<String, String> countries = new HashMap<>();
    for (String iso : Locale.getISOCountries()) {
      Locale l = new Locale("", iso);
      countries.put(l.getDisplayCountry(), iso);
    }

    String countryIsoCode = countries.get(countryName);
    if (countryIsoCode != null) {
      return getCountry(countryIsoCode);
    }
    return afghanistan();
  }
```
  
After you have initially selected a country, you may want to store it and retrieve the flag or other details later. This method allows you to store the selected country by its name, and later retrieve it with that same name. Try it as follows

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
Country country = picker.getCountryByName("Canada");
//TODO use the country object
```

### Get country from locale

```java
  public Country getCountryByLocale( Context context, Locale locale ) {
    this.context = context;
    String countryIsoCode = locale.getISO3Country().substring(0,2).toLowerCase();
    return getCountry(countryIsoCode);
  }
```

The Android Locale class uses ISO country name standards. This is useful for storing and then retriving the country that was first selected using the country picker. Pass your desired locale into this method to retrieve its country class and contained flag. Try it as follows

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
Country country = picker.getCountryByLocale(Locale.GERMANY);
//TODO use the country object
```
