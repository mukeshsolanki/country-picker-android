package com.mukesh.countrypicker.models;

/**
 * Created by mukesh on 25/04/16.
 */

public class Country {
  private String code;
  private String name;
  private String dialCode;
  private int flag;

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
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }
}