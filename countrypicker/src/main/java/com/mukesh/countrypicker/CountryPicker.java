package com.mukesh.countrypicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CountryPicker
    implements CountryPickerDialog.CountryPickerDialogInteractionListener {

  // region Countries
  private final Country[] COUNTRIES = {
      new Country("AD", "Andorra", "+376", R.drawable.flag_ad, "EUR"),
      new Country("AE", "United Arab Emirates", "+971", R.drawable.flag_ae, "AED"),
      new Country("AF", "Afghanistan", "+93", R.drawable.flag_af, "AFN"),
      new Country("AG", "Antigua and Barbuda", "+1", R.drawable.flag_ag, "XCD"),
      new Country("AI", "Anguilla", "+1", R.drawable.flag_ai, "XCD"),
      new Country("AL", "Albania", "+355", R.drawable.flag_al, "ALL"),
      new Country("AM", "Armenia", "+374", R.drawable.flag_am, "AMD"),
      new Country("AO", "Angola", "+244", R.drawable.flag_ao, "AOA"),
      new Country("AQ", "Antarctica", "+672", R.drawable.flag_aq, "USD"),
      new Country("AR", "Argentina", "+54", R.drawable.flag_ar, "ARS"),
      new Country("AS", "American Samoa", "+1", R.drawable.flag_as, "USD"),
      new Country("AT", "Austria", "+43", R.drawable.flag_at, "EUR"),
      new Country("AU", "Australia", "+61", R.drawable.flag_au, "AUD"),
      new Country("AW", "Aruba", "+297", R.drawable.flag_aw, "AWG"),
      new Country("AX", "Aland Islands", "+358", R.drawable.flag_ax, "EUR"),
      new Country("AZ", "Azerbaijan", "+994", R.drawable.flag_az, "AZN"),
      new Country("BA", "Bosnia and Herzegovina", "+387", R.drawable.flag_ba, "BAM"),
      new Country("BB", "Barbados", "+1", R.drawable.flag_bb, "BBD"),
      new Country("BD", "Bangladesh", "+880", R.drawable.flag_bd, "BDT"),
      new Country("BE", "Belgium", "+32", R.drawable.flag_be, "EUR"),
      new Country("BF", "Burkina Faso", "+226", R.drawable.flag_bf, "XOF"),
      new Country("BG", "Bulgaria", "+359", R.drawable.flag_bg, "BGN"),
      new Country("BH", "Bahrain", "+973", R.drawable.flag_bh, "BHD"),
      new Country("BI", "Burundi", "+257", R.drawable.flag_bi, "BIF"),
      new Country("BJ", "Benin", "+229", R.drawable.flag_bj, "XOF"),
      new Country("BL", "Saint Barthelemy", "+590", R.drawable.flag_bl, "EUR"),
      new Country("BM", "Bermuda", "+1", R.drawable.flag_bm, "BMD"),
      new Country("BN", "Brunei Darussalam", "+673", R.drawable.flag_bn, "BND"),
      new Country("BO", "Bolivia, Plurinational State of", "+591", R.drawable.flag_bo, "BOB"),
      new Country("BQ", "Bonaire", "+599", R.drawable.flag_bq, "USD"),
      new Country("BR", "Brazil", "+55", R.drawable.flag_br, "BRL"),
      new Country("BS", "Bahamas", "+1", R.drawable.flag_bs, "BSD"),
      new Country("BT", "Bhutan", "+975", R.drawable.flag_bt, "BTN"),
      new Country("BV", "Bouvet Island", "+47", R.drawable.flag_bv, "NOK"),
      new Country("BW", "Botswana", "+267", R.drawable.flag_bw, "BWP"),
      new Country("BY", "Belarus", "+375", R.drawable.flag_by, "BYR"),
      new Country("BZ", "Belize", "+501", R.drawable.flag_bz, "BZD"),
      new Country("CA", "Canada", "+1", R.drawable.flag_ca, "CAD"),
      new Country("CC", "Cocos (Keeling) Islands", "+61", R.drawable.flag_cc, "AUD"),
      new Country("CD", "Congo, The Democratic Republic of the", "+243", R.drawable.flag_cd, "CDF"),
      new Country("CF", "Central African Republic", "+236", R.drawable.flag_cf, "XAF"),
      new Country("CG", "Congo", "+242", R.drawable.flag_cg, "XAF"),
      new Country("CH", "Switzerland", "+41", R.drawable.flag_ch, "CHF"),
      new Country("CI", "Ivory Coast", "+225", R.drawable.flag_ci, "XOF"),
      new Country("CK", "Cook Islands", "+682", R.drawable.flag_ck, "NZD"),
      new Country("CL", "Chile", "+56", R.drawable.flag_cl, "CLP"),
      new Country("CM", "Cameroon", "+237", R.drawable.flag_cm, "XAF"),
      new Country("CN", "China", "+86", R.drawable.flag_cn, "CNY"),
      new Country("CO", "Colombia", "+57", R.drawable.flag_co, "COP"),
      new Country("CR", "Costa Rica", "+506", R.drawable.flag_cr, "CRC"),
      new Country("CU", "Cuba", "+53", R.drawable.flag_cu, "CUP"),
      new Country("CV", "Cape Verde", "+238", R.drawable.flag_cv, "CVE"),
      new Country("CW", "Curacao", "+599", R.drawable.flag_cw, "ANG"),
      new Country("CX", "Christmas Island", "+61", R.drawable.flag_cx, "AUD"),
      new Country("CY", "Cyprus", "+357", R.drawable.flag_cy, "EUR"),
      new Country("CZ", "Czech Republic", "+420", R.drawable.flag_cz, "CZK"),
      new Country("DE", "Germany", "+49", R.drawable.flag_de, "EUR"),
      new Country("DJ", "Djibouti", "+253", R.drawable.flag_dj, "DJF"),
      new Country("DK", "Denmark", "+45", R.drawable.flag_dk, "DKK"),
      new Country("DM", "Dominica", "+1", R.drawable.flag_dm, "XCD"),
      new Country("DO", "Dominican Republic", "+1", R.drawable.flag_do, "DOP"),
      new Country("DZ", "Algeria", "+213", R.drawable.flag_dz, "DZD"),
      new Country("EC", "Ecuador", "+593", R.drawable.flag_ec, "USD"),
      new Country("EE", "Estonia", "+372", R.drawable.flag_ee, "EUR"),
      new Country("EG", "Egypt", "+20", R.drawable.flag_eg, "EGP"),
      new Country("EH", "Western Sahara", "+212", R.drawable.flag_eh, "MAD"),
      new Country("ER", "Eritrea", "+291", R.drawable.flag_er, "ERN"),
      new Country("ES", "Spain", "+34", R.drawable.flag_es, "EUR"),
      new Country("ET", "Ethiopia", "+251", R.drawable.flag_et, "ETB"),
      new Country("FI", "Finland", "+358", R.drawable.flag_fi, "EUR"),
      new Country("FJ", "Fiji", "+679", R.drawable.flag_fj, "FJD"),
      new Country("FK", "Falkland Islands (Malvinas)", "+500", R.drawable.flag_fk, "FKP"),
      new Country("FM", "Micronesia, Federated States of", "+691", R.drawable.flag_fm, "USD"),
      new Country("FO", "Faroe Islands", "+298", R.drawable.flag_fo, "DKK"),
      new Country("FR", "France", "+33", R.drawable.flag_fr, "EUR"),
      new Country("GA", "Gabon", "+241", R.drawable.flag_ga, "XAF"),
      new Country("GB", "United Kingdom", "+44", R.drawable.flag_gb, "GBP"),
      new Country("GD", "Grenada", "+1", R.drawable.flag_gd, "XCD"),
      new Country("GE", "Georgia", "+995", R.drawable.flag_ge, "GEL"),
      new Country("GF", "French Guiana", "+594", R.drawable.flag_gf, "EUR"),
      new Country("GG", "Guernsey", "+44", R.drawable.flag_gg, "GGP"),
      new Country("GH", "Ghana", "+233", R.drawable.flag_gh, "GHS"),
      new Country("GI", "Gibraltar", "+350", R.drawable.flag_gi, "GIP"),
      new Country("GL", "Greenland", "+299", R.drawable.flag_gl, "DKK"),
      new Country("GM", "Gambia", "+220", R.drawable.flag_gm, "GMD"),
      new Country("GN", "Guinea", "+224", R.drawable.flag_gn, "GNF"),
      new Country("GP", "Guadeloupe", "+590", R.drawable.flag_gp, "EUR"),
      new Country("GQ", "Equatorial Guinea", "+240", R.drawable.flag_gq, "XAF"),
      new Country("GR", "Greece", "+30", R.drawable.flag_gr, "EUR"),
      new Country("GS", "South Georgia and the South Sandwich Islands", "+500", R.drawable.flag_gs,
          "GBP"),
      new Country("GT", "Guatemala", "+502", R.drawable.flag_gt, "GTQ"),
      new Country("GU", "Guam", "+1", R.drawable.flag_gu, "USD"),
      new Country("GW", "Guinea-Bissau", "+245", R.drawable.flag_gw, "XOF"),
      new Country("GY", "Guyana", "+595", R.drawable.flag_gy, "GYD"),
      new Country("HK", "Hong Kong", "+852", R.drawable.flag_hk, "HKD"),
      new Country("HM", "Heard Island and McDonald Islands", "+000", R.drawable.flag_hm, "AUD"),
      new Country("HN", "Honduras", "+504", R.drawable.flag_hn, "HNL"),
      new Country("HR", "Croatia", "+385", R.drawable.flag_hr, "HRK"),
      new Country("HT", "Haiti", "+509", R.drawable.flag_ht, "HTG"),
      new Country("HU", "Hungary", "+36", R.drawable.flag_hu, "HUF"),
      new Country("ID", "Indonesia", "+62", R.drawable.flag_id, "IDR"),
      new Country("IE", "Ireland", "+353", R.drawable.flag_ie, "EUR"),
      new Country("IL", "Israel", "+972", R.drawable.flag_il, "ILS"),
      new Country("IM", "Isle of Man", "+44", R.drawable.flag_im, "GBP"),
      new Country("IN", "India", "+91", R.drawable.flag_in, "INR"),
      new Country("IO", "British Indian Ocean Territory", "+246", R.drawable.flag_io, "USD"),
      new Country("IQ", "Iraq", "+964", R.drawable.flag_iq, "IQD"),
      new Country("IR", "Iran, Islamic Republic of", "+98", R.drawable.flag_ir, "IRR"),
      new Country("IS", "Iceland", "+354", R.drawable.flag_is, "ISK"),
      new Country("IT", "Italy", "+39", R.drawable.flag_it, "EUR"),
      new Country("JE", "Jersey", "+44", R.drawable.flag_je, "JEP"),
      new Country("JM", "Jamaica", "+1", R.drawable.flag_jm, "JMD"),
      new Country("JO", "Jordan", "+962", R.drawable.flag_jo, "JOD"),
      new Country("JP", "Japan", "+81", R.drawable.flag_jp, "JPY"),
      new Country("KE", "Kenya", "+254", R.drawable.flag_ke, "KES"),
      new Country("KG", "Kyrgyzstan", "+996", R.drawable.flag_kg, "KGS"),
      new Country("KH", "Cambodia", "+855", R.drawable.flag_kh, "KHR"),
      new Country("KI", "Kiribati", "+686", R.drawable.flag_ki, "AUD"),
      new Country("KM", "Comoros", "+269", R.drawable.flag_km, "KMF"),
      new Country("KN", "Saint Kitts and Nevis", "+1", R.drawable.flag_kn, "XCD"),
      new Country("KP", "North Korea", "+850", R.drawable.flag_kp, "KPW"),
      new Country("KR", "South Korea", "+82", R.drawable.flag_kr, "KRW"),
      new Country("KW", "Kuwait", "+965", R.drawable.flag_kw, "KWD"),
      new Country("KY", "Cayman Islands", "+345", R.drawable.flag_ky, "KYD"),
      new Country("KZ", "Kazakhstan", "+7", R.drawable.flag_kz, "KZT"),
      new Country("LA", "Lao People's Democratic Republic", "+856", R.drawable.flag_la, "LAK"),
      new Country("LB", "Lebanon", "+961", R.drawable.flag_lb, "LBP"),
      new Country("LC", "Saint Lucia", "+1", R.drawable.flag_lc, "XCD"),
      new Country("LI", "Liechtenstein", "+423", R.drawable.flag_li, "CHF"),
      new Country("LK", "Sri Lanka", "+94", R.drawable.flag_lk, "LKR"),
      new Country("LR", "Liberia", "+231", R.drawable.flag_lr, "LRD"),
      new Country("LS", "Lesotho", "+266", R.drawable.flag_ls, "LSL"),
      new Country("LT", "Lithuania", "+370", R.drawable.flag_lt, "LTL"),
      new Country("LU", "Luxembourg", "+352", R.drawable.flag_lu, "EUR"),
      new Country("LV", "Latvia", "+371", R.drawable.flag_lv, "LVL"),
      new Country("LY", "Libyan Arab Jamahiriya", "+218", R.drawable.flag_ly, "LYD"),
      new Country("MA", "Morocco", "+212", R.drawable.flag_ma, "MAD"),
      new Country("MC", "Monaco", "+377", R.drawable.flag_mc, "EUR"),
      new Country("MD", "Moldova, Republic of", "+373", R.drawable.flag_md, "MDL"),
      new Country("ME", "Montenegro", "+382", R.drawable.flag_me, "EUR"),
      new Country("MF", "Saint Martin", "+590", R.drawable.flag_mf, "EUR"),
      new Country("MG", "Madagascar", "+261", R.drawable.flag_mg, "MGA"),
      new Country("MH", "Marshall Islands", "+692", R.drawable.flag_mh, "USD"),
      new Country("MK", "Macedonia, The Former Yugoslav Republic of", "+389", R.drawable.flag_mk,
          "MKD"),
      new Country("ML", "Mali", "+223", R.drawable.flag_ml, "XOF"),
      new Country("MM", "Myanmar", "+95", R.drawable.flag_mm, "MMK"),
      new Country("MN", "Mongolia", "+976", R.drawable.flag_mn, "MNT"),
      new Country("MO", "Macao", "+853", R.drawable.flag_mo, "MOP"),
      new Country("MP", "Northern Mariana Islands", "+1", R.drawable.flag_mp, "USD"),
      new Country("MQ", "Martinique", "+596", R.drawable.flag_mq, "EUR"),
      new Country("MR", "Mauritania", "+222", R.drawable.flag_mr, "MRO"),
      new Country("MS", "Montserrat", "+1", R.drawable.flag_ms, "XCD"),
      new Country("MT", "Malta", "+356", R.drawable.flag_mt, "EUR"),
      new Country("MU", "Mauritius", "+230", R.drawable.flag_mu, "MUR"),
      new Country("MV", "Maldives", "+960", R.drawable.flag_mv, "MVR"),
      new Country("MW", "Malawi", "+265", R.drawable.flag_mw, "MWK"),
      new Country("MX", "Mexico", "+52", R.drawable.flag_mx, "MXN"),
      new Country("MY", "Malaysia", "+60", R.drawable.flag_my, "MYR"),
      new Country("MZ", "Mozambique", "+258", R.drawable.flag_mz, "MZN"),
      new Country("NA", "Namibia", "+264", R.drawable.flag_na, "NAD"),
      new Country("NC", "New Caledonia", "+687", R.drawable.flag_nc, "XPF"),
      new Country("NE", "Niger", "+227", R.drawable.flag_ne, "XOF"),
      new Country("NF", "Norfolk Island", "+672", R.drawable.flag_nf, "AUD"),
      new Country("NG", "Nigeria", "+234", R.drawable.flag_ng, "NGN"),
      new Country("NI", "Nicaragua", "+505", R.drawable.flag_ni, "NIO"),
      new Country("NL", "Netherlands", "+31", R.drawable.flag_nl, "EUR"),
      new Country("NO", "Norway", "+47", R.drawable.flag_no, "NOK"),
      new Country("NP", "Nepal", "+977", R.drawable.flag_np, "NPR"),
      new Country("NR", "Nauru", "+674", R.drawable.flag_nr, "AUD"),
      new Country("NU", "Niue", "+683", R.drawable.flag_nu, "NZD"),
      new Country("NZ", "New Zealand", "+64", R.drawable.flag_nz, "NZD"),
      new Country("OM", "Oman", "+968", R.drawable.flag_om, "OMR"),
      new Country("PA", "Panama", "+507", R.drawable.flag_pa, "PAB"),
      new Country("PE", "Peru", "+51", R.drawable.flag_pe, "PEN"),
      new Country("PF", "French Polynesia", "+689", R.drawable.flag_pf, "XPF"),
      new Country("PG", "Papua New Guinea", "+675", R.drawable.flag_pg, "PGK"),
      new Country("PH", "Philippines", "+63", R.drawable.flag_ph, "PHP"),
      new Country("PK", "Pakistan", "+92", R.drawable.flag_pk, "PKR"),
      new Country("PL", "Poland", "+48", R.drawable.flag_pl, "PLN"),
      new Country("PM", "Saint Pierre and Miquelon", "+508", R.drawable.flag_pm, "EUR"),
      new Country("PN", "Pitcairn", "+872", R.drawable.flag_pn, "NZD"),
      new Country("PR", "Puerto Rico", "+1", R.drawable.flag_pr, "USD"),
      new Country("PS", "Palestinian Territory, Occupied", "+970", R.drawable.flag_ps, "ILS"),
      new Country("PT", "Portugal", "+351", R.drawable.flag_pt, "EUR"),
      new Country("PW", "Palau", "+680", R.drawable.flag_pw, "USD"),
      new Country("PY", "Paraguay", "+595", R.drawable.flag_py, "PYG"),
      new Country("QA", "Qatar", "+974", R.drawable.flag_qa, "QAR"),
      new Country("RE", "Reunion", "+262", R.drawable.flag_re, "EUR"),
      new Country("RO", "Romania", "+40", R.drawable.flag_ro, "RON"),
      new Country("RS", "Serbia", "+381", R.drawable.flag_rs, "RSD"),
      new Country("RU", "Russia", "+7", R.drawable.flag_ru, "RUB"),
      new Country("RW", "Rwanda", "+250", R.drawable.flag_rw, "RWF"),
      new Country("SA", "Saudi Arabia", "+966", R.drawable.flag_sa, "SAR"),
      new Country("SB", "Solomon Islands", "+677", R.drawable.flag_sb, "SBD"),
      new Country("SC", "Seychelles", "+248", R.drawable.flag_sc, "SCR"),
      new Country("SD", "Sudan", "+249", R.drawable.flag_sd, "SDG"),
      new Country("SE", "Sweden", "+46", R.drawable.flag_se, "SEK"),
      new Country("SG", "Singapore", "+65", R.drawable.flag_sg, "SGD"),
      new Country("SH", "Saint Helena, Ascension and Tristan Da Cunha", "+290", R.drawable.flag_sh,
          "SHP"),
      new Country("SI", "Slovenia", "+386", R.drawable.flag_si, "EUR"),
      new Country("SJ", "Svalbard and Jan Mayen", "+47", R.drawable.flag_sj, "NOK"),
      new Country("SK", "Slovakia", "+421", R.drawable.flag_sk, "EUR"),
      new Country("SL", "Sierra Leone", "+232", R.drawable.flag_sl, "SLL"),
      new Country("SM", "San Marino", "+378", R.drawable.flag_sm, "EUR"),
      new Country("SN", "Senegal", "+221", R.drawable.flag_sn, "XOF"),
      new Country("SO", "Somalia", "+252", R.drawable.flag_so, "SOS"),
      new Country("SR", "Suriname", "+597", R.drawable.flag_sr, "SRD"),
      new Country("SS", "South Sudan", "+211", R.drawable.flag_ss, "SSP"),
      new Country("ST", "Sao Tome and Principe", "+239", R.drawable.flag_st, "STD"),
      new Country("SV", "El Salvador", "+503", R.drawable.flag_sv, "SVC"),
      new Country("SX", "Sint Maarten", "+1", R.drawable.flag_sx, "ANG"),
      new Country("SY", "Syrian Arab Republic", "+963", R.drawable.flag_sy, "SYP"),
      new Country("SZ", "Swaziland", "+268", R.drawable.flag_sz, "SZL"),
      new Country("TC", "Turks and Caicos Islands", "+1", R.drawable.flag_tc, "USD"),
      new Country("TD", "Chad", "+235", R.drawable.flag_td, "XAF"),
      new Country("TF", "French Southern Territories", "+262", R.drawable.flag_tf, "EUR"),
      new Country("TG", "Togo", "+228", R.drawable.flag_tg, "XOF"),
      new Country("TH", "Thailand", "+66", R.drawable.flag_th, "THB"),
      new Country("TJ", "Tajikistan", "+992", R.drawable.flag_tj, "TJS"),
      new Country("TK", "Tokelau", "+690", R.drawable.flag_tk, "NZD"),
      new Country("TL", "East Timor", "+670", R.drawable.flag_tl, "USD"),
      new Country("TM", "Turkmenistan", "+993", R.drawable.flag_tm, "TMT"),
      new Country("TN", "Tunisia", "+216", R.drawable.flag_tn, "TND"),
      new Country("TO", "Tonga", "+676", R.drawable.flag_to, "TOP"),
      new Country("TR", "Turkey", "+90", R.drawable.flag_tr, "TRY"),
      new Country("TT", "Trinidad and Tobago", "+1", R.drawable.flag_tt, "TTD"),
      new Country("TV", "Tuvalu", "+688", R.drawable.flag_tv, "AUD"),
      new Country("TW", "Taiwan", "+886", R.drawable.flag_tw, "TWD"),
      new Country("TZ", "Tanzania, United Republic of", "+255", R.drawable.flag_tz, "TZS"),
      new Country("UA", "Ukraine", "+380", R.drawable.flag_ua, "UAH"),
      new Country("UG", "Uganda", "+256", R.drawable.flag_ug, "UGX"),
      new Country("UM", "U.S. Minor Outlying Islands", "+1", R.drawable.flag_um, "USD"),
      new Country("US", "United States", "+1", R.drawable.flag_us, "USD"),
      new Country("UY", "Uruguay", "+598", R.drawable.flag_uy, "UYU"),
      new Country("UZ", "Uzbekistan", "+998", R.drawable.flag_uz, "UZS"),
      new Country("VA", "Holy See (Vatican City State)", "+379", R.drawable.flag_va, "EUR"),
      new Country("VC", "Saint Vincent and the Grenadines", "+1", R.drawable.flag_vc, "XCD"),
      new Country("VE", "Venezuela, Bolivarian Republic of", "+58", R.drawable.flag_ve, "VEF"),
      new Country("VG", "Virgin Islands, British", "+1", R.drawable.flag_vg, "USD"),
      new Country("VI", "Virgin Islands, U.S.", "+1", R.drawable.flag_vi, "USD"),
      new Country("VN", "Vietnam", "+84", R.drawable.flag_vn, "VND"),
      new Country("VU", "Vanuatu", "+678", R.drawable.flag_vu, "VUV"),
      new Country("WF", "Wallis and Futuna", "+681", R.drawable.flag_wf, "XPF"),
      new Country("WS", "Samoa", "+685", R.drawable.flag_ws, "WST"),
      new Country("XK", "Kosovo", "+383", R.drawable.flag_xk, "EUR"),
      new Country("YE", "Yemen", "+967", R.drawable.flag_ye, "YER"),
      new Country("YT", "Mayotte", "+262", R.drawable.flag_yt, "EUR"),
      new Country("ZA", "South Africa", "+27", R.drawable.flag_za, "ZAR"),
      new Country("ZM", "Zambia", "+260", R.drawable.flag_zm, "ZMW"),
      new Country("ZW", "Zimbabwe", "+263", R.drawable.flag_zw, "USD"),
  };
  // endregion

  // region Variables
  public static final int SORT_BY_NONE = 0;
  public static final int SORT_BY_NAME = 1;
  public static final int SORT_BY_ISO = 2;
  public static final int SORT_BY_DIAL_CODE = 3;
  private static final String COUNTRY_TAG = "COUNTRY_PICKER";

  private Context context;
  private int sortBy = SORT_BY_NONE;
  private OnCountryPickerListener onCountryPickerListener;
  private boolean canSearch = true;

  private List<Country> countries;
  // endregion

  // region Constructors
  private CountryPicker() {
  }

  CountryPicker(Builder builder) {
    sortBy = builder.sortBy;
    if (builder.onCountryPickerListener != null) {
      onCountryPickerListener = builder.onCountryPickerListener;
    }
    context = builder.context;
    canSearch = builder.canSearch;
    countries = new ArrayList<>(Arrays.asList(COUNTRIES));
    sortCountries(countries);
  }
  // endregion

  // region Listeners
  @Override public void sortCountries(@NonNull List<Country> countries) {
    if (sortBy == SORT_BY_NAME) {
      Collections.sort(countries, new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
          return country1.getName().trim().compareToIgnoreCase(country2.getName().trim());
        }
      });
    } else if (sortBy == SORT_BY_ISO) {
      Collections.sort(countries, new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
          return country1.getCode().trim().compareToIgnoreCase(country2.getCode().trim());
        }
      });
    } else if (sortBy == SORT_BY_DIAL_CODE) {
      Collections.sort(countries, new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
          return country1.getDialCode().trim().compareToIgnoreCase(country2.getDialCode().trim());
        }
      });
    }
  }

  @Override public List<Country> getAllCountries() {
    return countries;
  }

  @Override public boolean canSearch() {
    return canSearch;
  }

  // endregion

  // region Utility Methods
  public void showDialog(@NonNull FragmentManager supportFragmentManager) {
    if (countries == null || countries.isEmpty()) {
      throw new IllegalArgumentException(context.getString(R.string.error_no_countries_found));
    } else {
      CountryPickerDialog countryPickerDialog = CountryPickerDialog.newInstance();
      if (onCountryPickerListener != null) {
        countryPickerDialog.setCountryPickerListener(onCountryPickerListener);
      }
      countryPickerDialog.setDialogInteractionListener(this);
      countryPickerDialog.show(supportFragmentManager, COUNTRY_TAG);
    }
  }

  public void setCountries(@NonNull List<Country> countries) {
    this.countries.clear();
    this.countries.addAll(countries);
    sortCountries(this.countries);
  }

  public Country getCountryFromSIM() {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if (telephonyManager != null
        && telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
      return getCountryByISO(telephonyManager.getSimCountryIso());
    }
    return null;
  }

  public Country getCountryByLocale(@NonNull Locale locale) {
    String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
    return getCountryByISO(countryIsoCode);
  }

  public Country getCountryByName(@NonNull String countryName) {
    List<Country> countries = getAllCountries();
    Collections.sort(countries, new NameComparator());
    Country country = new Country();
    country.setName(countryName);
    int i = Collections.binarySearch(countries, country, new NameComparator());
    if (i < 0) {
      return null;
    } else {
      return countries.get(i);
    }
  }

  public Country getCountryByISO(@NonNull String countryIsoCode) {
    List<Country> countries = getAllCountries();
    Collections.sort(countries, new ISOCodeComparator());
    Country country = new Country();
    country.setCode(countryIsoCode);
    int i = Collections.binarySearch(countries, country, new ISOCodeComparator());
    if (i < 0) {
      return null;
    } else {
      return countries.get(i);
    }
  }
  // endregion

  // region Builder
  public static class Builder {
    private Context context;
    private int sortBy = SORT_BY_NONE;
    private boolean canSearch = true;
    private OnCountryPickerListener onCountryPickerListener;

    public Builder with(@NonNull Context context) {
      this.context = context;
      return this;
    }

    public Builder sortBy(@NonNull int sortBy) {
      this.sortBy = sortBy;
      return this;
    }

    public Builder listener(@NonNull OnCountryPickerListener onCountryPickerListener) {
      this.onCountryPickerListener = onCountryPickerListener;
      return this;
    }

    public Builder canSearch(@NonNull boolean canSearch) {
      this.canSearch = canSearch;
      return this;
    }

    public CountryPicker build() {
      return new CountryPicker(this);
    }
  }
  // endregion

  // region Comparators
  public static class ISOCodeComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country nextCountry) {
      return country.getCode().compareToIgnoreCase(nextCountry.getCode());
    }
  }

  public static class NameComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country nextCountry) {
      return country.getName().compareToIgnoreCase(nextCountry.getName());
    }
  }
  // endregion
}
