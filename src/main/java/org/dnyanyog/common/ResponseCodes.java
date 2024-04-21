package org.dnyanyog.common;

public enum ResponseCodes {
  USER_ADD_SUCCESS("Success", "User created successfuly"),
  USER_UPDATE_SUCCESS("Success", "User update successfuly"),
  USER_LOGIN_SUCCESS("Success", "Login successful"),
  USER_LOGIN_FAIL("Fail", "Login failed"),
  USER_FOUND_SUCCESS("Success", "User found"),
  USER_NOT_FOUND("Fail", "User not found");

  private final String code;
  private final String message;

  ResponseCodes(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
