package br.com.security.domain;

public class KeyAccess {

    public static String bookRead() {
        return getAuthority(Keys.BOOK, TokenAccess.READ);
    }

    public static String bookWrite() {
        return getAuthority(Keys.BOOK, TokenAccess.WRITE);
    }

    public static String stockRead() {
        return getAuthority(Keys.STOCK, TokenAccess.READ);
    }

    public static String stockWrite() {
        return getAuthority(Keys.STOCK, TokenAccess.WRITE);
    }

    public static String cambioRead() {
        return getAuthority(Keys.CAMBIO, TokenAccess.READ);
    }

    public static String cambioWrite() {
        return getAuthority(Keys.CAMBIO, TokenAccess.WRITE);
    }

    private static String getAuthority(Keys key, TokenAccess tokenAccess) {
        return "service." + key.getValue() + "." + tokenAccess.getValue();
    }
}