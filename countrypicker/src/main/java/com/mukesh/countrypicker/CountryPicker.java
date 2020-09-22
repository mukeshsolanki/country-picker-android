package com.mukesh.countrypicker;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukesh.countrypicker.listeners.BottomSheetInteractionListener;
import com.mukesh.countrypicker.listeners.OnCountryPickerListener;
import com.mukesh.countrypicker.listeners.OnItemClickListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CountryPicker implements BottomSheetInteractionListener, LifecycleObserver {

  // region Variables
  public static final int SORT_BY_NONE = 0;
  public static final int SORT_BY_NAME = 1;
  public static final int SORT_BY_ISO = 2;
  public static final int SORT_BY_DIAL_CODE = 3;
  public static final int THEME_OLD = 1;
  public static final int THEME_NEW = 2;
  private int theme;

  private int style;
  private Context context;
  private int sortBy = SORT_BY_NONE;
  private OnCountryPickerListener onCountryPickerListener;
  private boolean canSearch = true;

  private List<Country> countries;
  private EditText searchEditText;
  private RecyclerView countriesRecyclerView;
  private LinearLayout rootView;
  private int textColor;
  private int hintColor;
  private int backgroundColor;
  private int searchIconId;
  private Drawable searchIcon;
  private CountriesAdapter adapter;
  private List<Country> searchResults;
  private BottomSheetDialogView bottomSheetDialog;
  private Dialog dialog;
  // endregion

  // region Constructors
  private CountryPicker() {
  }

  CountryPicker(Builder builder) {
    sortBy = builder.sortBy;
    if (builder.onCountryPickerListener != null) {
      onCountryPickerListener = builder.onCountryPickerListener;
    }
    style = builder.style;
    context = builder.context;
    canSearch = builder.canSearch;
    theme = builder.theme;
    countries = constructCountries(context);
    sortCountries(countries);
  }
  // endregion

  //region construct countries

  private List<Country> constructCountries(Context context) {
    Country[] COUNTRIES = {
      new Country("AD", context.getString(R.string.andorra), "+376", R.drawable.flag_ad, "EUR"),
      new Country("AE", context.getString(R.string.united_arab_emirates), "+971", R.drawable.flag_ae, "AED"),
      new Country("AF", context.getString(R.string.afghanistan), "+93", R.drawable.flag_af, "AFN"),
      new Country("AG", context.getString(R.string.antigua_and_barbuda), "+1-268", R.drawable.flag_ag, "XCD"),
      new Country("AI", context.getString(R.string.anguilla), "+1-264", R.drawable.flag_ai, "XCD"),
      new Country("AL", context.getString(R.string.albania), "+355", R.drawable.flag_al, "ALL"),
      new Country("AM", context.getString(R.string.armenia), "+374", R.drawable.flag_am, "AMD"),
      new Country("AN", context.getString(R.string.netherlands_antilles), "+599",R.drawable.flag_an,"ANG"),
      new Country("AO", context.getString(R.string.angola), "+244", R.drawable.flag_ao, "AOA"),
      new Country("AQ", context.getString(R.string.antarctica), "+672", R.drawable.flag_aq, "USD"),
      new Country("AR", context.getString(R.string.argentina), "+54", R.drawable.flag_ar, "ARS"),
      new Country("AS", context.getString(R.string.american_samoa), "+1-684", R.drawable.flag_as, "USD"),
      new Country("AT", context.getString(R.string.austria), "+43", R.drawable.flag_at, "EUR"),
      new Country("AU", context.getString(R.string.australia), "+61", R.drawable.flag_au, "AUD"),
      new Country("AW", context.getString(R.string.aruba), "+297", R.drawable.flag_aw, "AWG"),
      new Country("AZ", context.getString(R.string.azerbaijan), "+994", R.drawable.flag_az, "AZN"),
      new Country("BA", context.getString(R.string.bosnia_and_herzegovina), "+387", R.drawable.flag_ba, "BAM"),
      new Country("BB", context.getString(R.string.barbados), "+1-246", R.drawable.flag_bb, "BBD"),
      new Country("BD", context.getString(R.string.bangladesh), "+880", R.drawable.flag_bd, "BDT"),
      new Country("BE", context.getString(R.string.belgium), "+32", R.drawable.flag_be, "EUR"),
      new Country("BF", context.getString(R.string.burkina_faso), "+226", R.drawable.flag_bf, "XOF"),
      new Country("BG", context.getString(R.string.bulgaria), "+359", R.drawable.flag_bg, "BGN"),
      new Country("BH", context.getString(R.string.bahrain), "+973", R.drawable.flag_bh, "BHD"),
      new Country("BI", context.getString(R.string.burundi), "+257", R.drawable.flag_bi, "BIF"),
      new Country("BJ", context.getString(R.string.benin), "+229", R.drawable.flag_bj, "XOF"),
      new Country("BL", context.getString(R.string.saint_barthelemy), "+590", R.drawable.flag_bl, "EUR"),
      new Country("BM", context.getString(R.string.bermuda), "+1-441", R.drawable.flag_bm, "BMD"),
      new Country("BN", context.getString(R.string.brunei), "+673", R.drawable.flag_bn, "BND"),
      new Country("BO", context.getString(R.string.bolivia), "+591", R.drawable.flag_bo, "BOB"),
      new Country("BR", context.getString(R.string.brazil), "+55", R.drawable.flag_br, "BRL"),
      new Country("BS", context.getString(R.string.bahamas), "+1-242", R.drawable.flag_bs, "BSD"),
      new Country("BT", context.getString(R.string.bhutan), "+975", R.drawable.flag_bt, "BTN"),
      new Country("BW", context.getString(R.string.botswana), "+267", R.drawable.flag_bw, "BWP"),
      new Country("BY", context.getString(R.string.belarus), "+375", R.drawable.flag_by, "BYR"),
      new Country("BZ", context.getString(R.string.belize), "+501", R.drawable.flag_bz, "BZD"),
      new Country("CA", context.getString(R.string.canada), "+1", R.drawable.flag_ca, "CAD"),
      new Country("CC", context.getString(R.string.cocos_islands), "+61", R.drawable.flag_cc, "AUD"),
      new Country("CD", context.getString(R.string.democratic_republic_of_the_congo), "+243", R.drawable.flag_cd, "CDF"),
      new Country("CF", context.getString(R.string.central_african_republic), "+236", R.drawable.flag_cf, "XAF"),
      new Country("CG", context.getString(R.string.republic_of_the_congo), "+242", R.drawable.flag_cg, "XAF"),
      new Country("CH", context.getString(R.string.switzerland), "+41", R.drawable.flag_ch, "CHF"),
      new Country("CI", context.getString(R.string.ivory_coast), "+225", R.drawable.flag_ci, "XOF"),
      new Country("CK", context.getString(R.string.cook_islands), "+682", R.drawable.flag_ck, "NZD"),
      new Country("CL", context.getString(R.string.chile), "+56", R.drawable.flag_cl, "CLP"),
      new Country("CM", context.getString(R.string.cameroon), "+237", R.drawable.flag_cm, "XAF"),
      new Country("CN", context.getString(R.string.china), "+86", R.drawable.flag_cn, "CNY"),
      new Country("CO", context.getString(R.string.colombia), "+57", R.drawable.flag_co, "COP"),
      new Country("CR", context.getString(R.string.costa_rica), "+506", R.drawable.flag_cr, "CRC"),
      new Country("CU", context.getString(R.string.cuba), "+53", R.drawable.flag_cu, "CUP"),
      new Country("CV", context.getString(R.string.cape_verde), "+238", R.drawable.flag_cv, "CVE"),
      new Country("CW", context.getString(R.string.curacao), "+599", R.drawable.flag_cw, "ANG"),
      new Country("CX", context.getString(R.string.christmas_island), "+61", R.drawable.flag_cx, "AUD"),
      new Country("CY", context.getString(R.string.cyprus), "+357", R.drawable.flag_cy, "EUR"),
      new Country("CZ", context.getString(R.string.czech_republic), "+420", R.drawable.flag_cz, "CZK"),
      new Country("DE", context.getString(R.string.germany), "+49", R.drawable.flag_de, "EUR"),
      new Country("DJ", context.getString(R.string.djibouti), "+253", R.drawable.flag_dj, "DJF"),
      new Country("DK", context.getString(R.string.denmark), "+45", R.drawable.flag_dk, "DKK"),
      new Country("DM", context.getString(R.string.dominica), "+1-767", R.drawable.flag_dm, "XCD"),
      new Country("DO", context.getString(R.string.dominican_republic), "+1-809, +1-829, +1-849", R.drawable.flag_do, "DOP"),
      new Country("DZ", context.getString(R.string.algeria), "+213", R.drawable.flag_dz, "DZD"),
      new Country("EC", context.getString(R.string.ecuador), "+593", R.drawable.flag_ec, "USD"),
      new Country("EE", context.getString(R.string.estonia), "+372", R.drawable.flag_ee, "EUR"),
      new Country("EG", context.getString(R.string.egypt), "+20", R.drawable.flag_eg, "EGP"),
      new Country("EH", context.getString(R.string.western_sahara), "+212", R.drawable.flag_eh, "MAD"),
      new Country("ER", context.getString(R.string.eritrea), "+291", R.drawable.flag_er, "ERN"),
      new Country("ES", context.getString(R.string.spain), "+34", R.drawable.flag_es, "EUR"),
      new Country("ET", context.getString(R.string.ethiopia), "+251", R.drawable.flag_et, "ETB"),
      new Country("FI", context.getString(R.string.finland), "+358", R.drawable.flag_fi, "EUR"),
      new Country("FJ", context.getString(R.string.fiji), "+679", R.drawable.flag_fj, "FJD"),
      new Country("FK", context.getString(R.string.falkland_islands), "+500", R.drawable.flag_fk, "FKP"),
      new Country("FM", context.getString(R.string.micronesia), "+691", R.drawable.flag_fm, "USD"),
      new Country("FO", context.getString(R.string.faroe_islands), "+298", R.drawable.flag_fo, "DKK"),
      new Country("FR", context.getString(R.string.france), "+33", R.drawable.flag_fr, "EUR"),
      new Country("GA", context.getString(R.string.gabon), "+241", R.drawable.flag_ga, "XAF"),
      new Country("GB", context.getString(R.string.united_kingdom), "+44", R.drawable.flag_gb, "GBP"),
      new Country("GD", context.getString(R.string.grenada), "+1-473", R.drawable.flag_gd, "XCD"),
      new Country("GE", context.getString(R.string.georgia), "+995", R.drawable.flag_ge, "GEL"),
      new Country("GG", context.getString(R.string.guernsey), "+44-1481", R.drawable.flag_gg, "GGP"),
      new Country("GH", context.getString(R.string.ghana), "+233", R.drawable.flag_gh, "GHS"),
      new Country("GI", context.getString(R.string.gibraltar), "+350", R.drawable.flag_gi, "GIP"),
      new Country("GL", context.getString(R.string.greenland), "+299", R.drawable.flag_gl, "DKK"),
      new Country("GM", context.getString(R.string.gambia), "+220", R.drawable.flag_gm, "GMD"),
      new Country("GN", context.getString(R.string.guinea), "+224", R.drawable.flag_gn, "GNF"),
      new Country("GQ", context.getString(R.string.equatorial_guinea), "+240", R.drawable.flag_gq, "XAF"),
      new Country("GR", context.getString(R.string.greece), "+30", R.drawable.flag_gr, "EUR"),
      new Country("GT", context.getString(R.string.guatemala), "+502", R.drawable.flag_gt, "GTQ"),
      new Country("GU", context.getString(R.string.guam), "+1-671", R.drawable.flag_gu, "USD"),
      new Country("GW", context.getString(R.string.guinea_bissau), "+245", R.drawable.flag_gw, "XOF"),
      new Country("GY", context.getString(R.string.guyana), "+592", R.drawable.flag_gy, "GYD"),
      new Country("HK", context.getString(R.string.hong_kong), "+852", R.drawable.flag_hk, "HKD"),
      new Country("HN", context.getString(R.string.honduras), "+504", R.drawable.flag_hn, "HNL"),
      new Country("HR", context.getString(R.string.croatia), "+385", R.drawable.flag_hr, "HRK"),
      new Country("HT", context.getString(R.string.haiti), "+509", R.drawable.flag_ht, "HTG"),
      new Country("HU", context.getString(R.string.hungary), "+36", R.drawable.flag_hu, "HUF"),
      new Country("ID", context.getString(R.string.indonesia), "+62", R.drawable.flag_id, "IDR"),
      new Country("IE", context.getString(R.string.ireland), "+353", R.drawable.flag_ie, "EUR"),
      new Country("IL", context.getString(R.string.israel), "+972", R.drawable.flag_il, "ILS"),
      new Country("IM", context.getString(R.string.isle_of_man), "+44-1624", R.drawable.flag_im, "GBP"),
      new Country("IN", context.getString(R.string.india), "+91", R.drawable.flag_in, "INR"),
      new Country("IO", context.getString(R.string.british_indian_ocean_territory), "+246", R.drawable.flag_io, "USD"),
      new Country("IQ", context.getString(R.string.iraq), "+964", R.drawable.flag_iq, "IQD"),
      new Country("IR", context.getString(R.string.iran), "+98", R.drawable.flag_ir, "IRR"),
      new Country("IS", context.getString(R.string.iceland), "+354", R.drawable.flag_is, "ISK"),
      new Country("IT", context.getString(R.string.italy), "+39", R.drawable.flag_it, "EUR"),
      new Country("JE", context.getString(R.string.jersey), "+44-1534", R.drawable.flag_je, "JEP"),
      new Country("JM", context.getString(R.string.jamaica), "+1-876", R.drawable.flag_jm, "JMD"),
      new Country("JO", context.getString(R.string.jordan), "+962", R.drawable.flag_jo, "JOD"),
      new Country("JP", context.getString(R.string.japan), "+81", R.drawable.flag_jp, "JPY"),
      new Country("KE", context.getString(R.string.kenya), "+254", R.drawable.flag_ke, "KES"),
      new Country("KG", context.getString(R.string.kyrgyzstan), "+996", R.drawable.flag_kg, "KGS"),
      new Country("KH", context.getString(R.string.cambodia), "+855", R.drawable.flag_kh, "KHR"),
      new Country("KI", context.getString(R.string.kiribati), "+686", R.drawable.flag_ki, "AUD"),
      new Country("KM", context.getString(R.string.comoros), "+269", R.drawable.flag_km, "KMF"),
      new Country("KN", context.getString(R.string.saint_kitts_and_nevis), "+1-869", R.drawable.flag_kn, "XCD"),
      new Country("KP", context.getString(R.string.north_korea), "+850", R.drawable.flag_kp, "KPW"),
      new Country("KR", context.getString(R.string.south_korea), "+82", R.drawable.flag_kr, "KRW"),
      new Country("KW", context.getString(R.string.kuwait), "+965", R.drawable.flag_kw, "KWD"),
      new Country("KY", context.getString(R.string.cayman_islands), "+1-345", R.drawable.flag_ky, "KYD"),
      new Country("KZ", context.getString(R.string.kazakhstan), "+7", R.drawable.flag_kz, "KZT"),
      new Country("LA", context.getString(R.string.laos), "+856", R.drawable.flag_la, "LAK"),
      new Country("LB", context.getString(R.string.lebanon), "+961", R.drawable.flag_lb, "LBP"),
      new Country("LC", context.getString(R.string.saint_lucia), "+1-758", R.drawable.flag_lc, "XCD"),
      new Country("LI", context.getString(R.string.liechtenstein), "+423", R.drawable.flag_li, "CHF"),
      new Country("LK", context.getString(R.string.sri_lanka), "+94", R.drawable.flag_lk, "LKR"),
      new Country("LR", context.getString(R.string.liberia), "+231", R.drawable.flag_lr, "LRD"),
      new Country("LS", context.getString(R.string.lesotho), "+266", R.drawable.flag_ls, "LSL"),
      new Country("LT", context.getString(R.string.lithuania), "+370", R.drawable.flag_lt, "LTL"),
      new Country("LU", context.getString(R.string.luxembourg), "+352", R.drawable.flag_lu, "EUR"),
      new Country("LV", context.getString(R.string.latvia), "+371", R.drawable.flag_lv, "LVL"),
      new Country("LY", context.getString(R.string.libya), "+218", R.drawable.flag_ly, "LYD"),
      new Country("MA", context.getString(R.string.morocco), "+212", R.drawable.flag_ma, "MAD"),
      new Country("MC", context.getString(R.string.monaco), "+377", R.drawable.flag_mc, "EUR"),
      new Country("MD", context.getString(R.string.moldova), "+373", R.drawable.flag_md, "MDL"),
      new Country("ME", context.getString(R.string.montenegro), "+382", R.drawable.flag_me, "EUR"),
      new Country("MF", context.getString(R.string.saint_martin), "+590", R.drawable.flag_mf, "EUR"),
      new Country("MG", context.getString(R.string.madagascar), "+261", R.drawable.flag_mg, "MGA"),
      new Country("MH", context.getString(R.string.marshall_islands), "+692", R.drawable.flag_mh, "USD"),
      new Country("MK", context.getString(R.string.macedonia), "+389", R.drawable.flag_mk, "MKD"),
      new Country("ML", context.getString(R.string.mali), "+223", R.drawable.flag_ml, "XOF"),
      new Country("MM", context.getString(R.string.myanmar), "+95", R.drawable.flag_mm, "MMK"),
      new Country("MN", context.getString(R.string.mongolia), "+976", R.drawable.flag_mn, "MNT"),
      new Country("MO", context.getString(R.string.macao), "+853", R.drawable.flag_mo, "MOP"),
      new Country("MP", context.getString(R.string.northern_mariana_islands), "+1-670", R.drawable.flag_mp, "USD"),
      new Country("MR", context.getString(R.string.mauritania), "+222", R.drawable.flag_mr, "MRO"),
      new Country("MS", context.getString(R.string.montserrat), "+1-664", R.drawable.flag_ms, "XCD"),
      new Country("MT", context.getString(R.string.malta), "+356", R.drawable.flag_mt, "EUR"),
      new Country("MU", context.getString(R.string.mauritius), "+230", R.drawable.flag_mu, "MUR"),
      new Country("MV", context.getString(R.string.maldives), "+960", R.drawable.flag_mv, "MVR"),
      new Country("MW", context.getString(R.string.malawi), "+265", R.drawable.flag_mw, "MWK"),
      new Country("MX", context.getString(R.string.mexico), "+52", R.drawable.flag_mx, "MXN"),
      new Country("MY", context.getString(R.string.malaysia), "+60", R.drawable.flag_my, "MYR"),
      new Country("MZ", context.getString(R.string.mozambique), "+258", R.drawable.flag_mz, "MZN"),
      new Country("NA", context.getString(R.string.namibia), "+264", R.drawable.flag_na, "NAD"),
      new Country("NC", context.getString(R.string.new_caledonia), "+687", R.drawable.flag_nc, "XPF"),
      new Country("NE", context.getString(R.string.niger), "+227", R.drawable.flag_ne, "XOF"),
      new Country("NG", context.getString(R.string.nigeria), "+234", R.drawable.flag_ng, "NGN"),
      new Country("NI", context.getString(R.string.nicaragua), "+505", R.drawable.flag_ni, "NIO"),
      new Country("NL", context.getString(R.string.netherlands), "+31", R.drawable.flag_nl, "EUR"),
      new Country("NO", context.getString(R.string.norway), "+47", R.drawable.flag_no, "NOK"),
      new Country("NP", context.getString(R.string.nepal), "+977", R.drawable.flag_np, "NPR"),
      new Country("NR", context.getString(R.string.nauru), "+674", R.drawable.flag_nr, "AUD"),
      new Country("NU", context.getString(R.string.niue), "+683", R.drawable.flag_nu, "NZD"),
      new Country("NZ", context.getString(R.string.new_zealand), "+64", R.drawable.flag_nz, "NZD"),
      new Country("OM", context.getString(R.string.oman), "+968", R.drawable.flag_om, "OMR"),
      new Country("PA", context.getString(R.string.panama), "+507", R.drawable.flag_pa, "PAB"),
      new Country("PE", context.getString(R.string.peru), "+51", R.drawable.flag_pe, "PEN"),
      new Country("PF", context.getString(R.string.french_polynesia), "+689", R.drawable.flag_pf, "XPF"),
      new Country("PG", context.getString(R.string.papua_new_guinea), "+675", R.drawable.flag_pg, "PGK"),
      new Country("PH", context.getString(R.string.philippines), "+63", R.drawable.flag_ph, "PHP"),
      new Country("PK", context.getString(R.string.pakistan), "+92", R.drawable.flag_pk, "PKR"),
      new Country("PL", context.getString(R.string.poland), "+48", R.drawable.flag_pl, "PLN"),
      new Country("PM", context.getString(R.string.saint_pierre_and_miquelon), "+508", R.drawable.flag_pm, "EUR"),
      new Country("PN", context.getString(R.string.pitcairn), "+64", R.drawable.flag_pn, "NZD"),
      new Country("PR", context.getString(R.string.puerto_rico), "+1-787, +1-939", R.drawable.flag_pr, "USD"),
      new Country("PS", context.getString(R.string.palestinian), "+970", R.drawable.flag_ps, "ILS"),
      new Country("PT", context.getString(R.string.portugal), "+351", R.drawable.flag_pt, "EUR"),
      new Country("PW", context.getString(R.string.palau), "+680", R.drawable.flag_pw, "USD"),
      new Country("PY", context.getString(R.string.paraguay), "+595", R.drawable.flag_py, "PYG"),
      new Country("QA", context.getString(R.string.qatar), "+974", R.drawable.flag_qa, "QAR"),
      new Country("RE", context.getString(R.string.reunion), "+262", R.drawable.flag_re, "EUR"),
      new Country("RO", context.getString(R.string.romania), "+40", R.drawable.flag_ro, "RON"),
      new Country("RS", context.getString(R.string.serbia), "+381", R.drawable.flag_rs, "RSD"),
      new Country("RU", context.getString(R.string.russia), "+7", R.drawable.flag_ru, "RUB"),
      new Country("RW", context.getString(R.string.rwanda), "+250", R.drawable.flag_rw, "RWF"),
      new Country("SA", context.getString(R.string.saudi_arabia), "+966", R.drawable.flag_sa, "SAR"),
      new Country("SB", context.getString(R.string.solomon_islands), "+677", R.drawable.flag_sb, "SBD"),
      new Country("SC", context.getString(R.string.seychelles), "+248", R.drawable.flag_sc, "SCR"),
      new Country("SD", context.getString(R.string.sudan), "+249", R.drawable.flag_sd, "SDG"),
      new Country("SE", context.getString(R.string.sweden), "+46", R.drawable.flag_se, "SEK"),
      new Country("SG", context.getString(R.string.singapore), "+65", R.drawable.flag_sg, "SGD"),
      new Country("SH", context.getString(R.string.saint_helena), "+290", R.drawable.flag_sh, "SHP"),
      new Country("SI", context.getString(R.string.slovenia), "+386", R.drawable.flag_si, "EUR"),
      new Country("SJ", context.getString(R.string.svalbard_and_jan_mayen), "+47", R.drawable.flag_sj, "NOK"),
      new Country("SK", context.getString(R.string.slovakia), "+421", R.drawable.flag_sk, "EUR"),
      new Country("SL", context.getString(R.string.sierra_leone), "+232", R.drawable.flag_sl, "SLL"),
      new Country("SM", context.getString(R.string.san_marino), "+378", R.drawable.flag_sm, "EUR"),
      new Country("SN", context.getString(R.string.senegal), "+221", R.drawable.flag_sn, "XOF"),
      new Country("SO", context.getString(R.string.somalia), "+252", R.drawable.flag_so, "SOS"),
      new Country("SR", context.getString(R.string.suriname), "+597", R.drawable.flag_sr, "SRD"),
      new Country("SS", context.getString(R.string.south_sudan), "+211", R.drawable.flag_ss, "SSP"),
      new Country("ST", context.getString(R.string.sao_tome_and_principe), "+239", R.drawable.flag_st, "STD"),
      new Country("SV", context.getString(R.string.el_salvador), "+503", R.drawable.flag_sv, "SVC"),
      new Country("SX", context.getString(R.string.sint_maarten), "+1-721", R.drawable.flag_sx, "ANG"),
      new Country("SY", context.getString(R.string.syria), "+963", R.drawable.flag_sy, "SYP"),
      new Country("SZ", context.getString(R.string.swaziland), "+268", R.drawable.flag_sz, "SZL"),
      new Country("TC", context.getString(R.string.turks_and_caicos_islands), "+1-649", R.drawable.flag_tc, "USD"),
      new Country("TD", context.getString(R.string.chad), "+235", R.drawable.flag_td, "XAF"),
      new Country("TG", context.getString(R.string.togo), "+228", R.drawable.flag_tg, "XOF"),
      new Country("TH", context.getString(R.string.thailand), "+66", R.drawable.flag_th, "THB"),
      new Country("TJ", context.getString(R.string.tajikistan), "+992", R.drawable.flag_tj, "TJS"),
      new Country("TK", context.getString(R.string.tokelau), "+690", R.drawable.flag_tk, "NZD"),
      new Country("TL", context.getString(R.string.east_timor), "+670", R.drawable.flag_tl, "USD"),
      new Country("TM", context.getString(R.string.turkmenistan), "+993", R.drawable.flag_tm, "TMT"),
      new Country("TN", context.getString(R.string.tunisia), "+216", R.drawable.flag_tn, "TND"),
      new Country("TO", context.getString(R.string.tonga), "+676", R.drawable.flag_to, "TOP"),
      new Country("TR", context.getString(R.string.turkey), "+90", R.drawable.flag_tr, "TRY"),
      new Country("TT", context.getString(R.string.trinidad_and_tobago), "+1-868", R.drawable.flag_tt, "TTD"),
      new Country("TV", context.getString(R.string.tuvalu), "+688", R.drawable.flag_tv, "AUD"),
      new Country("TW", context.getString(R.string.taiwan), "+886", R.drawable.flag_tw, "TWD"),
      new Country("TZ", context.getString(R.string.tanzania), "+255", R.drawable.flag_tz, "TZS"),
      new Country("UA", context.getString(R.string.ukraine), "+380", R.drawable.flag_ua, "UAH"),
      new Country("UG", context.getString(R.string.uganda), "+256", R.drawable.flag_ug, "UGX"),
      new Country("US", context.getString(R.string.united_states), "+1", R.drawable.flag_us, "USD"),
      new Country("UY", context.getString(R.string.uruguay), "+598", R.drawable.flag_uy, "UYU"),
      new Country("UZ", context.getString(R.string.uzbekistan), "+998", R.drawable.flag_uz, "UZS"),
      new Country("VA", context.getString(R.string.vatican), "+379", R.drawable.flag_va, "EUR"),
      new Country("VC", context.getString(R.string.saint_vincent_and_grenadines), "+1-784", R.drawable.flag_vc, "XCD"),
      new Country("VE", context.getString(R.string.venezuela), "+58", R.drawable.flag_ve, "VEF"),
      new Country("VG", context.getString(R.string.british_virgin_islands), "+1-284", R.drawable.flag_vg, "USD"),
      new Country("VI", context.getString(R.string.us_virgin_islands), "+1-340", R.drawable.flag_vi, "USD"),
      new Country("VN", context.getString(R.string.vietnam), "+84", R.drawable.flag_vn, "VND"),
      new Country("VU", context.getString(R.string.vanuatu), "+678", R.drawable.flag_vu, "VUV"),
      new Country("WF", context.getString(R.string.wallis_and_futuna), "+681", R.drawable.flag_wf, "XPF"),
      new Country("WS", context.getString(R.string.samoa), "+685", R.drawable.flag_ws, "WST"),
      new Country("XK", context.getString(R.string.kosovo), "+383", R.drawable.flag_xk, "EUR"),
      new Country("YE", context.getString(R.string.yemen), "+967", R.drawable.flag_ye, "YER"),
      new Country("YT", context.getString(R.string.mayotte), "+262", R.drawable.flag_yt, "EUR"),
      new Country("ZA", context.getString(R.string.south_africa), "+27", R.drawable.flag_za, "ZAR"),
      new Country("ZM", context.getString(R.string.zambia), "+260", R.drawable.flag_zm, "ZMW"),
      new Country("ZW", context.getString(R.string.zimbabwe), "+263", R.drawable.flag_zw, "USD")
    };

    return new ArrayList<>(Arrays.asList(COUNTRIES));
  }

  //endregion

  // region Listeners
  private void sortCountries(@NonNull List<Country> countries) {
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
  // endregion

  // region Utility Methods
  public void showDialog(@NonNull AppCompatActivity activity) {
    if (countries == null || countries.isEmpty()) {
      throw new IllegalArgumentException(context.getString(R.string.error_no_countries_found));
    } else {
      activity.getLifecycle().addObserver(this);
      dialog = new Dialog(activity);
      View dialogView = activity.getLayoutInflater().inflate(R.layout.country_picker, null);
      initiateUi(dialogView);
      setCustomStyle(dialogView);
      setSearchEditText();
      setupRecyclerView(dialogView);
      dialog.setContentView(dialogView);
      if (dialog.getWindow() != null) {
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(params);
        if (theme == THEME_NEW) {
          Drawable background =
              ContextCompat.getDrawable(context, R.drawable.ic_dialog_new_background);
          if (background != null) {
            background.setColorFilter(
                new PorterDuffColorFilter(backgroundColor, PorterDuff.Mode.SRC_ATOP));
          }
          rootView.setBackgroundDrawable(background);
          dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
      }
      dialog.show();
    }
  }

  // region BottomSheet Methods
  public void showBottomSheet(AppCompatActivity activity) {
    if (countries == null || countries.isEmpty()) {
      throw new IllegalArgumentException(context.getString(R.string.error_no_countries_found));
    } else {
      activity.getLifecycle().addObserver(this);
      bottomSheetDialog = BottomSheetDialogView.newInstance(theme);
      bottomSheetDialog.setListener(this);
      bottomSheetDialog.show(activity.getSupportFragmentManager(), "bottomsheet");
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  private void dismissDialogs() {
    if (bottomSheetDialog != null) {
      bottomSheetDialog.dismiss();
    }
    if (dialog != null) {
      dialog.dismiss();
    }
  }

  @Override public void setupRecyclerView(View sheetView) {
    searchResults = new ArrayList<>();
    searchResults.addAll(countries);
    sortCountries(searchResults);
    adapter = new CountriesAdapter(sheetView.getContext(), searchResults,
        new OnItemClickListener() {
          @Override public void onItemClicked(Country country) {
            if (onCountryPickerListener != null) {
              onCountryPickerListener.onSelectCountry(country);
              if (bottomSheetDialog != null) {
                bottomSheetDialog.dismiss();
              }
              if (dialog != null) {
                dialog.dismiss();
              }
              dialog = null;
              bottomSheetDialog = null;
              textColor = 0;
              hintColor = 0;
              backgroundColor = 0;
              searchIconId = 0;
              searchIcon = null;
            }
          }
        },
        textColor);
    countriesRecyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(sheetView.getContext());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    countriesRecyclerView.setLayoutManager(layoutManager);
    countriesRecyclerView.setAdapter(adapter);
  }

  @Override public void setSearchEditText() {
    if (canSearch) {
      searchEditText.addTextChangedListener(new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          // Intentionally Empty
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          // Intentionally Empty
        }

        @Override
        public void afterTextChanged(Editable searchQuery) {
          search(searchQuery.toString());
        }
      });
      searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
          InputMethodManager imm = (InputMethodManager) searchEditText.getContext()
              .getSystemService(Context.INPUT_METHOD_SERVICE);
          if (imm != null) {
            imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
          }
          return true;
        }
      });
    } else {
      searchEditText.setVisibility(View.GONE);
    }
  }

  private void search(String searchQuery) {
    searchResults.clear();
    for (Country country : countries) {
      if (country.getName().toLowerCase(Locale.ENGLISH).contains(searchQuery.toLowerCase())) {
        searchResults.add(country);
      }
    }
    sortCountries(searchResults);
    adapter.notifyDataSetChanged();
  }

  @SuppressWarnings("ResourceType")
  @Override public void setCustomStyle(View sheetView) {
    if (style != 0) {
      int[] attrs =
          {
              android.R.attr.textColor, android.R.attr.textColorHint, android.R.attr.background,
              android.R.attr.drawable
          };
      TypedArray ta = sheetView.getContext().obtainStyledAttributes(style, attrs);
      textColor = ta.getColor(0, Color.BLACK);
      hintColor = ta.getColor(1, Color.GRAY);
      backgroundColor = ta.getColor(2, Color.WHITE);
      searchIconId = ta.getResourceId(3, R.drawable.ic_search);
      searchEditText.setTextColor(textColor);
      searchEditText.setHintTextColor(hintColor);
      searchIcon = ContextCompat.getDrawable(searchEditText.getContext(), searchIconId);
      if (searchIconId == R.drawable.ic_search) {
        searchIcon.setColorFilter(new PorterDuffColorFilter(hintColor, PorterDuff.Mode.SRC_ATOP));
      }
      searchEditText.setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
      rootView.setBackgroundColor(backgroundColor);
      ta.recycle();
    }
  }

  @Override public void initiateUi(View sheetView) {
    searchEditText = sheetView.findViewById(R.id.country_code_picker_search);
    countriesRecyclerView = sheetView.findViewById(R.id.countries_recycler_view);
    rootView = sheetView.findViewById(R.id.rootView);
  }
  // endregion

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
    String countryIsoCode = locale.getCountry();
    return getCountryByISO(countryIsoCode);
  }

  public Country getCountryByName(@NonNull String countryName) {
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

  public Country getCountryByDialCode(@NonNull String dialCode){
    Collections.sort(countries, new DialCodeComparator());
    Country country = new Country();
    country.setDialCode(dialCode);
    int i = Collections.binarySearch(countries, country, new DialCodeComparator());
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
    private int style;
    private int theme = THEME_NEW;

    public Builder with(@NonNull Context context) {
      this.context = context;
      return this;
    }

    public Builder style(@NonNull @StyleRes int style) {
      this.style = style;
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

    public Builder theme(@NonNull int theme) {
      this.theme = theme;
      return this;
    }

    public CountryPicker build() {
      return new CountryPicker(this);
    }
  }
  // endregion

  // region Comparators
  private static class ISOCodeComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country nextCountry) {
      return country.getCode().compareToIgnoreCase(nextCountry.getCode());
    }
  }

  private static class NameComparator implements Comparator<Country> {
    @Override
    public int compare(Country country, Country nextCountry) {
      return country.getName().compareToIgnoreCase(nextCountry.getName());
    }
  }

  public static class DialCodeComparator implements  Comparator<Country>{
    @Override
    public int compare(Country country, Country nextCountry) {
      return country.getDialCode().compareTo(nextCountry.getDialCode());
    }
  }
  // endregion
}
