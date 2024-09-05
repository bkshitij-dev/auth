package com.example.demoauth.constant;

import com.example.demoauth.exception.AppException;

public final class AppConstants {

    private AppConstants() {
        throw new AppException(NOT_INSTANTIABLE);
    }

    public static final String NOT_INSTANTIABLE = "Class is final and not instantiable";

    public static final String SUCCESS_SAVE = "Data saved successfully";
    public static final String SUCCESS_RETRIEVE = "Data retrieved successfully";
    public static final String SUCCESS_UPDATE = "Data updated successfully";
    public static final String SUCCESS_REMOVE = "Data removed successfully";

}
