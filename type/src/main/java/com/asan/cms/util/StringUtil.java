package com.asan.cms.util;

public class StringUtil {
    public static String camelToSnake(final String camelStr) {
        String ret = camelStr.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
        return ret.toLowerCase();
    }

    /*
    public static String camelToSnake(String camel, boolean upper) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : camel.toCharArray()) {
            char nc = upper ? Character.toUpperCase(c) : Character.toLowerCase(c);
            if (Character.isUpperCase(c)) {
                stringBuilder.append('_').append(nc);
            } else {
                stringBuilder.append(nc);
            }
        }
        return stringBuilder.toString();
    }

    public static String camelToSnake(String value) {
        final char[] name = value.toCharArray();
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < name.length; i++) {
            if (Character.isUpperCase(name[i]) || name[i] == '.' || name[i] == '$') {
                if (i != 0 && name[i - 1] != '.' && name[i - 1] != '$') {
                    builder.append('_');
                }
                if (name[i] != '.' && name[i] != '$') {
                    builder.append(Character.toLowerCase(name[i]));
                }
            } else {
                builder.append(name[i]);
            }
        }

        return builder.toString();
    }

    public static String camelToSnakeCase(final String camelCase) {
        // check for null
        if (camelCase == null || camelCase.isEmpty()) {
            return "";
        }

        // simple regex
        return camelCase.replaceAll("\\B([A-Z])", "_$1").toLowerCase();
    }

    public static String camelToSnakeCase(String camelcase) {
        return camelcase.replaceAll("([A-Z][a-z])", "_$1");
    }

    public static String camelToSnakeCase(String string) {
        switch (string) {
            case "IO":
                return "io";
            case "OS":
                return "os";
            case "MD5":
                return "md5";
            default:
                StringBuilder result = new StringBuilder(string.length());
                for (char c : string.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        if (result.length() > 0) {
                            result.append('_');
                        }
                        result.append(Character.toLowerCase(c));
                    } else {
                        result.append(c);
                    }
                }
                return result.toString();
        }
    }

    public static String camelToSneakCase(String name) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (cur < name.length()) {
            char c = name.charAt(cur);

            if (!Character.isUpperCase(c)) {
                sb.append(c);
                cur++;
                continue;
            }

            if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '_') {
                sb.append('_');
            }

            while (Character.isUpperCase(c)) {
                sb.append(c);
                cur++;
                if (cur >= name.length()) {
                    break;
                }
                c = name.charAt(cur);
            }
        }

        if (name.charAt(0) != '_' && sb.charAt(0) == '_') {
            sb.deleteCharAt(0);
        }

        return sb.toString().toLowerCase();
    }

    public static String camelToSnake(String camel, boolean upper) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : camel.toCharArray()) {
            char nc = upper ? Character.toUpperCase(c) : Character.toLowerCase(c);
            if (Character.isUpperCase(c)) {
                stringBuilder.append('_').append(nc);
            } else {
                stringBuilder.append(nc);
            }
        }
        return stringBuilder.toString();
    }
     */
}
