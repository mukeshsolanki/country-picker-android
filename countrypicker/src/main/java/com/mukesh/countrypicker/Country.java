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
    public static final Country[] COUNTRIES = {
            new Country("AD", "Andorra", "أندورا", "+376", R.drawable.flag_ad),
            new Country("AE", "United Arab Emirates", "الإمارات العربية المتحدة", "+971", R.drawable.flag_ae),
            new Country("AF", "Afghanistan", "أفغانستان", "+93", R.drawable.flag_af),
            new Country("AG", "Antigua and Barbuda", "أنتيغوا وبربودا", "+1", R.drawable.flag_ag),
            new Country("AI", "Anguilla", "أنغويلا", "+1", R.drawable.flag_ai),
            new Country("AL", "Albania", "ألبانيا", "+355", R.drawable.flag_al),
            new Country("AM", "Armenia", "أرمينيا", "+374", R.drawable.flag_am),
            new Country("AO", "Angola", "أنغولا", "+244", R.drawable.flag_ao),
            new Country("AQ", "Antarctica", "أنتاركتيكا", "+672", R.drawable.flag_aq),
            new Country("AR", "Argentina", "الأرجنتين", "+54", R.drawable.flag_ar),
            new Country("AS", "AmericanSamoa", "ساموا-الأمريكي", "+1", R.drawable.flag_as),
            new Country("AT", "Austria", "النمسا", "+43", R.drawable.flag_at),
            new Country("AU", "Australia", "أستراليا", "+61", R.drawable.flag_au),
            new Country("AW", "Aruba", "أروبا", "+297", R.drawable.flag_aw),
            new Country("AX", "Åland Islands", "جزر آلاند", "+358", R.drawable.flag_ax),
            new Country("AZ", "Azerbaijan", "أذربيجان", "+994", R.drawable.flag_az),
            new Country("BA", "Bosnia and Herzegovina", "البوسنة و الهرسك", "+387", R.drawable.flag_ba),
            new Country("BB", "Barbados", "بربادوس", "+1", R.drawable.flag_bb),
            new Country("BD", "Bangladesh", "بنغلاديش", "+880", R.drawable.flag_bd),
            new Country("BE", "Belgium", "بلجيكا", "+32", R.drawable.flag_be),
            new Country("BF", "Burkina Faso", "بوركينا فاسو", "+226", R.drawable.flag_bf),
            new Country("BG", "Bulgaria", "بلغاريا", "+359", R.drawable.flag_bg),
            new Country("BH", "Bahrain", "البحرين", "+973", R.drawable.flag_bh),
            new Country("BI", "Burundi", "بوروندي", "+257", R.drawable.flag_bi),
            new Country("BJ", "Benin", "بنين", "+229", R.drawable.flag_bj),
            new Country("BL", "Saint Barthélemy", "سان بارتيلمي", "+590", R.drawable.flag_bl),
            new Country("BM", "Bermuda", "جزر برمود", "+1", R.drawable.flag_bm),
            new Country("BN", "Brunei Darussalam", "بروناي", "+673", R.drawable.flag_bn),
            new Country("BO", "Bolivia, Plurinational State of", "بوليفيا", "+591", R.drawable.flag_bo),
            new Country("BQ", "Bonaire", "بونير", "+599", R.drawable.flag_bq),
            new Country("BR", "Brazil", "البرازيل", "+55", R.drawable.flag_br),
            new Country("BS", "Bahamas", "الباهاماس", "+1", R.drawable.flag_bs),
            new Country("BT", "Bhutan", "بوتان", "+975", R.drawable.flag_bt),
            new Country("BV", "Bouvet Island", "جزيرة بوفيت", "+47", R.drawable.flag_bv),
            new Country("BW", "Botswana", "بوتسوانا", "+267", R.drawable.flag_bw),
            new Country("BY", "Belarus", "روسيا البيضاء", "+375", R.drawable.flag_by),
            new Country("BZ", "Belize", "بيليز", "+501", R.drawable.flag_bz),
            new Country("CA", "Canada", "كندا", "+1", R.drawable.flag_ca),
            new Country("CC", "Cocos (Keeling) Islands", "جزر كوكوس (كيلينغ)", "+61", R.drawable.flag_cc),
            new Country("CD", "Congo, The Democratic Republic of the", "جمهورية الكونغو الديمقراطية", "+243", R.drawable.flag_cd),
            new Country("CF", "Central African Republic", "جمهورية أفريقيا الوسطى", "+236", R.drawable.flag_cf),
            new Country("CG", "Congo", "جمهورية الكونغو", "+242", R.drawable.flag_cg),
            new Country("CH", "Switzerland", "سويسرا", "+41", R.drawable.flag_ch),
            new Country("CI", "Cote d'Ivoire (Ivory Coast)", "ساحل العاج", "+225", R.drawable.flag_ci),
            new Country("CK", "Cook Islands", "جزر كوك", "+682", R.drawable.flag_ck),
            new Country("CL", "Chile", "تشيلي", "+56", R.drawable.flag_cl),
            new Country("CM", "Cameroon", "كاميرون", "+237", R.drawable.flag_cm),
            new Country("CN", "China", "جمهورية الصين الشعبية", "+86", R.drawable.flag_cn),
            new Country("CO", "Colombia", "كولومبيا", "+57", R.drawable.flag_co),
            new Country("CR", "Costa Rica", "كوستاريكا", "+506", R.drawable.flag_cr),
            new Country("CU", "Cuba", "كوبا", "+53", R.drawable.flag_cu),
            new Country("CV", "Cape Verde", "الرأس الأخضر", "+238", R.drawable.flag_cv),
            new Country("CW", "Curacao", "كوراساو", "+599", R.drawable.flag_cw),
            new Country("CX", "Christmas Island", "عيد ميد، إسلاند", "+61", R.drawable.flag_cx),
            new Country("CY", "Cyprus", "قبرص", "+357", R.drawable.flag_cy),
            new Country("CZ", "Czech Republic", "الجمهورية التشيكية", "+420", R.drawable.flag_cz),
            new Country("DE", "Germany", "ألمانيا", "+49", R.drawable.flag_de),
            new Country("DJ", "Djibouti", "جيبوتي", "+253", R.drawable.flag_dj),
            new Country("DK", "Denmark", "الدانمارك", "+45", R.drawable.flag_dk),
            new Country("DM", "Dominica", "دومينيكا", "+1", R.drawable.flag_dm),
            new Country("DO", "Dominican Republic", "الجمهورية الدومينيكية", "+1", R.drawable.flag_do),
            new Country("DZ", "Algeria", "الجزائر", "+213", R.drawable.flag_dz),
            new Country("EC", "Ecuador", "إكوادور", "+593", R.drawable.flag_ec),
            new Country("EE", "Estonia", "استونيا", "+372", R.drawable.flag_ee),
            new Country("EG", "Egypt", "مصر", "+20", R.drawable.flag_eg),
            new Country("EH", "Western Sahara", "الصحراء الغربية", "+212", R.drawable.flag_eh),
            new Country("ER", "Eritrea", "إريتريا", "+291", R.drawable.flag_er),
            new Country("ES", "Spain", "إسبانيا", "+34", R.drawable.flag_es),
            new Country("ET", "Ethiopia", "أثيوبيا", "+251", R.drawable.flag_et),
            new Country("FI", "Finland", "فنلندا", "+358", R.drawable.flag_fi),
            new Country("FJ", "Fiji", "فيجي", "+679", R.drawable.flag_fj),
            new Country("FK", "Falkland Islands (Malvinas)", "جزر فوكلاند", "+500", R.drawable.flag_fk),
            new Country("FM", "Micronesia, Federated States of", "ولايات ميكرونيسيا المتحدة", "+691", R.drawable.flag_fm),
            new Country("FO", "Faroe Islands", "جزر فارو", "+298", R.drawable.flag_fo),
            new Country("FR", "France", "فرنسا", "+33", R.drawable.flag_fr),
            new Country("GA", "Gabon", "الغابون", "+241", R.drawable.flag_ga),
            new Country("GB", "United Kingdom", "المملكة المتحدة", "+44", R.drawable.flag_gb),
            new Country("GD", "Grenada", "غرينادا", "+1", R.drawable.flag_gd),
            new Country("GE", "Georgia", "جيورجيا", "+995", R.drawable.flag_ge),
            new Country("GF", "French Guiana", "غويانا الفرنسية", "+594", R.drawable.flag_gf),
            new Country("GG", "Guernsey", "غيرنزي", "+44", R.drawable.flag_gg),
            new Country("GH", "Ghana", "غانا", "+233", R.drawable.flag_gh),
            new Country("GI", "Gibraltar", "جبل طارق", "+350", R.drawable.flag_gi),
            new Country("GL", "Greenland", "جرينلاند", "+299", R.drawable.flag_gl),
            new Country("GM", "Gambia", "غامبيا", "+220", R.drawable.flag_gm),
            new Country("GN", "Guinea", "غينيا", "+224", R.drawable.flag_gn),
            new Country("GP", "Guadeloupe", "جزر جوادلوب", "+590", R.drawable.flag_gp),
            new Country("GQ", "Equatorial Guinea", "غينيا الاستوائي", "+240", R.drawable.flag_gq),
            new Country("GR", "Greece", "اليونان", "+30", R.drawable.flag_gr),
            new Country("GS", "South Georgia and the South Sandwich Islands", "جورجيا الجنوبية وجزر ساندويتش الجنوبية", "+500", R.drawable.flag_gs),
            new Country("GT", "Guatemala", "غواتيمال", "+502", R.drawable.flag_gt),
            new Country("GU", "Guam", "جوام", "+1", R.drawable.flag_gu),
            new Country("GW", "Guinea-Bissau", "غينيا-بيساو", "+245", R.drawable.flag_gw),
            new Country("GY", "Guyana", "غيانا", "+595", R.drawable.flag_gy),
            new Country("HK", "Hong Kong", "هونغ كونغ", "+852", R.drawable.flag_hk),
            new Country("HM", "Heard Island and McDonald Islands", "جزيرة هيرد وجزر ماكدونالد", "", R.drawable.flag_hm),
            new Country("HN", "Honduras", "هندوراس", "+504", R.drawable.flag_hn),
            new Country("HR", "Croatia", "كرواتيا", "+385", R.drawable.flag_hr),
            new Country("HT", "Haiti", "هايتي", "+509", R.drawable.flag_ht),
            new Country("HU", "Hungary", "المجر", "+36", R.drawable.flag_hu),
            new Country("ID", "Indonesia", "أندونيسيا", "+62", R.drawable.flag_id),
            new Country("IE", "Ireland", "جمهورية أيرلندا", "+353", R.drawable.flag_ie),
            new Country("IL", "Israel", "إسرائيل", "+972", R.drawable.flag_il),
            new Country("IM", "Isle of Man", "جزيرة مان", "+44", R.drawable.flag_im),
            new Country("IN", "India", "الهند", "+91", R.drawable.flag_in),
            new Country("IO", "British Indian Ocean Territory", "إقليم المحيط الهندي البريطاني", "+246", R.drawable.flag_io),
            new Country("IQ", "Iraq", "العراق", "+964", R.drawable.flag_iq),
            new Country("IR", "Iran, Islamic Republic of", "إيران", "+98", R.drawable.flag_ir),
            new Country("IS", "Iceland", "آيسلندا", "+354", R.drawable.flag_is),
            new Country("IT", "Italy", "إيطاليا", "+39", R.drawable.flag_it),
            new Country("JE", "Jersey", "جيرزي", "+44", R.drawable.flag_je),
            new Country("JM", "Jamaica", "جمايكا", "+1", R.drawable.flag_jm),
            new Country("JO", "Jordan", "الأردن", "+962", R.drawable.flag_jo),
            new Country("JP", "Japan", "اليابان", "+81", R.drawable.flag_jp),
            new Country("KE", "Kenya", "كينيا", "+254", R.drawable.flag_ke),
            new Country("KG", "Kyrgyzstan", "قيرغيزستان", "+996", R.drawable.flag_kg),
            new Country("KH", "Cambodia", "كمبوديا", "+855", R.drawable.flag_kh),
            new Country("KI", "Kiribati", "كيريباتي", "+686", R.drawable.flag_ki),
            new Country("KM", "Comoros", "جزر القمر", "+269", R.drawable.flag_km),
            new Country("KN", "Saint Kitts and Nevis", "سانت كيتس ونيفس", "+1", R.drawable.flag_kn),
            new Country("KP", "North Korea", "كوريا الشمالية", "+850", R.drawable.flag_kp),
            new Country("KR", "South Korea", "كوريا الجنوبية", "+82", R.drawable.flag_kr),
            new Country("KW", "Kuwait", "الكويت", "+965", R.drawable.flag_kw),
            new Country("KY", "Cayman Islands", "جزر كايمان", "+345", R.drawable.flag_ky),
            new Country("KZ", "Kazakhstan", "كازاخستان", "+7", R.drawable.flag_kz),
            new Country("LA", "Lao People's Democratic Republic", "لاوس", "+856", R.drawable.flag_la),
            new Country("LB", "Lebanon", "لبنان", "+961", R.drawable.flag_lb),
            new Country("LC", "Saint Lucia", "سانت لوسيا", "+1", R.drawable.flag_lc),
            new Country("LI", "Liechtenstein", "ليختنشتين", "+423", R.drawable.flag_li),
            new Country("LK", "Sri Lanka", "سريلانكا", "+94", R.drawable.flag_lk),
            new Country("LR", "Liberia", "ليبيريا", "+231", R.drawable.flag_lr),
            new Country("LS", "Lesotho", "ليسوتو", "+266", R.drawable.flag_ls),
            new Country("LT", "Lithuania", "لتوانيا", "+370", R.drawable.flag_lt),
            new Country("LU", "Luxembourg", "لوكسمبورغ", "+352", R.drawable.flag_lu),
            new Country("LV", "Latvia", "لاتفيا", "+371", R.drawable.flag_lv),
            new Country("LY", "Libyan Arab Jamahiriya", "ليبيا", "+218", R.drawable.flag_ly),
            new Country("MA", "Morocco", "المغرب", "+212", R.drawable.flag_ma),
            new Country("MC", "Monaco", "موناكو", "+377", R.drawable.flag_mc),
            new Country("MD", "Moldova, Republic of", "مولدافيا", "+373", R.drawable.flag_md),
            new Country("ME", "Montenegro", "الجبل الأسو", "+382", R.drawable.flag_me),
            new Country("MF", "Saint Martin", "سانت مارتن", "+590", R.drawable.flag_mf),
            new Country("MG", "Madagascar", "مدغشقر", "+261", R.drawable.flag_mg),
            new Country("MH", "Marshall Islands", "جزر مارشال", "+692", R.drawable.flag_mh),
            new Country("MK", "Macedonia, The Former Yugoslav Republic of", "جمهورية مقدونيا", "+389", R.drawable.flag_mk),
            new Country("ML", "Mali", "مالي", "+223", R.drawable.flag_ml),
            new Country("MM", "Myanmar", "ميانمار", "+95", R.drawable.flag_mm),
            new Country("MN", "Mongolia", "منغوليا", "+976", R.drawable.flag_mn),
            new Country("MO", "Macao", "ماكاو", "+853", R.drawable.flag_mo),
            new Country("MP", "Northern Mariana Islands", "جزر ماريانا الشمالية", "+1", R.drawable.flag_mp),
            new Country("MQ", "Martinique", "مارتينيك", "+596", R.drawable.flag_mq),
            new Country("MR", "Mauritania", "موريتانيا", "+222", R.drawable.flag_mr),
            new Country("MS", "Montserrat", "مونتسيرات", "+1", R.drawable.flag_ms),
            new Country("MT", "Malta", "مالطا", "+356", R.drawable.flag_mt),
            new Country("MU", "Mauritius", "موريشيوس", "+230", R.drawable.flag_mu),
            new Country("MV", "Maldives", "المالديف", "+960", R.drawable.flag_mv),
            new Country("MW", "Malawi", "مالاوي", "+265", R.drawable.flag_mw),
            new Country("MX", "Mexico", "المكسيك", "+52", R.drawable.flag_mx),
            new Country("MY", "Malaysia", "ماليزيا", "+60", R.drawable.flag_my),
            new Country("MZ", "Mozambique", "موزمبيق", "+258", R.drawable.flag_mz),
            new Country("NA", "Namibia", "ناميبيا", "+264", R.drawable.flag_na),
            new Country("NC", "New Caledonia", "كاليدونيا الجديدة", "+687", R.drawable.flag_nc),
            new Country("NE", "Niger", "النيجر", "+227", R.drawable.flag_ne),
            new Country("NF", "Norfolk Island", "جزيرة نورفولك", "+672", R.drawable.flag_nf),
            new Country("NG", "Nigeria", "نيجيريا", "+234", R.drawable.flag_ng),
            new Country("NI", "Nicaragua", "نيكاراجوا", "+505", R.drawable.flag_ni),
            new Country("NL", "Netherlands", "هولندا", "+31", R.drawable.flag_nl),
            new Country("NO", "Norway", "النرويج", "+47", R.drawable.flag_no),
            new Country("NP", "Nepal", "نيبال", "+977", R.drawable.flag_np),
            new Country("NR", "Nauru", "ناورو", "+674", R.drawable.flag_nr),
            new Country("NU", "Niue", "نييوي", "+683", R.drawable.flag_nu),
            new Country("NZ", "New Zealand", "نيوزيلندا", "+64", R.drawable.flag_nz),
            new Country("OM", "Oman", "عمان", "+968", R.drawable.flag_om),
            new Country("PA", "Panama", "بنما", "+507", R.drawable.flag_pa),
            new Country("PE", "Peru", "بيرو", "+51", R.drawable.flag_pe),
            new Country("PF", "French Polynesia", "بولينيزيا الفرنسية", "+689", R.drawable.flag_pf),
            new Country("PG", "Papua New Guinea", "بابوا غينيا الجديدة", "+675", R.drawable.flag_pg),
            new Country("PH", "Philippines", "الفليبين", "+63", R.drawable.flag_ph),
            new Country("PK", "Pakistan", "باكستان", "+92", R.drawable.flag_pk),
            new Country("PL", "Poland", "بولندا", "+48", R.drawable.flag_pl),
            new Country("PM", "Saint Pierre and Miquelon", "سان بيير وميكلون", "+508", R.drawable.flag_pm),
            new Country("PN", "Pitcairn", "جزر بيتكيرن", "+872", R.drawable.flag_pn),
            new Country("PR", "Puerto Rico", "بورتوريكو", "+1", R.drawable.flag_pr),
            new Country("PS", "Palestinian Territory, Occupied", "الأراضي الفلسطينية", "+970", R.drawable.flag_ps),
            new Country("PT", "Portugal", "البرتغال", "+351", R.drawable.flag_pt),
            new Country("PW", "Palau", "بالاو", "+680", R.drawable.flag_pw),
            new Country("PY", "Paraguay", "باراغواي", "+595", R.drawable.flag_py),
            new Country("QA", "Qatar", "قطر", "+974", R.drawable.flag_qa),
            new Country("RE", "Réunion", "لا ريونيون", "+262", R.drawable.flag_re),
            new Country("RO", "Romania", "رومانيا", "+40", R.drawable.flag_ro),
            new Country("RS", "Serbia", "جمهورية صربيا", "+381", R.drawable.flag_rs),
            new Country("RU", "Russia", "روسيا", "+7", R.drawable.flag_ru),
            new Country("RW", "Rwanda", "رواندا", "+250", R.drawable.flag_rw),
            new Country("SA", "Saudi Arabia", "المملكة العربية السعودية", "+966", R.drawable.flag_sa),
            new Country("SB", "Solomon Islands", "جزر سليمان", "+677", R.drawable.flag_sb),
            new Country("SC", "Seychelles", "سيشيل", "+248", R.drawable.flag_sc),
            new Country("SD", "Sudan", "السودان", "+249", R.drawable.flag_sd),
            new Country("SE", "Sweden", "السويد", "+46", R.drawable.flag_se),
            new Country("SG", "Singapore", "سنغافورة", "+65", R.drawable.flag_sg),
            new Country("SH", "Saint Helena, Ascension and Tristan Da Cunha", "سانت هيلانة وأسينشين وتريستان دا كونا", "+290", R.drawable.flag_sh),
            new Country("SI", "Slovenia", "سلوفينيا", "+386", R.drawable.flag_si),
            new Country("SJ", "Svalbard and Jan Mayen", "سفالبارد ويان ماين", "+47", R.drawable.flag_sj),
            new Country("SK", "Slovakia", "سلوفاكيا", "+421", R.drawable.flag_sk),
            new Country("SL", "Sierra Leone", "سيراليون", "+232", R.drawable.flag_sl),
            new Country("SM", "San Marino", "سان مارينو", "+378", R.drawable.flag_sm),
            new Country("SN", "Senegal", "السنغال", "+221", R.drawable.flag_sn),
            new Country("SO", "Somalia", "الصومال", "+252", R.drawable.flag_so),
            new Country("SR", "Suriname", "سورينام", "+597", R.drawable.flag_sr),
            new Country("SS", "South Sudan", "جنوب السودان", "+211", R.drawable.flag_ss),
            new Country("ST", "Sao Tome and Principe", "ساو تومي وبرينسيبي", "+239", R.drawable.flag_st),
            new Country("SV", "El Salvador", "إلسلفادور", "+503", R.drawable.flag_sv),
            new Country("SX", "Sint Maarten", "سينت مارتن", "+1", R.drawable.flag_sx),
            new Country("SY", "Syrian Arab Republic", "سوريا", "+963", R.drawable.flag_sy),
            new Country("SZ", "Swaziland", "سوازيلند", "+268", R.drawable.flag_sz),
            new Country("TC", "Turks and Caicos Islands", "جزر توركس وكايكوس", "+1", R.drawable.flag_tc),
            new Country("TD", "Chad", "تشاد", "+235", R.drawable.flag_td),
            new Country("TF", "French Southern Territories", "أراض فرنسية جنوبية وأنتارتيكية", "+262", R.drawable.flag_tf),
            new Country("TG", "Togo", "توغو", "+228", R.drawable.flag_tg),
            new Country("TH", "Thailand", "تايلندا", "+66", R.drawable.flag_th),
            new Country("TJ", "Tajikistan", "طاجيكستان", "+992", R.drawable.flag_tj),
            new Country("TK", "Tokelau", "توكيلاو", "+690", R.drawable.flag_tk),
            new Country("TL", "East Timor", "تيمور الشرقية", "+670", R.drawable.flag_tl),
            new Country("TM", "Turkmenistan", "تركمانستان", "+993", R.drawable.flag_tm),
            new Country("TN", "Tunisia", "تونس", "+216", R.drawable.flag_tn),
            new Country("TO", "Tonga", "تونغا", "+676", R.drawable.flag_to),
            new Country("TR", "Turkey", "تركيا", "+90", R.drawable.flag_tr),
            new Country("TT", "Trinidad and Tobago", "ترينيداد وتوباغو", "+1", R.drawable.flag_tt),
            new Country("TV", "Tuvalu", "توفالو", "+688", R.drawable.flag_tv),
            new Country("TW", "Taiwan", "تايوان", "+886", R.drawable.flag_tw),
            new Country("TZ", "Tanzania, United Republic of", "تنزانيا", "+255", R.drawable.flag_tz),
            new Country("UA", "Ukraine", "أوكرانيا", "+380", R.drawable.flag_ua),
            new Country("UG", "Uganda", "أوغندا", "+256", R.drawable.flag_ug),
            new Country("UM", "U.S. Minor Outlying Islands", "جزر الولايات المتحدة الصغيرة النائية", "", R.drawable.flag_um),
            new Country("US", "United States", "الولايات المتحدة", "+1", R.drawable.flag_us),
            new Country("UY", "Uruguay", "أورغواي", "+598", R.drawable.flag_uy),
            new Country("UZ", "Uzbekistan", "أوزباكستان", "+998", R.drawable.flag_uz),
            new Country("VA", "Holy See (Vatican City State)", "دولة مدينة الفاتيكان", "+379", R.drawable.flag_va),
            new Country("VC", "Saint Vincent and the Grenadines", "سانت فنسنت وجزر غرينادين", "+1", R.drawable.flag_vc),
            new Country("VE", "Venezuela, Bolivarian Republic of", "فنزويلا", "+58", R.drawable.flag_ve),
            new Country("VG", "Virgin Islands, British", "لجزر العذراء البريطانية", "+1", R.drawable.flag_vg),
            new Country("VI", "Virgin Islands, U.S.", "الجزر العذراء الأمريكي", "+1", R.drawable.flag_vi),
            new Country("VN", "Vietnam", "فيتنام", "+84", R.drawable.flag_vn),
            new Country("VU", "Vanuatu", "فانواتو", "+678", R.drawable.flag_vu),
            new Country("WF", "Wallis and Futuna", "والس وفوتونا", "+681", R.drawable.flag_wf),
            new Country("WS", "Samoa", "ساموا", "+685", R.drawable.flag_ws),
            new Country("XK", "Kosovo", "كوسوفو", "+383", R.drawable.flag_xk),
            new Country("YE", "Yemen", "اليمن", "+967", R.drawable.flag_ye),
            new Country("YT", "Mayotte", "مايوت", "+262", R.drawable.flag_yt),
            new Country("ZA", "South Africa", "جنوب أفريقيا", "+27", R.drawable.flag_za),
            new Country("ZM", "Zambia", "زامبيا", "+260", R.drawable.flag_zm),
            new Country("ZW", "Zimbabwe", "زمبابوي", "+263", R.drawable.flag_zw),
    };

    private String code;
    private String name;


    private String nameAr;
    private String dialCode;
    private int flag = -1;

    public Country(String code, String name, String nameAr, String dialCode, int flag) {
        this.code = code;
        this.name = name;
        this.nameAr = nameAr;
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

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
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