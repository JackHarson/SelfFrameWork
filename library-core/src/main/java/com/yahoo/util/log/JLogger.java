package com.yahoo.util.log;

import com.orhanobut.logger.Logger;

/**
 * Created by ZhangJin on 2022/4/23 10:03 上午.
 * Describe:
 */
public final class JLogger {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;
    private static final String TAG = "TAG";

    //控制log等级
    private static final int LEVEL = NOTHING;

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(message);
        }
    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void d(String tag, Object message) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message) {
        d(TAG, message);
    }


    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(message);
        }
    }

    public static void i(String message) {
        i(TAG, message);
    }


    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(message);
        }
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void json(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).json(message);
        }
    }

    public static void json(String message) {
        json(TAG, message);
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(message);
        }
    }

    public static void e(String message) {
        e(TAG, message);
    }
}
