package com.drivetuningsh.constant;

import java.nio.charset.Charset;
import java.util.Locale;

public enum AppConstant {

    DEFAULT_LOCALE(Locale.forLanguageTag("ru")),
    DEFAULT_ENCODING(Charset.forName("UTF-8"));

    AppConstant(Locale locale) {
        this.locale = locale;
    }

    AppConstant(Charset charset) {
        this.charset = charset;
    }

    AppConstant(long value) {
        this.value = value;
    }

    private Locale locale;
    private Charset charset;
    private long value;

    public Locale getLocale() {
        return locale;
    }

    public Charset getCharset() {
        return charset;
    }

    public long getValue() {
        return value;
    }
}
