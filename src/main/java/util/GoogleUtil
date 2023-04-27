package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class GoogleUtil {

    // Hàm getToken sử dụng để lấy token từ mã đã nhận được sau khi xác thực
    public static String getToken(final String code) throws IOException {
        // Tạo link để gửi yêu cầu lấy token
        String link = String.format(GoogleProperties.GOOGLE_LINK_GET_TOKEN(),
                GoogleProperties.GOOGLE_CLIENT_ID(),
                GoogleProperties.GOOGLE_CLIENT_SECRET(),
                GoogleProperties.GOOGLE_REDIRECT_URL(),
                code);

        // Gửi yêu cầu lấy token và lấy kết quả trả về dưới dạng chuỗi JSON
        String response_json_text = Request.Get(link).execute().returnContent().asString();

        // Chuyển kết quả trả về từ dạng chuỗi JSON sang đối tượng JsonObject sử dụng thư viện Gson
        JsonObject jobj = new Gson().fromJson(response_json_text, JsonObject.class);

        // Lấy giá trị của access_token trong JsonObject và loại bỏ ký tự ""
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;
    }

    // Hàm getUserInfor sử dụng để lấy thông tin người dùng từ token
    public static User getUserInfor(String accessToken) {

        // Tạo đối tượng GoogleClient sử dụng token và app-secret để truy vấn dữ liệu
        GoogleClient ggClient = new DefaultGoogleClient(accessToken,
                GoogleProperties.GOOGLE_CLIENT_SECRET(),
                Version.LATEST);

        return ggClient.fetchObject("me", User.class, Parameter.with("fields", "id, name, email, gender"));

        /*
        nếu không có đoạn code này
        Parameter.with("fields", "id, name, email")
        ==> chỉ lấy được thông tin cơ bản của người dùng như id,name
         */
}
