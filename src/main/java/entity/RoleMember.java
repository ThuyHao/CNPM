package entity;

public class RoleMember {
	private int idRoleMember;
	private String nameRole;
	private String Endow; // uu dai
	public RoleMember(int idRoleMember, String nameRole, String endow) {
		super();
		this.idRoleMember = idRoleMember;
		this.nameRole = nameRole;
		Endow = endow;
	}
	@Override
	public String toString() {
		return "RoleMember [idRoleMember=" + idRoleMember + ", nameRole=" + nameRole + ", Endow=" + Endow + "]";
	}
	public int getIdRoleMember() {
		return idRoleMember;
	}
	public void setIdRoleMember(int idRoleMember) {
		this.idRoleMember = idRoleMember;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public String getEndow() {
		return Endow;
	}
	public void setEndow(String endow) {
		Endow = endow;
	}
	
}
