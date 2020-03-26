package com.drivetuningsh.constant;

import lombok.Getter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Getter
public enum AppConstant {

    DEFAULT_LOCALE(Locale.ENGLISH),
    DEFAULT_ENCODING(StandardCharsets.UTF_8);

    private Locale locale;
    private Charset charset;

    AppConstant(Locale locale) {
        this.locale = locale;
    }

    AppConstant(Charset charset) {
        this.charset = charset;
    }
}
