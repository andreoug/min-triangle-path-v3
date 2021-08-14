package com.suprnation.openbook;

public enum  ErrorMessage {

    IO_ERROR(                   "001 - FILE SYSTEM ERROR"),
    NO_DATA_AVAILABLE_ERROR(    "002 - NO DATA AVAILABLE ERROR"),
    SYSTEM_ERROR(               "003 - SYSTEM ERROR");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
