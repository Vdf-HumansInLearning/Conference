package com.vodafone.conference.exceptions;

import lombok.ToString;

public class ApiRequestException extends RuntimeException {

    @ToString
    public enum Exceptions {
        ID_NOT_FOUND(0, "Id not found! "),
        DATE_NOT_FOUND(1, "Date not found! "),
        TITLE_NOT_FOUND(2, "Title not found! "),

        CONFERENCE_NOT_FOUND(3, "No Conference Found"),
        BAD_INPUT(4, "Incorrect or incomplete input!");

        private final int code;
        private final String description;

        Exceptions(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static String getDescription(Exceptions exception, String... args) {
            return exception.description + args[0];
        }

    }

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
