package com.mukesh.countrypicker;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 */

public class Country {

    private static Country[] COUNTRIES;

    public static Country[] getCountries(Context context){
        if(COUNTRIES == null)
            COUNTRIES = new Country[]{
                    new Country("AD", getCountryName(context, R.string.andorra, "Andorra"), "+376", R.drawable.flag_ad),
                    new Country("AE", getCountryName(context, R.string.united_arab_emirates, "United Arab Emirates"), "+971", R.drawable.flag_ae),
                    new Country("AF", getCountryName(context, R.string.afghanistan, "Afghanistan"), "+93", R.drawable.flag_af),
                    new Country("AG", getCountryName(context, R.string.antigua_and_barbuda, "Antigua and Barbuda"), "+1", R.drawable.flag_ag),
                    new Country("AI", getCountryName(context, R.string.anguilla, "Anguilla"), "+1", R.drawable.flag_ai),
                    new Country("AL", getCountryName(context, R.string.albania, "Albania"), "+355", R.drawable.flag_al),
                    new Country("AM", getCountryName(context, R.string.armenia, "Armenia"), "+374", R.drawable.flag_am),
                    new Country("AO", getCountryName(context, R.string.angola, "Angola"), "+244", R.drawable.flag_ao),
                    new Country("AQ", getCountryName(context, R.string.antarctica, "Antarctica"), "+672", R.drawable.flag_aq),
                    new Country("AR", getCountryName(context, R.string.argentina, "Argentina"), "+54", R.drawable.flag_ar),
                    new Country("AS", getCountryName(context, R.string.americansamoa, "AmericanSamoa"), "+1", R.drawable.flag_as),
                    new Country("AT", getCountryName(context, R.string.austria, "Austria"), "+43", R.drawable.flag_at),
                    new Country("AU", getCountryName(context, R.string.australia, "Australia"), "+61", R.drawable.flag_au),
                    new Country("AW", getCountryName(context, R.string.aruba, "Aruba"), "+297", R.drawable.flag_aw),
                    new Country("AX", getCountryName(context, R.string.aland_islands, "Åland Islands"), "+358", R.drawable.flag_ax),
                    new Country("AZ", getCountryName(context, R.string.azerbaijan, "Azerbaijan"), "+994", R.drawable.flag_az),
                    new Country("BA", getCountryName(context, R.string.bosnia_and_herzegovina, "Bosnia and Herzegovina"), "+387", R.drawable.flag_ba),
                    new Country("BB", getCountryName(context, R.string.barbados, "Barbados"), "+1", R.drawable.flag_bb),
                    new Country("BD", getCountryName(context, R.string.bangladesh, "Bangladesh"), "+880", R.drawable.flag_bd),
                    new Country("BE", getCountryName(context, R.string.belgium, "Belgium"), "+32", R.drawable.flag_be),
                    new Country("BF", getCountryName(context, R.string.burkina_faso, "Burkina Faso"), "+226", R.drawable.flag_bf),
                    new Country("BG", getCountryName(context, R.string.bulgaria, "Bulgaria"), "+359", R.drawable.flag_bg),
                    new Country("BH", getCountryName(context, R.string.bahrain, "Bahrain"), "+973", R.drawable.flag_bh),
                    new Country("BI", getCountryName(context, R.string.burundi, "Burundi"), "+257", R.drawable.flag_bi),
                    new Country("BJ", getCountryName(context, R.string.benin, "Benin"), "+229", R.drawable.flag_bj),
                    new Country("BL", getCountryName(context, R.string.saint_barthelemy, "Saint Barthélemy"), "+590", R.drawable.flag_bl),
                    new Country("BM", getCountryName(context, R.string.bermuda, "Bermuda"), "+1", R.drawable.flag_bm),
                    new Country("BN", getCountryName(context, R.string.brunei_darussalam, "Brunei Darussalam"), "+673", R.drawable.flag_bn),
                    new Country("BO", getCountryName(context, R.string.bolivia_plurinational_state_of, "Bolivia, Plurinational State of"), "+591", R.drawable.flag_bo),
                    new Country("BQ", getCountryName(context, R.string.bonaire, "Bonaire"), "+599", R.drawable.flag_bq),
                    new Country("BR", getCountryName(context, R.string.brazil, "Brazil"), "+55", R.drawable.flag_br),
                    new Country("BS", getCountryName(context, R.string.bahamas, "Bahamas"), "+1", R.drawable.flag_bs),
                    new Country("BT", getCountryName(context, R.string.bhutan, "Bhutan"), "+975", R.drawable.flag_bt),
                    new Country("BV", getCountryName(context, R.string.bouvet_island, "Bouvet Island"), "+47", R.drawable.flag_bv),
                    new Country("BW", getCountryName(context, R.string.botswana, "Botswana"), "+267", R.drawable.flag_bw),
                    new Country("BY", getCountryName(context, R.string.belarus, "Belarus"), "+375", R.drawable.flag_by),
                    new Country("BZ", getCountryName(context, R.string.belize, "Belize"), "+501", R.drawable.flag_bz),
                    new Country("CA", getCountryName(context, R.string.canada, "Canada"), "+1", R.drawable.flag_ca),
                    new Country("CC", getCountryName(context, R.string.cocos_keeling_islands, "Cocos (Keeling) Islands"), "+61", R.drawable.flag_cc),
                    new Country("CD", getCountryName(context, R.string.congo_the_democratic_republic_of_the, "Congo, The Democratic Republic of the"), "+243", R.drawable.flag_cd),
                    new Country("CF", getCountryName(context, R.string.central_african_republic, "Central African Republic"), "+236", R.drawable.flag_cf),
                    new Country("CG", getCountryName(context, R.string.congo, "Congo"), "+242", R.drawable.flag_cg),
                    new Country("CH", getCountryName(context, R.string.switzerland, "Switzerland"), "+41", R.drawable.flag_ch),
                    new Country("CI", getCountryName(context, R.string.ivory_coast, "Ivory Coast"), "+225", R.drawable.flag_ci),
                    new Country("CK", getCountryName(context, R.string.cook_islands, "Cook Islands"), "+682", R.drawable.flag_ck),
                    new Country("CL", getCountryName(context, R.string.chile, "Chile"), "+56", R.drawable.flag_cl),
                    new Country("CM", getCountryName(context, R.string.cameroon, "Cameroon"), "+237", R.drawable.flag_cm),
                    new Country("CN", getCountryName(context, R.string.china, "China"), "+86", R.drawable.flag_cn),
                    new Country("CO", getCountryName(context, R.string.colombia, "Colombia"), "+57", R.drawable.flag_co),
                    new Country("CR", getCountryName(context, R.string.costa_rica, "Costa Rica"), "+506", R.drawable.flag_cr),
                    new Country("CU", getCountryName(context, R.string.cuba, "Cuba"), "+53", R.drawable.flag_cu),
                    new Country("CV", getCountryName(context, R.string.cape_verde, "Cape Verde"), "+238", R.drawable.flag_cv),
                    new Country("CW", getCountryName(context, R.string.curacao, "Curacao"), "+599", R.drawable.flag_cw),
                    new Country("CX", getCountryName(context, R.string.christmas_island, "Christmas Island"), "+61", R.drawable.flag_cx),
                    new Country("CY", getCountryName(context, R.string.cyprus, "Cyprus"), "+357", R.drawable.flag_cy),
                    new Country("CZ", getCountryName(context, R.string.czech_republic, "Czech Republic"), "+420", R.drawable.flag_cz),
                    new Country("DE", getCountryName(context, R.string.germany, "Germany"), "+49", R.drawable.flag_de),
                    new Country("DJ", getCountryName(context, R.string.djibouti, "Djibouti"), "+253", R.drawable.flag_dj),
                    new Country("DK", getCountryName(context, R.string.denmark, "Denmark"), "+45", R.drawable.flag_dk),
                    new Country("DM", getCountryName(context, R.string.dominica, "Dominica"), "+1", R.drawable.flag_dm),
                    new Country("DO", getCountryName(context, R.string.dominican_republic, "Dominican Republic"), "+1", R.drawable.flag_do),
                    new Country("DZ", getCountryName(context, R.string.algeria, "Algeria"), "+213", R.drawable.flag_dz),
                    new Country("EC", getCountryName(context, R.string.ecuador, "Ecuador"), "+593", R.drawable.flag_ec),
                    new Country("EE", getCountryName(context, R.string.estonia, "Estonia"), "+372", R.drawable.flag_ee),
                    new Country("EG", getCountryName(context, R.string.egypt, "Egypt"), "+20", R.drawable.flag_eg),
                    new Country("EH", getCountryName(context, R.string.western_sahara, "Western Sahara"), "+212", R.drawable.flag_eh),
                    new Country("ER", getCountryName(context, R.string.eritrea, "Eritrea"), "+291", R.drawable.flag_er),
                    new Country("ES", getCountryName(context, R.string.spain, "Spain"), "+34", R.drawable.flag_es),
                    new Country("ET", getCountryName(context, R.string.ethiopia, "Ethiopia"), "+251", R.drawable.flag_et),
                    new Country("FI", getCountryName(context, R.string.finland, "Finland"), "+358", R.drawable.flag_fi),
                    new Country("FJ", getCountryName(context, R.string.fiji, "Fiji"), "+679", R.drawable.flag_fj),
                    new Country("FK", getCountryName(context, R.string.falkland_islands_malvinas, "Falkland Islands (Malvinas)"), "+500", R.drawable.flag_fk),
                    new Country("FM", getCountryName(context, R.string.micronesia_federated_states_of, "Micronesia, Federated States of"), "+691", R.drawable.flag_fm),
                    new Country("FO", getCountryName(context, R.string.faroe_islands, "Faroe Islands"), "+298", R.drawable.flag_fo),
                    new Country("FR", getCountryName(context, R.string.france, "France"), "+33", R.drawable.flag_fr),
                    new Country("GA", getCountryName(context, R.string.gabon, "Gabon"), "+241", R.drawable.flag_ga),
                    new Country("GB", getCountryName(context, R.string.united_kingdom, "United Kingdom"), "+44", R.drawable.flag_gb),
                    new Country("GD", getCountryName(context, R.string.grenada, "Grenada"), "+1", R.drawable.flag_gd),
                    new Country("GE", getCountryName(context, R.string.georgia, "Georgia"), "+995", R.drawable.flag_ge),
                    new Country("GF", getCountryName(context, R.string.french_guiana, "French Guiana"), "+594", R.drawable.flag_gf),
                    new Country("GG", getCountryName(context, R.string.guernsey, "Guernsey"), "+44", R.drawable.flag_gg),
                    new Country("GH", getCountryName(context, R.string.ghana, "Ghana"), "+233", R.drawable.flag_gh),
                    new Country("GI", getCountryName(context, R.string.gibraltar, "Gibraltar"), "+350", R.drawable.flag_gi),
                    new Country("GL", getCountryName(context, R.string.greenland, "Greenland"), "+299", R.drawable.flag_gl),
                    new Country("GM", getCountryName(context, R.string.gambia, "Gambia"), "+220", R.drawable.flag_gm),
                    new Country("GN", getCountryName(context, R.string.guinea, "Guinea"), "+224", R.drawable.flag_gn),
                    new Country("GP", getCountryName(context, R.string.guadeloupe, "Guadeloupe"), "+590", R.drawable.flag_gp),
                    new Country("GQ", getCountryName(context, R.string.equatorial_guinea, "Equatorial Guinea"), "+240", R.drawable.flag_gq),
                    new Country("GR", getCountryName(context, R.string.greece, "Greece"), "+30", R.drawable.flag_gr),
                    new Country("GS", getCountryName(context, R.string.south_georgia_and_the_south_sandwich_islands, "South Georgia and the South Sandwich Islands"), "+500", R.drawable.flag_gs),
                    new Country("GT", getCountryName(context, R.string.guatemala, "Guatemala"), "+502", R.drawable.flag_gt),
                    new Country("GU", getCountryName(context, R.string.guam, "Guam"), "+1", R.drawable.flag_gu),
                    new Country("GW", getCountryName(context, R.string.guinea_bissau, "Guinea-Bissau"), "+245", R.drawable.flag_gw),
                    new Country("GY", getCountryName(context, R.string.guyana, "Guyana"), "+595", R.drawable.flag_gy),
                    new Country("HK", getCountryName(context, R.string.hong_kong, "Hong Kong"), "+852", R.drawable.flag_hk),
                    new Country("HM", getCountryName(context, R.string.heard_island_and_mcdonald_islands, "Heard Island and McDonald Islands"), "", R.drawable.flag_hm),
                    new Country("HN", getCountryName(context, R.string.honduras, "Honduras"), "+504", R.drawable.flag_hn),
                    new Country("HR", getCountryName(context, R.string.croatia, "Croatia"), "+385", R.drawable.flag_hr),
                    new Country("HT", getCountryName(context, R.string.haiti, "Haiti"), "+509", R.drawable.flag_ht),
                    new Country("HU", getCountryName(context, R.string.hungary, "Hungary"), "+36", R.drawable.flag_hu),
                    new Country("ID", getCountryName(context, R.string.indonesia, "Indonesia"), "+62", R.drawable.flag_id),
                    new Country("IE", getCountryName(context, R.string.ireland, "Ireland"), "+353", R.drawable.flag_ie),
                    new Country("IL", getCountryName(context, R.string.israel, "Israel"), "+972", R.drawable.flag_il),
                    new Country("IM", getCountryName(context, R.string.isle_of_man, "Isle of Man"), "+44", R.drawable.flag_im),
                    new Country("IN", getCountryName(context, R.string.india, "India"), "+91", R.drawable.flag_in),
                    new Country("IO", getCountryName(context, R.string.british_indian_ocean_territory, "British Indian Ocean Territory"), "+246", R.drawable.flag_io),
                    new Country("IQ", getCountryName(context, R.string.iraq, "Iraq"), "+964", R.drawable.flag_iq),
                    new Country("IR", getCountryName(context, R.string.iran_islamic_republic_of, "Iran, Islamic Republic of"), "+98", R.drawable.flag_ir),
                    new Country("IS", getCountryName(context, R.string.iceland, "Iceland"), "+354", R.drawable.flag_is),
                    new Country("IT", getCountryName(context, R.string.italy, "Italy"), "+39", R.drawable.flag_it),
                    new Country("JE", getCountryName(context, R.string.jersey, "Jersey"), "+44", R.drawable.flag_je),
                    new Country("JM", getCountryName(context, R.string.jamaica, "Jamaica"), "+1", R.drawable.flag_jm),
                    new Country("JO", getCountryName(context, R.string.jordan, "Jordan"), "+962", R.drawable.flag_jo),
                    new Country("JP", getCountryName(context, R.string.japan, "Japan"), "+81", R.drawable.flag_jp),
                    new Country("KE", getCountryName(context, R.string.kenya, "Kenya"), "+254", R.drawable.flag_ke),
                    new Country("KG", getCountryName(context, R.string.kyrgyzstan, "Kyrgyzstan"), "+996", R.drawable.flag_kg),
                    new Country("KH", getCountryName(context, R.string.cambodia, "Cambodia"), "+855", R.drawable.flag_kh),
                    new Country("KI", getCountryName(context, R.string.kiribati, "Kiribati"), "+686", R.drawable.flag_ki),
                    new Country("KM", getCountryName(context, R.string.comoros, "Comoros"), "+269", R.drawable.flag_km),
                    new Country("KN", getCountryName(context, R.string.saint_kitts_and_nevis, "Saint Kitts and Nevis"), "+1", R.drawable.flag_kn),
                    new Country("KP", getCountryName(context, R.string.north_korea, "North Korea"), "+850", R.drawable.flag_kp),
                    new Country("KR", getCountryName(context, R.string.south_korea, "South Korea"), "+82", R.drawable.flag_kr),
                    new Country("KW", getCountryName(context, R.string.kuwait, "Kuwait"), "+965", R.drawable.flag_kw),
                    new Country("KY", getCountryName(context, R.string.cayman_islands, "Cayman Islands"), "+345", R.drawable.flag_ky),
                    new Country("KZ", getCountryName(context, R.string.kazakhstan, "Kazakhstan"), "+7", R.drawable.flag_kz),
                    new Country("LA", getCountryName(context, R.string.lao_peoples_democratic_republic, "Lao People's Democratic Republic"), "+856", R.drawable.flag_la),
                    new Country("LB", getCountryName(context, R.string.lebanon, "Lebanon"), "+961", R.drawable.flag_lb),
                    new Country("LC", getCountryName(context, R.string.saint_lucia, "Saint Lucia"), "+1", R.drawable.flag_lc),
                    new Country("LI", getCountryName(context, R.string.liechtenstein, "Liechtenstein"), "+423", R.drawable.flag_li),
                    new Country("LK", getCountryName(context, R.string.sri_lanka, "Sri Lanka"), "+94", R.drawable.flag_lk),
                    new Country("LR", getCountryName(context, R.string.liberia, "Liberia"), "+231", R.drawable.flag_lr),
                    new Country("LS", getCountryName(context, R.string.lesotho, "Lesotho"), "+266", R.drawable.flag_ls),
                    new Country("LT", getCountryName(context, R.string.lithuania, "Lithuania"), "+370", R.drawable.flag_lt),
                    new Country("LU", getCountryName(context, R.string.luxembourg, "Luxembourg"), "+352", R.drawable.flag_lu),
                    new Country("LV", getCountryName(context, R.string.latvia, "Latvia"), "+371", R.drawable.flag_lv),
                    new Country("LY", getCountryName(context, R.string.libyan_arab_jamahiriya, "Libyan Arab Jamahiriya"), "+218", R.drawable.flag_ly),
                    new Country("MA", getCountryName(context, R.string.morocco, "Morocco"), "+212", R.drawable.flag_ma),
                    new Country("MC", getCountryName(context, R.string.monaco, "Monaco"), "+377", R.drawable.flag_mc),
                    new Country("MD", getCountryName(context, R.string.moldova_republic_of, "Moldova, Republic of"), "+373", R.drawable.flag_md),
                    new Country("ME", getCountryName(context, R.string.montenegro, "Montenegro"), "+382", R.drawable.flag_me),
                    new Country("MF", getCountryName(context, R.string.saint_martin, "Saint Martin"), "+590", R.drawable.flag_mf),
                    new Country("MG", getCountryName(context, R.string.madagascar, "Madagascar"), "+261", R.drawable.flag_mg),
                    new Country("MH", getCountryName(context, R.string.marshall_islands, "Marshall Islands"), "+692", R.drawable.flag_mh),
                    new Country("MK", getCountryName(context, R.string.macedonia_the_former_yugoslav_republic_of, "Macedonia, The Former Yugoslav Republic of"), "+389", R.drawable.flag_mk),
                    new Country("ML", getCountryName(context, R.string.mali, "Mali"), "+223", R.drawable.flag_ml),
                    new Country("MM", getCountryName(context, R.string.myanmar, "Myanmar"), "+95", R.drawable.flag_mm),
                    new Country("MN", getCountryName(context, R.string.mongolia, "Mongolia"), "+976", R.drawable.flag_mn),
                    new Country("MO", getCountryName(context, R.string.macao, "Macao"), "+853", R.drawable.flag_mo),
                    new Country("MP", getCountryName(context, R.string.northern_mariana_islands, "Northern Mariana Islands"), "+1", R.drawable.flag_mp),
                    new Country("MQ", getCountryName(context, R.string.martinique, "Martinique"), "+596", R.drawable.flag_mq),
                    new Country("MR", getCountryName(context, R.string.mauritania, "Mauritania"), "+222", R.drawable.flag_mr),
                    new Country("MS", getCountryName(context, R.string.montserrat, "Montserrat"), "+1", R.drawable.flag_ms),
                    new Country("MT", getCountryName(context, R.string.malta, "Malta"), "+356", R.drawable.flag_mt),
                    new Country("MU", getCountryName(context, R.string.mauritius, "Mauritius"), "+230", R.drawable.flag_mu),
                    new Country("MV", getCountryName(context, R.string.maldives, "Maldives"), "+960", R.drawable.flag_mv),
                    new Country("MW", getCountryName(context, R.string.malawi, "Malawi"), "+265", R.drawable.flag_mw),
                    new Country("MX", getCountryName(context, R.string.mexico, "Mexico"), "+52", R.drawable.flag_mx),
                    new Country("MY", getCountryName(context, R.string.malaysia, "Malaysia"), "+60", R.drawable.flag_my),
                    new Country("MZ", getCountryName(context, R.string.mozambique, "Mozambique"), "+258", R.drawable.flag_mz),
                    new Country("NA", getCountryName(context, R.string.namibia, "Namibia"), "+264", R.drawable.flag_na),
                    new Country("NC", getCountryName(context, R.string.new_caledonia, "New Caledonia"), "+687", R.drawable.flag_nc),
                    new Country("NE", getCountryName(context, R.string.niger, "Niger"), "+227", R.drawable.flag_ne),
                    new Country("NF", getCountryName(context, R.string.norfolk_island, "Norfolk Island"), "+672", R.drawable.flag_nf),
                    new Country("NG", getCountryName(context, R.string.nigeria, "Nigeria"), "+234", R.drawable.flag_ng),
                    new Country("NI", getCountryName(context, R.string.nicaragua, "Nicaragua"), "+505", R.drawable.flag_ni),
                    new Country("NL", getCountryName(context, R.string.netherlands, "Netherlands"), "+31", R.drawable.flag_nl),
                    new Country("NO", getCountryName(context, R.string.norway, "Norway"), "+47", R.drawable.flag_no),
                    new Country("NP", getCountryName(context, R.string.nepal, "Nepal"), "+977", R.drawable.flag_np),
                    new Country("NR", getCountryName(context, R.string.nauru, "Nauru"), "+674", R.drawable.flag_nr),
                    new Country("NU", getCountryName(context, R.string.niue, "Niue"), "+683", R.drawable.flag_nu),
                    new Country("NZ", getCountryName(context, R.string.new_zealand, "New Zealand"), "+64", R.drawable.flag_nz),
                    new Country("OM", getCountryName(context, R.string.oman, "Oman"), "+968", R.drawable.flag_om),
                    new Country("PA", getCountryName(context, R.string.panama, "Panama"), "+507", R.drawable.flag_pa),
                    new Country("PE", getCountryName(context, R.string.peru, "Peru"), "+51", R.drawable.flag_pe),
                    new Country("PF", getCountryName(context, R.string.french_polynesia, "French Polynesia"), "+689", R.drawable.flag_pf),
                    new Country("PG", getCountryName(context, R.string.papua_new_guinea, "Papua New Guinea"), "+675", R.drawable.flag_pg),
                    new Country("PH", getCountryName(context, R.string.philippines, "Philippines"), "+63", R.drawable.flag_ph),
                    new Country("PK", getCountryName(context, R.string.pakistan, "Pakistan"), "+92", R.drawable.flag_pk),
                    new Country("PL", getCountryName(context, R.string.poland, "Poland"), "+48", R.drawable.flag_pl),
                    new Country("PM", getCountryName(context, R.string.saint_pierre_and_miquelon, "Saint Pierre and Miquelon"), "+508", R.drawable.flag_pm),
                    new Country("PN", getCountryName(context, R.string.pitcairn, "Pitcairn"), "+872", R.drawable.flag_pn),
                    new Country("PR", getCountryName(context, R.string.puerto_rico, "Puerto Rico"), "+1", R.drawable.flag_pr),
                    new Country("PS", getCountryName(context, R.string.palestinian_territory_occupied, "Palestinian Territory, Occupied"), "+970", R.drawable.flag_ps),
                    new Country("PT", getCountryName(context, R.string.portugal, "Portugal"), "+351", R.drawable.flag_pt),
                    new Country("PW", getCountryName(context, R.string.palau, "Palau"), "+680", R.drawable.flag_pw),
                    new Country("PY", getCountryName(context, R.string.paraguay, "Paraguay"), "+595", R.drawable.flag_py),
                    new Country("QA", getCountryName(context, R.string.qatar, "Qatar"), "+974", R.drawable.flag_qa),
                    new Country("RE", getCountryName(context, R.string.reunion, "Réunion"), "+262", R.drawable.flag_re),
                    new Country("RO", getCountryName(context, R.string.romania, "Romania"), "+40", R.drawable.flag_ro),
                    new Country("RS", getCountryName(context, R.string.serbia, "Serbia"), "+381", R.drawable.flag_rs),
                    new Country("RU", getCountryName(context, R.string.russia, "Russia"), "+7", R.drawable.flag_ru),
                    new Country("RW", getCountryName(context, R.string.rwanda, "Rwanda"), "+250", R.drawable.flag_rw),
                    new Country("SA", getCountryName(context, R.string.saudi_arabia, "Saudi Arabia"), "+966", R.drawable.flag_sa),
                    new Country("SB", getCountryName(context, R.string.solomon_islands, "Solomon Islands"), "+677", R.drawable.flag_sb),
                    new Country("SC", getCountryName(context, R.string.seychelles, "Seychelles"), "+248", R.drawable.flag_sc),
                    new Country("SD", getCountryName(context, R.string.sudan, "Sudan"), "+249", R.drawable.flag_sd),
                    new Country("SE", getCountryName(context, R.string.sweden, "Sweden"), "+46", R.drawable.flag_se),
                    new Country("SG", getCountryName(context, R.string.singapore, "Singapore"), "+65", R.drawable.flag_sg),
                    new Country("SH", getCountryName(context, R.string.saint_helena_ascension_and_tristan_da_cunha, "Saint Helena, Ascension and Tristan Da Cunha"), "+290", R.drawable.flag_sh),
                    new Country("SI", getCountryName(context, R.string.slovenia, "Slovenia"), "+386", R.drawable.flag_si),
                    new Country("SJ", getCountryName(context, R.string.svalbard_and_jan_mayen, "Svalbard and Jan Mayen"), "+47", R.drawable.flag_sj),
                    new Country("SK", getCountryName(context, R.string.slovakia, "Slovakia"), "+421", R.drawable.flag_sk),
                    new Country("SL", getCountryName(context, R.string.sierra_leone, "Sierra Leone"), "+232", R.drawable.flag_sl),
                    new Country("SM", getCountryName(context, R.string.san_marino, "San Marino"), "+378", R.drawable.flag_sm),
                    new Country("SN", getCountryName(context, R.string.senegal, "Senegal"), "+221", R.drawable.flag_sn),
                    new Country("SO", getCountryName(context, R.string.somalia, "Somalia"), "+252", R.drawable.flag_so),
                    new Country("SR", getCountryName(context, R.string.suriname, "Suriname"), "+597", R.drawable.flag_sr),
                    new Country("SS", getCountryName(context, R.string.south_sudan, "South Sudan"), "+211", R.drawable.flag_ss),
                    new Country("ST", getCountryName(context, R.string.sao_tome_and_principe, "Sao Tome and Principe"), "+239", R.drawable.flag_st),
                    new Country("SV", getCountryName(context, R.string.el_salvador, "El Salvador"), "+503", R.drawable.flag_sv),
                    new Country("SX", getCountryName(context, R.string.sint_maarten, "Sint Maarten"), "+1", R.drawable.flag_sx),
                    new Country("SY", getCountryName(context, R.string.syrian_arab_republic, "Syrian Arab Republic"), "+963", R.drawable.flag_sy),
                    new Country("SZ", getCountryName(context, R.string.swaziland, "Swaziland"), "+268", R.drawable.flag_sz),
                    new Country("TC", getCountryName(context, R.string.turks_and_caicos_islands, "Turks and Caicos Islands"), "+1", R.drawable.flag_tc),
                    new Country("TD", getCountryName(context, R.string.chad, "Chad"), "+235", R.drawable.flag_td),
                    new Country("TF", getCountryName(context, R.string.french_southern_territories, "French Southern Territories"), "+262", R.drawable.flag_tf),
                    new Country("TG", getCountryName(context, R.string.togo, "Togo"), "+228", R.drawable.flag_tg),
                    new Country("TH", getCountryName(context, R.string.thailand, "Thailand"), "+66", R.drawable.flag_th),
                    new Country("TJ", getCountryName(context, R.string.tajikistan, "Tajikistan"), "+992", R.drawable.flag_tj),
                    new Country("TK", getCountryName(context, R.string.tokelau, "Tokelau"), "+690", R.drawable.flag_tk),
                    new Country("TL", getCountryName(context, R.string.east_timor, "East Timor"), "+670", R.drawable.flag_tl),
                    new Country("TM", getCountryName(context, R.string.turkmenistan, "Turkmenistan"), "+993", R.drawable.flag_tm),
                    new Country("TN", getCountryName(context, R.string.tunisia, "Tunisia"), "+216", R.drawable.flag_tn),
                    new Country("TO", getCountryName(context, R.string.tonga, "Tonga"), "+676", R.drawable.flag_to),
                    new Country("TR", getCountryName(context, R.string.turkey, "Turkey"), "+90", R.drawable.flag_tr),
                    new Country("TT", getCountryName(context, R.string.trinidad_and_tobago, "Trinidad and Tobago"), "+1", R.drawable.flag_tt),
                    new Country("TV", getCountryName(context, R.string.tuvalu, "Tuvalu"), "+688", R.drawable.flag_tv),
                    new Country("TW", getCountryName(context, R.string.taiwan, "Taiwan"), "+886", R.drawable.flag_tw),
                    new Country("TZ", getCountryName(context, R.string.tanzania_united_republic_of, "Tanzania, United Republic of"), "+255", R.drawable.flag_tz),
                    new Country("UA", getCountryName(context, R.string.ukraine, "Ukraine"), "+380", R.drawable.flag_ua),
                    new Country("UG", getCountryName(context, R.string.uganda, "Uganda"), "+256", R.drawable.flag_ug),
                    new Country("UM", getCountryName(context, R.string.us_minor_outlying_islands, "U.S. Minor Outlying Islands"), "", R.drawable.flag_um),
                    new Country("US", getCountryName(context, R.string.united_states, "United States"), "+1", R.drawable.flag_us),
                    new Country("UY", getCountryName(context, R.string.uruguay, "Uruguay"), "+598", R.drawable.flag_uy),
                    new Country("UZ", getCountryName(context, R.string.uzbekistan, "Uzbekistan"), "+998", R.drawable.flag_uz),
                    new Country("VA", getCountryName(context, R.string.holy_see_vatican_city_state, "Holy See (Vatican City State)"), "+379", R.drawable.flag_va),
                    new Country("VC", getCountryName(context, R.string.saint_vincent_and_the_grenadines, "Saint Vincent and the Grenadines"), "+1", R.drawable.flag_vc),
                    new Country("VE", getCountryName(context, R.string.venezuela_bolivarian_republic_of, "Venezuela, Bolivarian Republic of"), "+58", R.drawable.flag_ve),
                    new Country("VG", getCountryName(context, R.string.virgin_islands_british, "Virgin Islands, British"), "+1", R.drawable.flag_vg),
                    new Country("VI", getCountryName(context, R.string.virgin_islands_us, "Virgin Islands, U.S."), "+1", R.drawable.flag_vi),
                    new Country("VN", getCountryName(context, R.string.viet_nam, "Viet Nam"), "+84", R.drawable.flag_vn),
                    new Country("VU", getCountryName(context, R.string.vanuatu, "Vanuatu"), "+678", R.drawable.flag_vu),
                    new Country("WF", getCountryName(context, R.string.wallis_and_futuna, "Wallis and Futuna"), "+681", R.drawable.flag_wf),
                    new Country("WS", getCountryName(context, R.string.samoa, "Samoa"), "+685", R.drawable.flag_ws),
                    new Country("XK", getCountryName(context, R.string.kosovo, "Kosovo"), "+383", R.drawable.flag_xk),
                    new Country("YE", getCountryName(context, R.string.yemen, "Yemen"), "+967", R.drawable.flag_ye),
                    new Country("YT", getCountryName(context, R.string.mayotte, "Mayotte"), "+262", R.drawable.flag_yt),
                    new Country("ZA", getCountryName(context, R.string.south_africa, "South Africa"), "+27", R.drawable.flag_za),
                    new Country("ZM", getCountryName(context, R.string.zambia, "Zambia"), "+260", R.drawable.flag_zm),
                    new Country("ZW", getCountryName(context, R.string.zimbabwe, "Zimbabwe"), "+263", R.drawable.flag_zw),

            };
        return COUNTRIES;
    }

    private static String getCountryName(Context context, @StringRes int resNameId, @NotNull String defaultName){
        return context!=null?context.getString(resNameId): defaultName;
    }

    private String code;
    private String name;
    private String dialCode;
    private int flag = -1;

    public Country(String code, String name, String dialCode, @DrawableRes int flag) {
        this.code = code;
        this.name = name;
        this.dialCode = dialCode;
        this.flag = flag;
    }

    public Country() {
    }

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

    @DrawableRes
    public int getFlag() {
        return flag;
    }

    public void setFlag(@DrawableRes int flag) {
        this.flag = flag;
    }

    public void loadFlagByCode(@NotNull Context context) {
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

    public static List<Country> getAllCountries(Context context) {
        if (allCountriesList == null) {
            allCountriesList = Arrays.asList(Country.getCountries(context));
        }
        return allCountriesList;
    }

    public static List<Country> getAllCountries(){
        return Country.getAllCountries(null);
    }

    public static Country getCountryByISO(String countryIsoCode, Context context) {
        countryIsoCode = countryIsoCode.toUpperCase();

        Country c = new Country();
        c.setCode(countryIsoCode);

        int i = Arrays.binarySearch(Country.getCountries(context), c, new ISOCodeComparator());

        if (i < 0) {
            return null;
        } else {
            return Country.getCountries(context)[i];
        }
    }

    public static Country getCountryByISO(String countryIsoCode) {
        return Country.getCountryByISO(countryIsoCode, null);
    }

    public static Country getCountryByName(String countryName, Context context) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // countries one by one

        for (Country c : Country.getCountries(context)) {
            if (countryName.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    public static Country getCountryByName(String countryName) {
        return Country.getCountryByName(countryName, null);
    }

    public static Country getCountryByLocale(Locale locale, Context context) {
        String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
        return Country.getCountryByISO(countryIsoCode, context);
    }

    public static Country getCountryByLocale(Locale locale) {
        return Country.getCountryByLocale(locale, null);
    }

    public static Country getCountryFromSIM(Context context) {
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
            return Country.getCountryByISO(telephonyManager.getSimCountryIso(), context);
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