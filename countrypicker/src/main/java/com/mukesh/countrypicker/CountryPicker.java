package com.mukesh.countrypicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CountryPicker {

  // region Countries
  private final Country[] COUNTRIES = {
      new Country("AD", "+376", R.drawable.flag_ad, "EUR"),
      new Country("AE", "+971", R.drawable.flag_ae, "AED"),
      new Country("AF", "+93", R.drawable.flag_af, "AFN"),
      new Country("AG", "+1", R.drawable.flag_ag, "XCD"),
      new Country("AI", "+1", R.drawable.flag_ai, "XCD"),
      new Country("AL", "+355", R.drawable.flag_al, "ALL"),
      new Country("AM", "+374", R.drawable.flag_am, "AMD"),
      new Country("AO", "+244", R.drawable.flag_ao, "AOA"),
      new Country("AQ", "+672", R.drawable.flag_aq, "USD"),
      new Country("AR", "+54", R.drawable.flag_ar, "ARS"),
      new Country("AS", "+1", R.drawable.flag_as, "USD"),
      new Country("AT", "+43", R.drawable.flag_at, "EUR"),
      new Country("AU", "+61", R.drawable.flag_au, "AUD"),
      new Country("AW", "+297", R.drawable.flag_aw, "AWG"),
      new Country("AX", "+358", R.drawable.flag_ax, "EUR"),
      new Country("AZ", "+994", R.drawable.flag_az, "AZN"),
      new Country("BA", "+387", R.drawable.flag_ba, "BAM"),
      new Country("BB", "+1", R.drawable.flag_bb, "BBD"),
      new Country("BD", "+880", R.drawable.flag_bd, "BDT"),
      new Country("BE", "+32", R.drawable.flag_be, "EUR"),
      new Country("BF", "+226", R.drawable.flag_bf, "XOF"),
      new Country("BG", "+359", R.drawable.flag_bg, "BGN"),
      new Country("BH", "+973", R.drawable.flag_bh, "BHD"),
      new Country("BI", "+257", R.drawable.flag_bi, "BIF"),
      new Country("BJ", "+229", R.drawable.flag_bj, "XOF"),
      new Country("BL", "+590", R.drawable.flag_bl, "EUR"),
      new Country("BM", "+1", R.drawable.flag_bm, "BMD"),
      new Country("BN", "+673", R.drawable.flag_bn, "BND"),
      new Country("BO", "+591", R.drawable.flag_bo, "BOB"),
      new Country("BQ", "+599", R.drawable.flag_bq, "USD"),
      new Country("BR", "+55", R.drawable.flag_br, "BRL"),
      new Country("BS", "+1", R.drawable.flag_bs, "BSD"),
      new Country("BT", "+975", R.drawable.flag_bt, "BTN"),
      new Country("BV", "+47", R.drawable.flag_bv, "NOK"),
      new Country("BW", "+267", R.drawable.flag_bw, "BWP"),
      new Country("BY", "+375", R.drawable.flag_by, "BYR"),
      new Country("BZ", "+501", R.drawable.flag_bz, "BZD"),
      new Country("CA", "+1", R.drawable.flag_ca, "CAD"),
      new Country("CC", "+61", R.drawable.flag_cc, "AUD"),
      new Country("CD", "+243", R.drawable.flag_cd, "CDF"),
      new Country("CF", "+236", R.drawable.flag_cf, "XAF"),
      new Country("CG", "+242", R.drawable.flag_cg, "XAF"),
      new Country("CH", "+41", R.drawable.flag_ch, "CHF"),
      new Country("CI", "+225", R.drawable.flag_ci, "XOF"),
      new Country("CK", "+682", R.drawable.flag_ck, "NZD"),
      new Country("CL", "+56", R.drawable.flag_cl, "CLP"),
      new Country("CM", "+237", R.drawable.flag_cm, "XAF"),
      new Country("CN", "+86", R.drawable.flag_cn, "CNY"),
      new Country("CO", "+57", R.drawable.flag_co, "COP"),
      new Country("CR", "+506", R.drawable.flag_cr, "CRC"),
      new Country("CU", "+53", R.drawable.flag_cu, "CUP"),
      new Country("CV", "+238", R.drawable.flag_cv, "CVE"),
      new Country("CW", "+599", R.drawable.flag_cw, "ANG"),
      new Country("CX", "+61", R.drawable.flag_cx, "AUD"),
      new Country("CY", "+357", R.drawable.flag_cy, "EUR"),
      new Country("CZ", "+420", R.drawable.flag_cz, "CZK"),
      new Country("DE", "+49", R.drawable.flag_de, "EUR"),
      new Country("DJ", "+253", R.drawable.flag_dj, "DJF"),
      new Country("DK", "+45", R.drawable.flag_dk, "DKK"),
      new Country("DM", "+1", R.drawable.flag_dm, "XCD"),
      new Country("DO", "+1", R.drawable.flag_do, "DOP"),
      new Country("DZ", "+213", R.drawable.flag_dz, "DZD"),
      new Country("EC", "+593", R.drawable.flag_ec, "USD"),
      new Country("EE", "+372", R.drawable.flag_ee, "EUR"),
      new Country("EG", "+20", R.drawable.flag_eg, "EGP"),
      new Country("EH", "+212", R.drawable.flag_eh, "MAD"),
      new Country("ER", "+291", R.drawable.flag_er, "ERN"),
      new Country("ES", "+34", R.drawable.flag_es, "EUR"),
      new Country("ET", "+251", R.drawable.flag_et, "ETB"),
      new Country("FI", "+358", R.drawable.flag_fi, "EUR"),
      new Country("FJ", "+679", R.drawable.flag_fj, "FJD"),
      new Country("FK", "+500", R.drawable.flag_fk, "FKP"),
      new Country("FM", "+691", R.drawable.flag_fm, "USD"),
      new Country("FO", "+298", R.drawable.flag_fo, "DKK"),
      new Country("FR", "+33", R.drawable.flag_fr, "EUR"),
      new Country("GA", "+241", R.drawable.flag_ga, "XAF"),
      new Country("GB", "+44", R.drawable.flag_gb, "GBP"),
      new Country("GD", "+1", R.drawable.flag_gd, "XCD"),
      new Country("GE", "+995", R.drawable.flag_ge, "GEL"),
      new Country("GF", "+594", R.drawable.flag_gf, "EUR"),
      new Country("GG", "+44", R.drawable.flag_gg, "GGP"),
      new Country("GH", "+233", R.drawable.flag_gh, "GHS"),
      new Country("GI", "+350", R.drawable.flag_gi, "GIP"),
      new Country("GL", "+299", R.drawable.flag_gl, "DKK"),
      new Country("GM", "+220", R.drawable.flag_gm, "GMD"),
      new Country("GN", "+224", R.drawable.flag_gn, "GNF"),
      new Country("GP", "+590", R.drawable.flag_gp, "EUR"),
      new Country("GQ", "+240", R.drawable.flag_gq, "XAF"),
      new Country("GR", "+30", R.drawable.flag_gr, "EUR"),
      new Country("GS", "+500", R.drawable.flag_gs, "GBP"),
      new Country("GT", "+502", R.drawable.flag_gt, "GTQ"),
      new Country("GU", "+1", R.drawable.flag_gu, "USD"),
      new Country("GW", "+245", R.drawable.flag_gw, "XOF"),
      new Country("GY", "+595", R.drawable.flag_gy, "GYD"),
      new Country("HK", "+852", R.drawable.flag_hk, "HKD"),
      new Country("HM", "+000", R.drawable.flag_hm, "AUD"),
      new Country("HN", "+504", R.drawable.flag_hn, "HNL"),
      new Country("HR", "+385", R.drawable.flag_hr, "HRK"),
      new Country("HT", "+509", R.drawable.flag_ht, "HTG"),
      new Country("HU", "+36", R.drawable.flag_hu, "HUF"),
      new Country("ID", "+62", R.drawable.flag_id, "IDR"),
      new Country("IE", "+353", R.drawable.flag_ie, "EUR"),
      new Country("IL", "+972", R.drawable.flag_il, "ILS"),
      new Country("IM", "+44", R.drawable.flag_im, "GBP"),
      new Country("IN", "+91", R.drawable.flag_in, "INR"),
      new Country("IO", "+246", R.drawable.flag_io, "USD"),
      new Country("IQ", "+964", R.drawable.flag_iq, "IQD"),
      new Country("IR", "+98", R.drawable.flag_ir, "IRR"),
      new Country("IS", "+354", R.drawable.flag_is, "ISK"),
      new Country("IT", "+39", R.drawable.flag_it, "EUR"),
      new Country("JE", "+44", R.drawable.flag_je, "JEP"),
      new Country("JM", "+1", R.drawable.flag_jm, "JMD"),
      new Country("JO", "+962", R.drawable.flag_jo, "JOD"),
      new Country("JP", "+81", R.drawable.flag_jp, "JPY"),
      new Country("KE", "+254", R.drawable.flag_ke, "KES"),
      new Country("KG", "+996", R.drawable.flag_kg, "KGS"),
      new Country("KH", "+855", R.drawable.flag_kh, "KHR"),
      new Country("KI", "+686", R.drawable.flag_ki, "AUD"),
      new Country("KM", "+269", R.drawable.flag_km, "KMF"),
      new Country("KN", "+1", R.drawable.flag_kn, "XCD"),
      new Country("KP", "+850", R.drawable.flag_kp, "KPW"),
      new Country("KR", "+82", R.drawable.flag_kr, "KRW"),
      new Country("KW", "+965", R.drawable.flag_kw, "KWD"),
      new Country("KY", "+345", R.drawable.flag_ky, "KYD"),
      new Country("KZ", "+7", R.drawable.flag_kz, "KZT"),
      new Country("LA", "+856", R.drawable.flag_la, "LAK"),
      new Country("LB", "+961", R.drawable.flag_lb, "LBP"),
      new Country("LC", "+1", R.drawable.flag_lc, "XCD"),
      new Country("LI", "+423", R.drawable.flag_li, "CHF"),
      new Country("LK", "+94", R.drawable.flag_lk, "LKR"),
      new Country("LR", "+231", R.drawable.flag_lr, "LRD"),
      new Country("LS", "+266", R.drawable.flag_ls, "LSL"),
      new Country("LT", "+370", R.drawable.flag_lt, "LTL"),
      new Country("LU", "+352", R.drawable.flag_lu, "EUR"),
      new Country("LV", "+371", R.drawable.flag_lv, "LVL"),
      new Country("LY", "+218", R.drawable.flag_ly, "LYD"),
      new Country("MA", "+212", R.drawable.flag_ma, "MAD"),
      new Country("MC", "+377", R.drawable.flag_mc, "EUR"),
      new Country("MD", "+373", R.drawable.flag_md, "MDL"),
      new Country("ME", "+382", R.drawable.flag_me, "EUR"),
      new Country("MF", "+590", R.drawable.flag_mf, "EUR"),
      new Country("MG", "+261", R.drawable.flag_mg, "MGA"),
      new Country("MH", "+692", R.drawable.flag_mh, "USD"),
      new Country("MK", "+389", R.drawable.flag_mk, "MKD"),
      new Country("ML", "+223", R.drawable.flag_ml, "XOF"),
      new Country("MM", "+95", R.drawable.flag_mm, "MMK"),
      new Country("MN", "+976", R.drawable.flag_mn, "MNT"),
      new Country("MO", "+853", R.drawable.flag_mo, "MOP"),
      new Country("MP", "+1", R.drawable.flag_mp, "USD"),
      new Country("MQ", "+596", R.drawable.flag_mq, "EUR"),
      new Country("MR", "+222", R.drawable.flag_mr, "MRO"),
      new Country("MS", "+1", R.drawable.flag_ms, "XCD"),
      new Country("MT", "+356", R.drawable.flag_mt, "EUR"),
      new Country("MU", "+230", R.drawable.flag_mu, "MUR"),
      new Country("MV", "+960", R.drawable.flag_mv, "MVR"),
      new Country("MW", "+265", R.drawable.flag_mw, "MWK"),
      new Country("MX", "+52", R.drawable.flag_mx, "MXN"),
      new Country("MY", "+60", R.drawable.flag_my, "MYR"),
      new Country("MZ", "+258", R.drawable.flag_mz, "MZN"),
      new Country("NA", "+264", R.drawable.flag_na, "NAD"),
      new Country("NC", "+687", R.drawable.flag_nc, "XPF"),
      new Country("NE", "+227", R.drawable.flag_ne, "XOF"),
      new Country("NF", "+672", R.drawable.flag_nf, "AUD"),
      new Country("NG", "+234", R.drawable.flag_ng, "NGN"),
      new Country("NI", "+505", R.drawable.flag_ni, "NIO"),
      new Country("NL", "+31", R.drawable.flag_nl, "EUR"),
      new Country("NO", "+47", R.drawable.flag_no, "NOK"),
      new Country("NP", "+977", R.drawable.flag_np, "NPR"),
      new Country("NR", "+674", R.drawable.flag_nr, "AUD"),
      new Country("NU", "+683", R.drawable.flag_nu, "NZD"),
      new Country("NZ", "+64", R.drawable.flag_nz, "NZD"),
      new Country("OM", "+968", R.drawable.flag_om, "OMR"),
      new Country("PA", "+507", R.drawable.flag_pa, "PAB"),
      new Country("PE", "+51", R.drawable.flag_pe, "PEN"),
      new Country("PF", "+689", R.drawable.flag_pf, "XPF"),
      new Country("PG", "+675", R.drawable.flag_pg, "PGK"),
      new Country("PH", "+63", R.drawable.flag_ph, "PHP"),
      new Country("PK", "+92", R.drawable.flag_pk, "PKR"),
      new Country("PL", "+48", R.drawable.flag_pl, "PLN"),
      new Country("PM", "+508", R.drawable.flag_pm, "EUR"),
      new Country("PN", "+872", R.drawable.flag_pn, "NZD"),
      new Country("PR", "+1", R.drawable.flag_pr, "USD"),
      new Country("PS", "+970", R.drawable.flag_ps, "ILS"),
      new Country("PT", "+351", R.drawable.flag_pt, "EUR"),
      new Country("PW", "+680", R.drawable.flag_pw, "USD"),
      new Country("PY", "+595", R.drawable.flag_py, "PYG"),
      new Country("QA", "+974", R.drawable.flag_qa, "QAR"),
      new Country("RE", "+262", R.drawable.flag_re, "EUR"),
      new Country("RO", "+40", R.drawable.flag_ro, "RON"),
      new Country("RS", "+381", R.drawable.flag_rs, "RSD"),
      new Country("RU", "+7", R.drawable.flag_ru, "RUB"),
      new Country("RW", "+250", R.drawable.flag_rw, "RWF"),
      new Country("SA", "+966", R.drawable.flag_sa, "SAR"),
      new Country("SB", "+677", R.drawable.flag_sb, "SBD"),
      new Country("SC", "+248", R.drawable.flag_sc, "SCR"),
      new Country("SD", "+249", R.drawable.flag_sd, "SDG"),
      new Country("SE", "+46", R.drawable.flag_se, "SEK"),
      new Country("SG", "+65", R.drawable.flag_sg, "SGD"),
      new Country("SH", "+290", R.drawable.flag_sh, "SHP"),
      new Country("SI", "+386", R.drawable.flag_si, "EUR"),
      new Country("SJ", "+47", R.drawable.flag_sj, "NOK"),
      new Country("SK", "+421", R.drawable.flag_sk, "EUR"),
      new Country("SL", "+232", R.drawable.flag_sl, "SLL"),
      new Country("SM", "+378", R.drawable.flag_sm, "EUR"),
      new Country("SN", "+221", R.drawable.flag_sn, "XOF"),
      new Country("SO", "+252", R.drawable.flag_so, "SOS"),
      new Country("SR", "+597", R.drawable.flag_sr, "SRD"),
      new Country("SS", "+211", R.drawable.flag_ss, "SSP"),
      new Country("ST", "+239", R.drawable.flag_st, "STD"),
      new Country("SV", "+503", R.drawable.flag_sv, "SVC"),
      new Country("SX", "+1", R.drawable.flag_sx, "ANG"),
      new Country("SY", "+963", R.drawable.flag_sy, "SYP"),
      new Country("SZ", "+268", R.drawable.flag_sz, "SZL"),
      new Country("TC", "+1", R.drawable.flag_tc, "USD"),
      new Country("TD", "+235", R.drawable.flag_td, "XAF"),
      new Country("TF", "+262", R.drawable.flag_tf, "EUR"),
      new Country("TG", "+228", R.drawable.flag_tg, "XOF"),
      new Country("TH", "+66", R.drawable.flag_th, "THB"),
      new Country("TJ", "+992", R.drawable.flag_tj, "TJS"),
      new Country("TK", "+690", R.drawable.flag_tk, "NZD"),
      new Country("TL", "+670", R.drawable.flag_tl, "USD"),
      new Country("TM", "+993", R.drawable.flag_tm, "TMT"),
      new Country("TN", "+216", R.drawable.flag_tn, "TND"),
      new Country("TO", "+676", R.drawable.flag_to, "TOP"),
      new Country("TR", "+90", R.drawable.flag_tr, "TRY"),
      new Country("TT", "+1", R.drawable.flag_tt, "TTD"),
      new Country("TV", "+688", R.drawable.flag_tv, "AUD"),
      new Country("TW", "+886", R.drawable.flag_tw, "TWD"),
      new Country("TZ", "+255", R.drawable.flag_tz, "TZS"),
      new Country("UA", "+380", R.drawable.flag_ua, "UAH"),
      new Country("UG", "+256", R.drawable.flag_ug, "UGX"),
      new Country("UM", "+1", R.drawable.flag_um, "USD"),
      new Country("US", "+1", R.drawable.flag_us, "USD"),
      new Country("UY", "+598", R.drawable.flag_uy, "UYU"),
      new Country("UZ", "+998", R.drawable.flag_uz, "UZS"),
      new Country("VA", "+379", R.drawable.flag_va, "EUR"),
      new Country("VC", "+1", R.drawable.flag_vc, "XCD"),
      new Country("VE", "+58", R.drawable.flag_ve, "VEF"),
      new Country("VG", "+1", R.drawable.flag_vg, "USD"),
      new Country("VI", "+1", R.drawable.flag_vi, "USD"),
      new Country("VN", "+84", R.drawable.flag_vn, "VND"),
      new Country("VU", "+678", R.drawable.flag_vu, "VUV"),
      new Country("WF", "+681", R.drawable.flag_wf, "XPF"),
      new Country("WS", "+685", R.drawable.flag_ws, "WST"),
      new Country("XK", "+383", R.drawable.flag_xk, "EUR"),
      new Country("YE", "+967", R.drawable.flag_ye, "YER"),
      new Country("YT", "+262", R.drawable.flag_yt, "EUR"),
      new Country("ZA", "+27", R.drawable.flag_za, "ZAR"),
      new Country("ZM", "+260", R.drawable.flag_zm, "ZMW"),
      new Country("ZW", "+263", R.drawable.flag_zw, "USD"),
  };
  // endregion

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
  private BottomSheetDialog bottomSheetDialog;
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
    countries = new ArrayList<>(Arrays.asList(COUNTRIES));

    if (builder.locale != null) {
      List<Country> localizedCountries = new ArrayList<>();
      for (Country country : countries) {
        localizedCountries.add(country.localize(builder.locale));
      }
      countries = localizedCountries;
    }
    sortCountries(countries);
  }
  // endregion

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
  public void showDialog(@NonNull Activity activity) {
    if (countries == null || countries.isEmpty()) {
      throw new IllegalArgumentException(context.getString(R.string.error_no_countries_found));
    } else {
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
  public void showBottomSheet(Activity activity) {
    if (countries == null || countries.isEmpty()) {
      throw new IllegalArgumentException(context.getString(R.string.error_no_countries_found));
    } else {
      bottomSheetDialog = new BottomSheetDialog(activity);
      View sheetView = activity.getLayoutInflater().inflate(R.layout.country_picker, null);
      initiateUi(sheetView);
      setCustomStyle(sheetView);
      setSearchEditText();
      setupRecyclerView(sheetView);
      bottomSheetDialog.setContentView(sheetView);
      if (theme == THEME_NEW && bottomSheetDialog.getWindow() != null) {
        FrameLayout bottomSheet = bottomSheetDialog.getWindow()
            .findViewById(android.support.design.R.id.design_bottom_sheet);
        bottomSheet.setBackgroundColor(Color.TRANSPARENT);
        Drawable background =
            ContextCompat.getDrawable(context, R.drawable.ic_bottomsheet_new_background);
        if (background != null) {
          background.setColorFilter(
              new PorterDuffColorFilter(backgroundColor, PorterDuff.Mode.SRC_ATOP));
        }
        rootView.setBackgroundDrawable(background);
      }
      bottomSheetDialog.show();
    }
  }

  private void setupRecyclerView(View sheetView) {
    searchResults = new ArrayList<>();
    searchResults.addAll(countries);
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

  private void setSearchEditText() {
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
  private void setCustomStyle(View sheetView) {
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

  private void initiateUi(View sheetView) {
    searchEditText = sheetView.findViewById(R.id.country_code_picker_search);
    countriesRecyclerView = sheetView.findViewById(R.id.countries_recycler_view);
    rootView = sheetView.findViewById(R.id.rootView);
  }
  // endregion

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
  // endregion

  // region Builder
  public static class Builder {
    private Context context;
    private int sortBy = SORT_BY_NONE;
    private boolean canSearch = true;
    private OnCountryPickerListener onCountryPickerListener;
    private int style;
    private int theme = THEME_NEW;
    private Locale locale = null;

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

    public Builder locale(@NonNull Locale locale) {
      this.locale = locale;
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
