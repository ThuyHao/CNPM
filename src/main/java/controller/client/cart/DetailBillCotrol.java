package controller.client.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.client.OrderDAO;
import entity.Order;
import entity.OrderDetail;

@WebServlet("/DetailBill")
public class DetailBillCotrol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Order order = OrderDAO.getOrderByBid(id);
		List<OrderDetail> orderDetails = OrderDAO.getOrderDetailByBid(id);
		request.setAttribute("bill", order);
		request.setAttribute("billProducts", orderDetails);
		System.out.println(order);
		System.out.println(orderDetails);
		request.getRequestDispatcher("/client/BillDetail.jsp").forward(request, response);
	}

}
