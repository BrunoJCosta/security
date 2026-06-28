package br.com.security.domain;

public class KeyAccess {

    public static String bookRead() {
        return montagem(Keys.BOOK, TokenAccess.READ);
    }

    public static String bookWrite() {
        return montagem(Keys.BOOK, TokenAccess.WRITE);
    }

    public static String stockRead() {
        return montagem(Keys.STOCK, TokenAccess.READ);
    }

    public static String stockWrite() {
        return montagem(Keys.STOCK, TokenAccess.WRITE);
    }

    public static String cambioRead() {
        return montagem(Keys.CAMBIO, TokenAccess.READ);
    }

    public static String cambioWrite() {
        return montagem(Keys.CAMBIO, TokenAccess.WRITE);
    }

    private static String montagem(Keys key, TokenAccess access) {
        return key + "." + access;
    }
}
