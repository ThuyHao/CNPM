package dao.client;

import context.DBContext;
import entity.Account;
import org.jdbi.v3.core.Jdbi;
import util.EnCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO {
	public AuthDAO() {
		super();
	}

	public static Account login(String username, String pass) {
		Jdbi me = DBContext.me();
		String passEncode = EnCode.toSHA1(pass);
		String queryLogin = "select id,accountName,password,fullName,address,email,phone,idRoleMember from accounts where accountName = ? and password  = ?";
		return (Account) me.withHandle(handle -> {
			return handle.createQuery(queryLogin)
					.bind(0, username).bind(1, passEncode).mapToBean(Account.class).findFirst().orElse(null);
		});
	}

	public static boolean checkAccountExist(String userName) { // ton tai la true
		Jdbi me = DBContext.me();
		try {
			return me.withHandle(handle -> handle
					.createQuery("SELECT EXISTS(SELECT id FROM accounts WHERE accountName = ? )")
					.bind(0, userName).mapTo(Boolean.class).one());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	public static int loginFail(String username) {
		Jdbi me = DBContext.me();
		try {
			int num = me.withHandle(handle -> handle.createQuery(
							"SELECT numberloginfail FROM accounts WHERE accountName = ?")
					.bind(0, username).mapTo(int.class).one());
			if (num >= 5) {
				return 5;
			}
			me.withHandle(handle -> handle.createUpdate(
							"UPDATE accounts SET numberloginfail = ? WHERE accountName = ?")
					.bind(0, num + 1).bind(1, username).execute());
			return num + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void resetlogin(String username) {
		Jdbi me = DBContext.me();
		try {
			me.withHandle(handle -> handle.createQuery(
							"SELECT numberloginfail FROM accounts WHERE accountName = ?")
					.bind(0, username).mapTo(int.class).one());
			me.withHandle(handle -> handle.createUpdate(
							"UPDATE accounts SET numberloginfail = ? WHERE accountName = ?")
					.bind(0, 0 ).bind(1, username).execute());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean checkEmailExist( String email) { // ton tai la true
		Jdbi me = DBContext.me();
		try {
			return me.withHandle(handle -> handle
					.createQuery("SELECT EXISTS(SELECT id FROM accounts WHERE  Email = ?)")
					.bind(0, email).mapTo(Boolean.class).one());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	public static boolean signUp(String userName, String password, String name, String email, String address, String NumberPhone) {
		String signInQuery = "INSERT INTO accounts (accountName, password, fullName, email, address,phone) VALUES (?, ?, ?, ?, ?, ?);";
		String passEncode = EnCode.toSHA1(password);
		Jdbi me = DBContext.me();
		me.withHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(signInQuery).bind(0, userName).bind(1, passEncode).bind(2, name)
						.bind(3, email).bind(4, address).bind(5, NumberPhone).execute();
				handle.commit();
				return true;
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
			return false;
		});
		return false;
	}

	public static void signUpGoogle(String id, String name, String email, String picture) {
		String ggCus = "insert into accounts (accountName, password, fullName, email, image, type, idOther) values (:accountName, :password, :fullName, :email, :image, :type, :idOther)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(ggCus).bind("accountName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("fullName", name).bind("email", EnCode.toSHA1(EnCode.toSHA1(email)))
						.bind("image", picture).bind("type", 3).bind("idOther", id)
						.execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
		});
	}

	private static Account getAccount(String id, String email, String loginBy) {
		Jdbi me = DBContext.me();
		String emailEncode = EnCode.toSHA1(EnCode.toSHA1(email));
		return (Account) me.withHandle(handle -> {
			return handle.createQuery(loginBy).bind(0, id).bind(1, emailEncode).mapToBean(Account.class).findFirst().orElse(null);
		});
	}

	public static Account loginGG(String id, String email) {
		String loginGGQuery = "select id, accountName, password, fullName, email, image, type, idOther from accounts where idOther= ? and email = ? and type = 3";
		return getAccount(id, email, loginGGQuery);
	}

	public static void signUpFacebook(String id, String name, String email, String pic) {
		String fbCus = "insert into accounts (accountName, password, fullName, email, image, type, idOther) values (:accountName, :password, :fullName, :email, :image, :type, :idOther)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(fbCus).bind("accountName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("fullName", name).bind("email", EnCode.toSHA1(EnCode.toSHA1(email)))
						.bind("image", pic).bind("type", 2).bind("idOther", id)
						.execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
		});
	}

	public static Account loginFacebook(String id, String email) {
		String loginFBQuery = "select id, accountName, password, fullName, email, image, type, idOther from accounts where idOther= ? and email = ? and type = 2";
		return getAccount(id, email, loginFBQuery);
	}

}