package com.mukesh.countrypicker;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 */

public class Country {
  public static final Country[] COUNTRIES = {
      new Country("AD", "Andorra", "+376", R.drawable.flag_ad),
      new Country("AE", "United Arab Emirates", "+971", R.drawable.flag_ae),
      new Country("AF", "Afghanistan", "+93", R.drawable.flag_af),
      new Country("AG", "Antigua and Barbuda", "+1", R.drawable.flag_ag),
      new Country("AI", "Anguilla", "+1", R.drawable.flag_ai),
      new Country("AL", "Albania", "+355", R.drawable.flag_al),
      new Country("AM", "Armenia", "+374", R.drawable.flag_am),
      new Country("AO", "Angola", "+244", R.drawable.flag_ao),
      new Country("AQ", "Antarctica", "+672", R.drawable.flag_aq),
      new Country("AR", "Argentina", "+54", R.drawable.flag_ar),
      new Country("AS", "American Samoa", "+1", R.drawable.flag_as),
      new Country("AT", "Austria", "+43", R.drawable.flag_at),
      new Country("AU", "Australia", "+61", R.drawable.flag_au),
      new Country("AW", "Aruba", "+297", R.drawable.flag_aw),
      new Country("AX", "Åland Islands", "+358", R.drawable.flag_ax),
      new Country("AZ", "Azerbaijan", "+994", R.drawable.flag_az),
      new Country("BA", "Bosnia and Herzegovina", "+387", R.drawable.flag_ba),
      new Country("BB", "Barbados", "+1", R.drawable.flag_bb),
      new Country("BD", "Bangladesh", "+880", R.drawable.flag_bd),
      new Country("BE", "Belgium", "+32", R.drawable.flag_be),
      new Country("BF", "Burkina Faso", "+226", R.drawable.flag_bf),
      new Country("BG", "Bulgaria", "+359", R.drawable.flag_bg),
      new Country("BH", "Bahrain", "+973", R.drawable.flag_bh),
      new Country("BI", "Burundi", "+257", R.drawable.flag_bi),
      new Country("BJ", "Benin", "+229", R.drawable.flag_bj),
      new Country("BL", "Saint Barthélemy", "+590", R.drawable.flag_bl),
      new Country("BM", "Bermuda", "+1", R.drawable.flag_bm),
      new Country("BN", "Brunei Darussalam", "+673", R.drawable.flag_bn),
      new Country("BO", "Bolivia, Plurinational State of", "+591", R.drawable.flag_bo),
      new Country("BQ", "Bonaire", "+599", R.drawable.flag_bq),
      new Country("BR", "Brazil", "+55", R.drawable.flag_br),
      new Country("BS", "Bahamas", "+1", R.drawable.flag_bs),
      new Country("BT", "Bhutan", "+975", R.drawable.flag_bt),
      new Country("BV", "Bouvet Island", "+47", R.drawable.flag_bv),
      new Country("BW", "Botswana", "+267", R.drawable.flag_bw),
      new Country("BY", "Belarus", "+375", R.drawable.flag_by),
      new Country("BZ", "Belize", "+501", R.drawable.flag_bz),
      new Country("CA", "Canada", "+1", R.drawable.flag_ca),
      new Country("CC", "Cocos (Keeling) Islands", "+61", R.drawable.flag_cc),
      new Country("CD", "Congo, The Democratic Republic of the", "+243", R.drawable.flag_cd),
      new Country("CF", "Central African Republic", "+236", R.drawable.flag_cf),
      new Country("CG", "Congo", "+242", R.drawable.flag_cg),
      new Country("CH", "Switzerland", "+41", R.drawable.flag_ch),
      new Country("CI", "Ivory Coast", "+225", R.drawable.flag_ci),
      new Country("CK", "Cook Islands", "+682", R.drawable.flag_ck),
      new Country("CL", "Chile", "+56", R.drawable.flag_cl),
      new Country("CM", "Cameroon", "+237", R.drawable.flag_cm),
      new Country("CN", "China", "+86", R.drawable.flag_cn),
      new Country("CO", "Colombia", "+57", R.drawable.flag_co),
      new Country("CR", "Costa Rica", "+506", R.drawable.flag_cr),
      new Country("CU", "Cuba", "+53", R.drawable.flag_cu),
      new Country("CV", "Cape Verde", "+238", R.drawable.flag_cv),
      new Country("CW", "Curacao", "+599", R.drawable.flag_cw),
      new Country("CX", "Christmas Island", "+61", R.drawable.flag_cx),
      new Country("CY", "Cyprus", "+357", R.drawable.flag_cy),
      new Country("CZ", "Czech Republic", "+420", R.drawable.flag_cz),
      new Country("DE", "Germany", "+49", R.drawable.flag_de),
      new Country("DJ", "Djibouti", "+253", R.drawable.flag_dj),
      new Country("DK", "Denmark", "+45", R.drawable.flag_dk),
      new Country("DM", "Dominica", "+1", R.drawable.flag_dm),
      new Country("DO", "Dominican Republic", "+1", R.drawable.flag_do),
      new Country("DZ", "Algeria", "+213", R.drawable.flag_dz),
      new Country("EC", "Ecuador", "+593", R.drawable.flag_ec),
      new Country("EE", "Estonia", "+372", R.drawable.flag_ee),
      new Country("EG", "Egypt", "+20", R.drawable.flag_eg),
      new Country("EH", "Western Sahara", "+212", R.drawable.flag_eh),
      new Country("ER", "Eritrea", "+291", R.drawable.flag_er),
      new Country("ES", "Spain", "+34", R.drawable.flag_es),
      new Country("ET", "Ethiopia", "+251", R.drawable.flag_et),
      new Country("FI", "Finland", "+358", R.drawable.flag_fi),
      new Country("FJ", "Fiji", "+679", R.drawable.flag_fj),
      new Country("FK", "Falkland Islands (Malvinas)", "+500", R.drawable.flag_fk),
      new Country("FM", "Micronesia, Federated States of", "+691", R.drawable.flag_fm),
      new Country("FO", "Faroe Islands", "+298", R.drawable.flag_fo),
      new Country("FR", "France", "+33", R.drawable.flag_fr),
      new Country("GA", "Gabon", "+241", R.drawable.flag_ga),
      new Country("GB", "United Kingdom", "+44", R.drawable.flag_gb),
      new Country("GD", "Grenada", "+1", R.drawable.flag_gd),
      new Country("GE", "Georgia", "+995", R.drawable.flag_ge),
      new Country("GF", "French Guiana", "+594", R.drawable.flag_gf),
      new Country("GG", "Guernsey", "+44", R.drawable.flag_gg),
      new Country("GH", "Ghana", "+233", R.drawable.flag_gh),
      new Country("GI", "Gibraltar", "+350", R.drawable.flag_gi),
      new Country("GL", "Greenland", "+299", R.drawable.flag_gl),
      new Country("GM", "Gambia", "+220", R.drawable.flag_gm),
      new Country("GN", "Guinea", "+224", R.drawable.flag_gn),
      new Country("GP", "Guadeloupe", "+590", R.drawable.flag_gp),
      new Country("GQ", "Equatorial Guinea", "+240", R.drawable.flag_gq),
      new Country("GR", "Greece", "+30", R.drawable.flag_gr),
      new Country("GS", "South Georgia and the South Sandwich Islands", "+500", R.drawable.flag_gs),
      new Country("GT", "Guatemala", "+502", R.drawable.flag_gt),
      new Country("GU", "Guam", "+1", R.drawable.flag_gu),
      new Country("GW", "Guinea-Bissau", "+245", R.drawable.flag_gw),
      new Country("GY", "Guyana", "+595", R.drawable.flag_gy),
      new Country("HK", "Hong Kong", "+852", R.drawable.flag_hk),
      new Country("HM", "Heard Island and McDonald Islands", "", R.drawable.flag_hm),
      new Country("HN", "Honduras", "+504", R.drawable.flag_hn),
      new Country("HR", "Croatia", "+385", R.drawable.flag_hr),
      new Country("HT", "Haiti", "+509", R.drawable.flag_ht),
      new Country("HU", "Hungary", "+36", R.drawable.flag_hu),
      new Country("ID", "Indonesia", "+62", R.drawable.flag_id),
      new Country("IE", "Ireland", "+353", R.drawable.flag_ie),
      new Country("IL", "Israel", "+972", R.drawable.flag_il),
      new Country("IM", "Isle of Man", "+44", R.drawable.flag_im),
      new Country("IN", "India", "+91", R.drawable.flag_in),
      new Country("IO", "British Indian Ocean Territory", "+246", R.drawable.flag_io),
      new Country("IQ", "Iraq", "+964", R.drawable.flag_iq),
      new Country("IR", "Iran, Islamic Republic of", "+98", R.drawable.flag_ir),
      new Country("IS", "Iceland", "+354", R.drawable.flag_is),
      new Country("IT", "Italy", "+39", R.drawable.flag_it),
      new Country("JE", "Jersey", "+44", R.drawable.flag_je),
      new Country("JM", "Jamaica", "+1", R.drawable.flag_jm),
      new Country("JO", "Jordan", "+962", R.drawable.flag_jo),
      new Country("JP", "Japan", "+81", R.drawable.flag_jp),
      new Country("KE", "Kenya", "+254", R.drawable.flag_ke),
      new Country("KG", "Kyrgyzstan", "+996", R.drawable.flag_kg),
      new Country("KH", "Cambodia", "+855", R.drawable.flag_kh),
      new Country("KI", "Kiribati", "+686", R.drawable.flag_ki),
      new Country("KM", "Comoros", "+269", R.drawable.flag_km),
      new Country("KN", "Saint Kitts and Nevis", "+1", R.drawable.flag_kn),
      new Country("KP", "North Korea", "+850", R.drawable.flag_kp),
      new Country("KR", "South Korea", "+82", R.drawable.flag_kr),
      new Country("KW", "Kuwait", "+965", R.drawable.flag_kw),
      new Country("KY", "Cayman Islands", "+345", R.drawable.flag_ky),
      new Country("KZ", "Kazakhstan", "+7", R.drawable.flag_kz),
      new Country("LA", "Lao People's Democratic Republic", "+856", R.drawable.flag_la),
      new Country("LB", "Lebanon", "+961", R.drawable.flag_lb),
      new Country("LC", "Saint Lucia", "+1", R.drawable.flag_lc),
      new Country("LI", "Liechtenstein", "+423", R.drawable.flag_li),
      new Country("LK", "Sri Lanka", "+94", R.drawable.flag_lk),
      new Country("LR", "Liberia", "+231", R.drawable.flag_lr),
      new Country("LS", "Lesotho", "+266", R.drawable.flag_ls),
      new Country("LT", "Lithuania", "+370", R.drawable.flag_lt),
      new Country("LU", "Luxembourg", "+352", R.drawable.flag_lu),
      new Country("LV", "Latvia", "+371", R.drawable.flag_lv),
      new Country("LY", "Libyan Arab Jamahiriya", "+218", R.drawable.flag_ly),
      new Country("MA", "Morocco", "+212", R.drawable.flag_ma),
      new Country("MC", "Monaco", "+377", R.drawable.flag_mc),
      new Country("MD", "Moldova, Republic of", "+373", R.drawable.flag_md),
      new Country("ME", "Montenegro", "+382", R.drawable.flag_me),
      new Country("MF", "Saint Martin", "+590", R.drawable.flag_mf),
      new Country("MG", "Madagascar", "+261", R.drawable.flag_mg),
      new Country("MH", "Marshall Islands", "+692", R.drawable.flag_mh),
      new Country("MK", "Macedonia, The Former Yugoslav Republic of", "+389", R.drawable.flag_mk),
      new Country("ML", "Mali", "+223", R.drawable.flag_ml),
      new Country("MM", "Myanmar", "+95", R.drawable.flag_mm),
      new Country("MN", "Mongolia", "+976", R.drawable.flag_mn),
      new Country("MO", "Macao", "+853", R.drawable.flag_mo),
      new Country("MP", "Northern Mariana Islands", "+1", R.drawable.flag_mp),
      new Country("MQ", "Martinique", "+596", R.drawable.flag_mq),
      new Country("MR", "Mauritania", "+222", R.drawable.flag_mr),
      new Country("MS", "Montserrat", "+1", R.drawable.flag_ms),
      new Country("MT", "Malta", "+356", R.drawable.flag_mt),
      new Country("MU", "Mauritius", "+230", R.drawable.flag_mu),
      new Country("MV", "Maldives", "+960", R.drawable.flag_mv),
      new Country("MW", "Malawi", "+265", R.drawable.flag_mw),
      new Country("MX", "Mexico", "+52", R.drawable.flag_mx),
      new Country("MY", "Malaysia", "+60", R.drawable.flag_my),
      new Country("MZ", "Mozambique", "+258", R.drawable.flag_mz),
      new Country("NA", "Namibia", "+264", R.drawable.flag_na),
      new Country("NC", "New Caledonia", "+687", R.drawable.flag_nc),
      new Country("NE", "Niger", "+227", R.drawable.flag_ne),
      new Country("NF", "Norfolk Island", "+672", R.drawable.flag_nf),
      new Country("NG", "Nigeria", "+234", R.drawable.flag_ng),
      new Country("NI", "Nicaragua", "+505", R.drawable.flag_ni),
      new Country("NL", "Netherlands", "+31", R.drawable.flag_nl),
      new Country("NO", "Norway", "+47", R.drawable.flag_no),
      new Country("NP", "Nepal", "+977", R.drawable.flag_np),
      new Country("NR", "Nauru", "+674", R.drawable.flag_nr),
      new Country("NU", "Niue", "+683", R.drawable.flag_nu),
      new Country("NZ", "New Zealand", "+64", R.drawable.flag_nz),
      new Country("OM", "Oman", "+968", R.drawable.flag_om),
      new Country("PA", "Panama", "+507", R.drawable.flag_pa),
      new Country("PE", "Peru", "+51", R.drawable.flag_pe),
      new Country("PF", "French Polynesia", "+689", R.drawable.flag_pf),
      new Country("PG", "Papua New Guinea", "+675", R.drawable.flag_pg),
      new Country("PH", "Philippines", "+63", R.drawable.flag_ph),
      new Country("PK", "Pakistan", "+92", R.drawable.flag_pk),
      new Country("PL", "Poland", "+48", R.drawable.flag_pl),
      new Country("PM", "Saint Pierre and Miquelon", "+508", R.drawable.flag_pm),
      new Country("PN", "Pitcairn", "+872", R.drawable.flag_pn),
      new Country("PR", "Puerto Rico", "+1", R.drawable.flag_pr),
      new Country("PS", "Palestinian Territory, Occupied", "+970", R.drawable.flag_ps),
      new Country("PT", "Portugal", "+351", R.drawable.flag_pt),
      new Country("PW", "Palau", "+680", R.drawable.flag_pw),
      new Country("PY", "Paraguay", "+595", R.drawable.flag_py),
      new Country("QA", "Qatar", "+974", R.drawable.flag_qa),
      new Country("RE", "Réunion", "+262", R.drawable.flag_re),
      new Country("RO", "Romania", "+40", R.drawable.flag_ro),
      new Country("RS", "Serbia", "+381", R.drawable.flag_rs),
      new Country("RU", "Russia", "+7", R.drawable.flag_ru),
      new Country("RW", "Rwanda", "+250", R.drawable.flag_rw),
      new Country("SA", "Saudi Arabia", "+966", R.drawable.flag_sa),
      new Country("SB", "Solomon Islands", "+677", R.drawable.flag_sb),
      new Country("SC", "Seychelles", "+248", R.drawable.flag_sc),
      new Country("SD", "Sudan", "+249", R.drawable.flag_sd),
      new Country("SE", "Sweden", "+46", R.drawable.flag_se),
      new Country("SG", "Singapore", "+65", R.drawable.flag_sg),
      new Country("SH", "Saint Helena, Ascension and Tristan Da Cunha", "+290", R.drawable.flag_sh),
      new Country("SI", "Slovenia", "+386", R.drawable.flag_si),
      new Country("SJ", "Svalbard and Jan Mayen", "+47", R.drawable.flag_sj),
      new Country("SK", "Slovakia", "+421", R.drawable.flag_sk),
      new Country("SL", "Sierra Leone", "+232", R.drawable.flag_sl),
      new Country("SM", "San Marino", "+378", R.drawable.flag_sm),
      new Country("SN", "Senegal", "+221", R.drawable.flag_sn),
      new Country("SO", "Somalia", "+252", R.drawable.flag_so),
      new Country("SR", "Suriname", "+597", R.drawable.flag_sr),
      new Country("SS", "South Sudan", "+211", R.drawable.flag_ss),
      new Country("ST", "Sao Tome and Principe", "+239", R.drawable.flag_st),
      new Country("SV", "El Salvador", "+503", R.drawable.flag_sv),
      new Country("SX", "Sint Maarten", "+1", R.drawable.flag_sx),
      new Country("SY", "Syrian Arab Republic", "+963", R.drawable.flag_sy),
      new Country("SZ", "Swaziland", "+268", R.drawable.flag_sz),
      new Country("TC", "Turks and Caicos Islands", "+1", R.drawable.flag_tc),
      new Country("TD", "Chad", "+235", R.drawable.flag_td),
      new Country("TF", "French Southern Territories", "+262", R.drawable.flag_tf),
      new Country("TG", "Togo", "+228", R.drawable.flag_tg),
      new Country("TH", "Thailand", "+66", R.drawable.flag_th),
      new Country("TJ", "Tajikistan", "+992", R.drawable.flag_tj),
      new Country("TK", "Tokelau", "+690", R.drawable.flag_tk),
      new Country("TL", "East Timor", "+670", R.drawable.flag_tl),
      new Country("TM", "Turkmenistan", "+993", R.drawable.flag_tm),
      new Country("TN", "Tunisia", "+216", R.drawable.flag_tn),
      new Country("TO", "Tonga", "+676", R.drawable.flag_to),
      new Country("TR", "Turkey", "+90", R.drawable.flag_tr),
      new Country("TT", "Trinidad and Tobago", "+1", R.drawable.flag_tt),
      new Country("TV", "Tuvalu", "+688", R.drawable.flag_tv),
      new Country("TW", "Taiwan", "+886", R.drawable.flag_tw),
      new Country("TZ", "Tanzania, United Republic of", "+255", R.drawable.flag_tz),
      new Country("UA", "Ukraine", "+380", R.drawable.flag_ua),
      new Country("UG", "Uganda", "+256", R.drawable.flag_ug),
      new Country("UM", "U.S. Minor Outlying Islands", "", R.drawable.flag_um),
      new Country("US", "United States", "+1", R.drawable.flag_us),
      new Country("UY", "Uruguay", "+598", R.drawable.flag_uy),
      new Country("UZ", "Uzbekistan", "+998", R.drawable.flag_uz),
      new Country("VA", "Holy See (Vatican City State)", "+379", R.drawable.flag_va),
      new Country("VC", "Saint Vincent and the Grenadines", "+1", R.drawable.flag_vc),
      new Country("VE", "Venezuela, Bolivarian Republic of", "+58", R.drawable.flag_ve),
      new Country("VG", "Virgin Islands, British", "+1", R.drawable.flag_vg),
      new Country("VI", "Virgin Islands, U.S.", "+1", R.drawable.flag_vi),
      new Country("VN", "Viet Nam", "+84", R.drawable.flag_vn),
      new Country("VU", "Vanuatu", "+678", R.drawable.flag_vu),
      new Country("WF", "Wallis and Futuna", "+681", R.drawable.flag_wf),
      new Country("WS", "Samoa", "+685", R.drawable.flag_ws),
      new Country("XK", "Kosovo", "+383", R.drawable.flag_xk),
      new Country("YE", "Yemen", "+967", R.drawable.flag_ye),
      new Country("YT", "Mayotte", "+262", R.drawable.flag_yt),
      new Country("ZA", "South Africa", "+27", R.drawable.flag_za),
      new Country("ZM", "Zambia", "+260", R.drawable.flag_zm),
      new Country("ZW", "Zimbabwe", "+263", R.drawable.flag_zw),
  };

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

  ;


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

  public static List<Country> getAllCountries() {
    if (allCountriesList == null) {
      allCountriesList = Arrays.asList(COUNTRIES);
    }
    return allCountriesList;
  }

  public static Country getCountryByISO(String countryIsoCode) {
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