package com.suprnation.openbook;

/**
 * Created by gandreou on 02/08/2021.
 */
public enum  ErrorMessage {

    IO_ERROR(                   "001 - FILE SYSTEM ERROR"),
    NO_DATA_AVAILABLE_ERROR(    "002 - NO DATA AVAILABLE ERROR"),
    SYSTEM_ERROR(               "003 - SYSTEM ERROR");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static ErrorMessage fromString(String text) {
        if (text != null) {
            for (ErrorMessage b : ErrorMessage.values()) {
                if (text.equalsIgnoreCase(b.message)) {
                    return b;
                }
            }
        }
        return null;
    }
}
