package entity;

import java.sql.Date;
import java.util.List;

public class Discount {
	private int idDiscount;
	private String nameDiscount;
	private String description;
	private String codeDiscount;
	private double percentage;
	private int minimum_product_quantity;
	private Date start_time;
	private Date end_time;
	private List<Product> products;
	private String Status;

	public Discount(int idDiscount, String nameDiscount, String description, String codeDiscount, double percentage,
			int minimum_product_quantity, Date start_time, Date end_time, List<Product> products, String status) {
		super();
		this.idDiscount = idDiscount;
		this.nameDiscount = nameDiscount;
		this.description = description;
		this.codeDiscount = codeDiscount;
		this.percentage = percentage;
		this.minimum_product_quantity = minimum_product_quantity;
		this.start_time = start_time;
		this.end_time = end_time;
		this.products = products;
		Status = status;
	}

	public Discount() {
		super();
	}

	public int getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(int idDiscount) {
		this.idDiscount = idDiscount;
	}

	public String getNameDiscount() {
		return nameDiscount;
	}

	public void setNameDiscount(String nameDiscount) {
		this.nameDiscount = nameDiscount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeDiscount() {
		return codeDiscount;
	}

	public void setCodeDiscount(String codeDiscount) {
		this.codeDiscount = codeDiscount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getMinimum_product_quantity() {
		return minimum_product_quantity;
	}

	public void setMinimum_product_quantity(int minimum_product_quantity) {
		this.minimum_product_quantity = minimum_product_quantity;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Discount [idDiscount=" + idDiscount + ", nameDiscount=" + nameDiscount + ", description=" + description
				+ ", codeDiscount=" + codeDiscount + ", percentage=" + percentage + ", minimum_product_quantity="
				+ minimum_product_quantity + ", start_time=" + start_time + ", end_time=" + end_time + ", products="
				+ products + ", Status=" + Status + "]";
	}

}
