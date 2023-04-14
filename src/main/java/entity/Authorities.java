package entity;

public class Authorities { // quyen
	private int idAuthorities;
	private String nameAuthorities;
	public Authorities(int idAuthorities, String nameAuthorities) {
		super();
		this.idAuthorities = idAuthorities;
		this.nameAuthorities = nameAuthorities;
	}
	public Authorities() {
		super();
	}
	@Override
	public String toString() {
		return "Authorities [idAuthorities=" + idAuthorities + ", nameAuthorities=" + nameAuthorities + "]";
	}
	public int getIdAuthorities() {
		return idAuthorities;
	}
	public void setIdAuthorities(int idAuthorities) {
		this.idAuthorities = idAuthorities;
	}
	public String getNameAuthorities() {
		return nameAuthorities;
	}
	public void setNameAuthorities(String nameAuthorities) {
		this.nameAuthorities = nameAuthorities;
	}
	
}
