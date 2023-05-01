package controller.client.products;

import dao.client.*;
import entity.Product;
import util.ContentValue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@WebServlet(name = "ShowProduct", value = "/ShowProduct")
public class ShowProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try{
            String sort;
            sort = request.getParameter("sort");
            if(sort == null){
                sort = "id-asc";
            }

            int cid = 0; //id loại sản phẩm
            if(request.getParameter("cid") != null){
                cid = Integer.parseInt(request.getParameter("cid"));
            }

            String indexPage = request.getParameter("index");
            if(indexPage == null){
                indexPage = "1";
            }

            int index = Integer.parseInt(indexPage);
            String search = request.getParameter("search");
            StringTokenizer cutString = new StringTokenizer(sort, "-");
            String sortName = cutString.nextToken();
            String sortType = cutString.nextToken();

            int count;
            if(search != null && !search.isEmpty()){
                count = AccessDAO.getTotalProductSearch(search);
                int endPage = count / Integer.parseInt(ContentValue.getValue("sumProductInOnePage"));
                if(count % Integer.parseInt(ContentValue.getValue("sumProductInOnePage")) != 0){
                    endPage++;
                }
                List<Product> list = AccessDAO.pagingProductSearch(index, search, sortName, sortType);
                //gửi reponse từ servlet qua jsp
                request.setAttribute("listProduct", list);
                request.setAttribute("endPage", endPage);
            }else {

                count = ProductDAO.getTotalProduct(cid);
                int endPage = count / Integer.parseInt(ContentValue.getValue("sumProductInOnePage"));
                if(count % Integer.parseInt(ContentValue.getValue("sumProductInOnePage")) != 0){
                    endPage++;
                }
                List<Product> list = ProductDAO.pagingProduct(index, cid, sortName, sortType);
                request.setAttribute("listProduct", list);
                request.setAttribute("endPage", endPage);
            }
            request.setAttribute("sort", sort);
            request.setAttribute("tag", index);
            request.setAttribute("search", search);
            request.setAttribute("cid", cid);
        }catch (Exception e){
            e.printStackTrace();
        }
        //8.7 : Hệ thống chuyển chuyển dữ liệu đến trang danh sách sản phẩm.
        request.getRequestDispatcher("/client/ListProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
