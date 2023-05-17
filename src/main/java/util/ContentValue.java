package util;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ContentValue {
    static ResourceBundle rb = ResourceBundle.getBundle("app_data", Locale.forLanguageTag("vi_VN"));
    public static String getValue(String key) throws IOException {
        return rb.getString(key);
    }

    public static void main(String[] args) {

    }
}
