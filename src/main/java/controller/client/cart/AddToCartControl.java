package controller.client.cart;
import dao.client.UtilDAO;
import entity.Account;
import entity.OrderDetail;
import entity.Product;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AddToCartControl")
public class AddToCartControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String pId = request.getParameter("pid");
        String sizeColor = request.getParameter("variantId");
        StringTokenizer SizeColorToken = new StringTokenizer(sizeColor, "/");
        String size = SizeColorToken.nextToken();
        String color = SizeColorToken.nextToken();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/client/Login.jsp?pid=" + pId);
        } else {
            int quantity = 1;
            Product product = UtilDAO.findProductById(Integer.parseInt(pId));
            Object obj = session.getAttribute("cart");
            String getQuantity = request.getParameter("quantity");
            if (getQuantity != null) {
                quantity = Integer.parseInt(getQuantity);
            }
            if (obj == null) {
                List<OrderDetail> orderDetails = new ArrayList<>();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(product.getDiscountPrice());
                orderDetail.setProductSize(size);
                orderDetail.setProductColor(color);
                orderDetails.add(orderDetail);
                Map<String, List<OrderDetail>> map = new HashMap<>();
                map.put(pId, orderDetails);
                session.setAttribute("cart", map);
            } else {
                Map<String, List<OrderDetail>> map = (Map<String, List<OrderDetail>>) obj;
                if (map.containsKey(pId)) {
                    boolean found = false;
                    for (OrderDetail o : map.get(pId)) {
                        if (o.getProductSize() == null && size == null || o.getProductSize() != null && o.getProductSize().equals(size)) {
                            if (o.getProductColor() == null && color == null || o.getProductColor() != null && o.getProductColor().equals(color)) {
                                o.setQuantity(o.getQuantity() + quantity);
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setProduct(product);
                        orderDetail.setQuantity(quantity);
                        orderDetail.setPrice(product.getDiscountPrice());
                        orderDetail.setProductSize(size);
                        orderDetail.setProductColor(color);
                        map.get(pId).add(orderDetail);
                    }
                } else {
                    List<OrderDetail> orderDetails = new ArrayList<>();
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(quantity);
                    orderDetail.setPrice(product.getDiscountPrice());
                    orderDetail.setProductSize(size);
                    orderDetail.setProductColor(color);
                    orderDetails.add(orderDetail);
                    map.put(pId, orderDetails);
                }
                session.setAttribute("cart", map);// luu tam vao session
            }
            request.getRequestDispatcher("CartControl").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
