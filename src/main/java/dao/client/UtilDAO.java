package dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import org.jdbi.v3.core.Jdbi;

import context.DBContext;

public class UtilDAO {
    public static List<ImageProduct> findListImageByIdProduct(int idProduct) {
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createQuery("SELECT id,idProduct,image FROM image_products where idProduct = ?").bind(0, idProduct).mapToBean(ImageProduct.class).list());
    }

    public static List<SizeColorProduct> findListSizeColorByIdProduct(int idProduct) {
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createQuery("SELECT id,idProduct,size,color FROM size_color_products where idProduct = ?").bind(0, idProduct).mapToBean(SizeColorProduct.class).list());
    }
    public static Product findProductById(int idProduct) {
        Jdbi me = DBContext.me();
        String query = "SELECT p.id,p.nameProduct,listPrice,description,nameSupplier,nameProducer,nameCategorie,pp.discount,pp.discountPrice,i.quantity,i.id_size_color FROM products p join product_prices pp on p.id = pp.idProduct join suppliers s on p.idSupplier = s.id join producers ps on ps.id = p.idProducer join categories c on p.idCategorie = c.id join inventorys i on i.idProduct = p.id where p.isActive ='1' and s.isActive= '1' and p.id = ?";
        return me.withHandle(handle -> handle.createQuery(query).bind(0, idProduct).map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
                        rs.getDouble("listPrice"), rs.getString("description"), new Supplier(rs.getString("nameSupplier")), new Producer(rs.getString("nameProducer")),new Category(rs.getString("nameCategorie")),
                        UtilDAO.findListImageByIdProduct(rs.getInt("id")), UtilDAO.findListSizeColorByIdProduct(rs.getInt("id")), rs.getInt("discount"), rs.getDouble("discountPrice"),new Inventory(rs.getInt("id"),rs.getInt("id_size_color"), rs.getInt("quantity"))))
                .findFirst().orElse(null));
    }

    public static Account findAccountById(int idAccount) {
        Jdbi me = DBContext.me();
        String query = "select id,accountName,password,fullName,address,email,phone,idRoleMember  from accounts where id =?";
        return me.withHandle(handle -> handle.createQuery(query).bind(0, idAccount).mapToBean(Account.class).findFirst().orElse(null));

    }

    public static void main(String[] args) {
        System.out.println(findProductById(1));
    }

}
