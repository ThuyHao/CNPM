import jakarta.servlet.annotation.WebServlet;
import util.GoogleService;
import util.GoogleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoogleLoginServlet", value = "/GoogleLoginServlet")
public class LoginGoogle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Tham số này chứa mã lỗi nếu việc đăng nhập bị hủy bởi người dùng.*/
        String error = request.getParameter("error");

        /* Tham số này chứa mã xác thực dùng để lấy thông tin người dùng Google nếu việc đăng nhập thành công.*/
        String code = request.getParameter("code");

        // khi người dùng hủy đăng nhập bằng Google
        if (error != null && error.equals("access_denied")) {
            response.sendRedirect(request.getContextPath() + "/shop/login");
        }
        // khi người dùng đồng ý đăng nhập bằng tài khoản GOogle trên trang xác thực tài khoản của Google
        if (code != null) {
            String accessToken = GoogleUtil.getToken(code);
            User userGoogle = GoogleUtil.getUserInfor(accessToken);

            String webBrowser = request.getHeader("User-Agent");

            UserCustomer userCustomer = new UserCustomer();
            userCustomer.setId_gg(userGoogle.getId());
            userCustomer.setName(userGoogle.getName());
            userCustomer.setSex(userGoogle.getGender());
            userCustomer.setEmail(userGoogle.getEmail());

            // kiểm tra id_user_gg của người dùng có tồn tại trong hệ thống hay chưa ?
            int id_user = GoogleService.checkExistAccReturnId(JDBiConnector.me(), userGoogle.getId());
            if (id_user != -1) {
                // đã tồn tại trong hệ thống
                userCustomer.setId(id_user + "");
                Log logSignIn = new Log(userCustomer.getId(), "", "đăng nhập hệ thống bằng tài khoản Google", "", webBrowser, "");
                logSignIn.insert(JDBiConnector.me()); // ghi lịch sử đăng nhập vào bảng Log

                request.getSession().setAttribute("auth_customer", userCustomer);
                response.sendRedirect(request.getContextPath() + "/HomeServlet");

            } else if (id_user == -1) {
                // chưa tồn tại trong hệ thống

                Log logCreateAcc = new Log("", "", "", "", webBrowser, "");
                int new_id_user = GoogleService.createAccProReturnId(JDBiConnector.me(), userCustomer, logCreateAcc);

                if (new_id_user != -1) {
                    // tạo tài khoản thành công đồng thời đăng nhập vào hệ thống
                    userCustomer.setId(new_id_user + "");
                    Log logSignIn = new Log(userCustomer.getId(), "", "đăng nhập hệ thống bằng tài khoản Google", "", webBrowser, "");
                    logSignIn.insert(JDBiConnector.me());

                    request.getSession().setAttribute("auth_customer", userCustomer);
                    response.sendRedirect(request.getContextPath() + "/HomeServlet");

                } else {
                    // tạo tài khoản không thành công <=> không thể đăng nhập vào hệ thống

                    response.sendRedirect(request.getContextPath() + "/LoginServlet");

                }

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}