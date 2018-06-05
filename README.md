# Country Picker for Android

[![](https://jitpack.io/v/mukeshsolanki/country-picker-android/month.svg)](https://jitpack.io/#mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/API-9%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=9) [![](https://jitpack.io/v/mukeshsolanki/country-picker-android.svg)](https://jitpack.io/#mukeshsolanki/country-picker-android) [![](https://img.shields.io/badge/Android%20Arsenal-Country%20Picker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/3561) [![](https://travis-ci.org/mukeshsolanki/country-picker-android.svg?branch=master)](https://travis-ci.org/mukeshsolanki/country-picker-android) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![](https://img.shields.io/badge/paypal-donate-yellow.svg)](https://www.paypal.me/mukeshsolanki)

CountryPicker is a simple library that can be show a country picker. See the example to see more detail.

![](https://raw.githubusercontent.com/mukeshsolanki/country-picker-android/master/Screenshot_20160506-152951.png)

## How to use

### Integration

Integrating and using the project is simple all you need to do is follow the below steps

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

Once the project has been added to gradle, You can use the builder to configure and display it as a dialog.

```java
CountryPicker countryPicker =
    new CountryPicker.Builder().with(context)
        .listener(new OnCountryPickerListener() {
          @Override public void onSelectCountry(Country country) {
            //DO something here
          }
        })
        .build();


countryPicker.showDialog(getSupportFragmentManager()); // Show the dialog
```

That's it, all done.

### Generic operations with countries

```java
countryPicker.setCountries(List<Country> countries); //Set custom list of countries
List<Country> countries = countryPicker.getAllCountries(); //List of all countries
Country country = countryPicker.getCountryFromSIM(context); //Get user country based on SIM card
Country country = countryPicker.getCountryByLocale(locale); //Get country based on Locale
Country country = countryPicker.getCountryByName(countryName); //Get country by its name
Country country = countryPicker.getCountryByISO(countryIsoCode); //Get country by its ISO code

String name = country.getName();
String code = country.getCode();
int flag = country.getFlag();  // returns android resource id of flag or -1, if none is associated
String dialCode = country.getDialCode();
String currency = country.getCurrency();

country.loadFlagByCode();  // attempts to associate flag to country based on its ISO code. Used if you create your own instance of Country.class
```

## Author
Maintained by [Mukesh Solanki](https://www.github.com/mukeshsolanki)

## Contribution
[![GitHub contributors](https://img.shields.io/github/contributors/mukeshsolanki/country-picker-android.svg)](https://github.com/mukeshsolanki/country-picker-android/graphs/contributors)

* Bug reports and pull requests are welcome.
* Make sure you use [square/java-code-styles](https://github.com/square/java-code-styles) to format your code.

## License
```
Copyright 2018 Mukesh Solanki

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
