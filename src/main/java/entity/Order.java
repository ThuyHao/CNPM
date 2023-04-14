package entity;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	private int id;
	private String createAt; // ngay dat
	private String deliveryDate; // ngay mua
	private String statusPay; // Trinh trang thanh toan
	private int idAccount;
	private Account account;
	private double sale; // giam gia
	private double totalPrice;
	private String status;
	private String address;
	private String note;
	private String updateAt; // ngay mua

	private List<OrderDetail> orderDetails;

	public Order() {
		super();
	}

	public Order(int id, String createAt, String deliveryDate, String statusPay, Account account, double sale, String status, String address, String node, String updateAt, List<OrderDetail> orderDetails) {
		this.id = id;
		this.createAt = createAt;
		this.deliveryDate = deliveryDate;
		this.statusPay = statusPay;
		this.account = account;
		this.sale = sale;
		this.status = status;
		this.address = address;
		this.note = node;
		this.updateAt = updateAt;
		this.orderDetails = orderDetails;
	}

	public Order(int id, String createAt, String statusPay,  String status,Account account,double totalPrice,int idAccount) {
		this.id = id;
		this.createAt = createAt;
		this.statusPay = statusPay;
		this.status = status;
		this.idAccount = idAccount;
		this.account = account;
		this.totalPrice = totalPrice;
	}
	public Order(int id, String createAt, String statusPay,  String status,Account account,double totalPrice) {
		this.id = id;
		this.createAt = createAt;
		this.statusPay = statusPay;
		this.status = status;
		this.account = account;
		this.totalPrice = totalPrice;
	}

	public Order(int id, String createAt, String deliveryDate, String statusPay, Account account, double sale, double totalPrice, String status, String address, String node, String updateAt, List<OrderDetail> orderDetails) {
		this.id = id;
		this.createAt = createAt;
		this.deliveryDate = deliveryDate;
		this.statusPay = statusPay;
		this.account = account;
		this.sale = sale;
		this.totalPrice = totalPrice;
		this.status = status;
		this.address = address;
		this.note = node;
		this.updateAt = updateAt;
		this.orderDetails = orderDetails;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public String getCreateAt() {
		return createAt;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getStatusPay() {
		return statusPay;
	}

	public Account getAccount() {
		return account;
	}

	public double getSale() {
		return sale;
	}

	public String getStatus() {
		return status;
	}

	public String getAddress() {
		return address;
	}

	public String getNote() {
		return note;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setStatusPay(String statusPay) {
		this.statusPay = statusPay;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", createAt='" + createAt + '\'' +
				", deliveryDate='" + deliveryDate + '\'' +
				", statusPay='" + statusPay + '\'' +
				", account=" + account +
				", sale=" + sale +
				", totalPrice=" + totalPrice +
				", status='" + status + '\'' +
				", address='" + address + '\'' +
				", node='" + note + '\'' +
				", updateAt='" + updateAt + '\'' +
				", orderDetails=" + orderDetails +
				'}';
	}
}
