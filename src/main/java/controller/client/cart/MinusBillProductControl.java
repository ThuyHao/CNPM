package controller.client.cart;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.OrderDetail;

@WebServlet("/MinusBillProductControl")
public class MinusBillProductControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");
        if (obj != null) {
            Map<String, List<OrderDetail>> map = (Map<String, List<OrderDetail>>) obj;
            for (Map.Entry<String, List<OrderDetail>> entry : map.entrySet()) {
                List<OrderDetail> orderDetails = entry.getValue();
                for (OrderDetail o : orderDetails) {
                    if (String.valueOf(o.getProduct().getId()).equalsIgnoreCase(key)) {
                        if (o.getProductSize() == null && size == null || o.getProductSize() != null && o.getProductSize().equals(size)) {
                            if (o.getProductColor() == null && color == null || o.getProductColor() != null && o.getProductColor().equals(color)) {
                               if(o.getQuantity()>1){
                                   o.setQuantity(o.getQuantity()-1);
                               }
                            }
                        }
                    }
                }
            }
            session.setAttribute("cart", map); // update lại vào session
        }

        request.getRequestDispatcher("CartControl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
