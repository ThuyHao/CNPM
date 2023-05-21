package controller.client.products;

import dao.client.AccessDAO;
import entity.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset = UTF-8");
        try{
            //8.3 : Hệ thống lấy dữ liệu từ khóa từ ô nhập từ khóa.
            String txtSearch = request.getParameter("search");
            //8.4 : Hệ thống thực hiện kiểm tra từ khóa.
            if (txtSearch.matches(".*[!@#$%^&*()\\-=_+\\[\\]{}|;':\",./<>?].*")) {
                //8.11 : Hệ thống chuyển hướng đến trang lỗi tìm kiếm.
                request.getRequestDispatcher("/client/errorSearch.jsp").forward(request, response);return;
            } else {
                //8.5 : Nếu từ khóa hợp lệ thì hệ thống sẽ gọi phương thức tìm kiếm sản phẩm.
                List<Product> list = AccessDAO.searchByName(txtSearch);
                //8.5.4	: Hệ thống trả về danh sách sản phẩm.
                request.setAttribute("listProduct", list);
                //8.6 : Hệ thống chuyển hướng yêu cầu sang controller ShowProduct kèm request và reponse để xử lý tiếp dữ liệu.
                request.getRequestDispatcher("ShowProduct").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
