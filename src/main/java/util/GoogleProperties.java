package util;

import java.io.IOException;
import java.util.Properties;

public class GoogleProperties {
    private static final String PROP_PATH = "google.properties";
    private static final Properties PROP = new Properties();

    static {
        try {
            PROP.load(GoogleProperties.class.getClassLoader().getResourceAsStream(PROP_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
            Đây là một khối static initializer được sử dụng để khởi tạo các giá trị static trong class GoogleProperties.

            Trong khối này, class sử dụng đối tượng Properties để đọc tệp tin cấu hình "google.properties" và lưu các giá trị vào trong biến static PROP.

            Để đọc tệp tin cấu hình, class sử dụng phương thức getResourceAsStream() của đối tượng ClassLoader của class GoogleProperties.

            Phương thức này trả về một đối tượng InputStream để đọc dữ liệu từ tệp tin cấu hình.

            Nếu có lỗi khi đọc tệp tin cấu hình, class sẽ ném ra một RuntimeException, được xử lý bởi code gọi hàm khởi tạo của class.

            Khối static initializer này sẽ được thực thi chỉ một lần, khi class GoogleProperties được nạp vào bộ nhớ.
         */
    }
    public static String GOOGLE_CLIENT_ID() {
        return PROP.getProperty("clientID");
    }

    public static String GOOGLE_CLIENT_SECRET() {
        return PROP.getProperty("clientSecret");
    }

    public static String GOOGLE_REDIRECT_URL() {
        return PROP.getProperty("redirectUrl");
    }

    public static String GOOGLE_LINK_GET_TOKEN() {
        return PROP.getProperty("linkGetToken");
    }
}
