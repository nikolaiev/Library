package com.controller.filters.i18n;

import java.util.Locale;

/**
 * Created by vlad on 19.04.17.
 */
public enum ProgramLocale {
    EN(new Locale("en", "EN")),
    RU(new Locale("ru", "RU")),
    UA(new Locale("ua", "UA"));

    public static final ProgramLocale DEFAULT_LOCALE = EN;

    private Locale locale;

    ProgramLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public static ProgramLocale getOrDefault(String localeString) {
        try{
            return ProgramLocale.valueOf(localeString);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return DEFAULT_LOCALE;
        }
    }
}
