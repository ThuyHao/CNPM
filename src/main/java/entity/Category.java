package entity;

public class Category {
	private int idCategory;
	private String nameCategory;
	public Category(int id, String name) {
		super();
		this.idCategory = id;
		this.nameCategory = name;
	}

	public Category(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [id=" + idCategory + ", name=" + nameCategory + "]";
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
}
