package dao.client;

import context.DBContext;
import entity.*;
import org.jdbi.v3.core.Handle;

import java.util.*;

public class AccessDAO {
    public static List<Product> searchByName(String keyWord) {
        //8.5.1	: Hệ thống kết nối đến cơ sở dữ liệu.
        try (Handle handle = DBContext.me().open()) {
            //8.5.2	: Hệ thống thực hiện truy vấn cơ sở dữ liệu để tìm kiếm sản phẩm.
            String query = "SELECT p.id, p.nameProduct, listPrice, description, nameSupplier, nameProducer, nameCategorie, pp.discount, pp.discountPrice, i.quantity " + "FROM products p " + "JOIN product_prices pp ON p.id = pp.idProduct " + "JOIN suppliers s ON p.idSupplier = s.id " + "JOIN producers ps ON ps.id = p.idProducer " + "JOIN categories c ON p.idCategorie = c.id " + "JOIN inventorys i ON i.idProduct = p.id " + "WHERE p.isActive = '1' AND s.isActive = '1' AND p.nameProduct LIKE ?";
            //8.5.3	: Cơ sở dữ liệu trả về danh sách sản phẩm.
            return handle.createQuery(query).bind(0,"%" + keyWord + "%").map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
                            rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
                            rs.getInt("discount"),rs.getDouble("discountPrice")))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static int getTotalProductSearch(String keyWord) {
        try (Handle handle = DBContext.me().open()) {
            String query = "SELECT COUNT(nameProduct) FROM products WHERE nameProduct LIKE ?";
            return handle.createQuery(query).bind(0, "%" + keyWord + "%").mapTo(Integer.class).one();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<Product> pagingProductSearch(int index, String keyWord, String sortName, String typeName) {
        String query = "select p.id, p.nameProduct, pp.listPrice,pp.discount, pp.discountPrice from products p join product_prices pp on p.id = pp.idProduct where  p.nameProduct like ? order by " + sortName + " " + typeName + "  limit ?,12";
        try (Handle handle = DBContext.me().open()) {
            return handle.createQuery(query).bind(0,"%" + keyWord + "%").bind(1, (index-1)*12).map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
                            rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
                            rs.getInt("discount"),rs.getDouble("discountPrice")))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
