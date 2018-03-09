package com.mukesh.countrypicker;
/**
 * Created by Bashayer Alsalman
 * 9 Mar 2018
 */
enum Languages {
    ENGLISH("en"),
    ARABIC("ar");

    public String code;

    Languages(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
