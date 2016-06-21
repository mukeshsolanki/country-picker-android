# Country Picker for Android

CountryPicker is a simple library that can be show a country picker. See the example to see more detail.

<img src="https://raw.githubusercontent.com/mukeshsolanki/country-picker-android/master/Screenshot_20160506-152951.png" width="480" height="800" />

## How to use

Integrating the project is simple a refined all you need to do is add this to your app level gradle file

```java
compile 'com.mukesh:countrypicker:1.1.4'
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

### Get user country based on sim

The following code will get the current users country details based on sim.

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");
Country country = picker.getUserCountryInfo(this);
//TODO use the country object
```