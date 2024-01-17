package me.esmee.luxehosting.bot;

public class Prefix {

    private static String prefix = "";

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Prefix.prefix = prefix;
    }

}
