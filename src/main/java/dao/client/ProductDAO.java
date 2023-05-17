package dao.client;

import context.DBContext;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static int getTotalProduct(int cid) {
        String query = "";
        switch (cid) {
            case 0: //tất cả sản phẩm
                query = "select count(p.nameProduct) from products p join product_prices pp on p.id = pp.idProduct";
                break;
            case 1: //góc bé trai
                query = "select count(p.nameProduct) from products p join categories C on p.idCategorie = c.id where c.id in(1,2,5,7,9,13)";
                break;
            case 2: //góc bé gái
                query = "select count(p.nameProduct) from products p join categories C on p.idCategorie = c.id  where idCategorie in(3,4,6,8,10,11,13)";
                break;
            case 3: //phụ kiện
                query = "select count(p.nameProduct) from products p join categories C on p.idCategorie = c.id  where idCategorie in(14,15)";
                break;
            case 4: //khuyến mãi
                query = "select count(p.nameProduct) from products p join categories C on p.idCategorie = c.id join product_prices pp on pp.idProduct = p.id where pp.listPrice > pp.discountPrice";
                break;
            case 5: //đồ chơi
                query = "select count(p.nameProduct) from products p join categories C on p.idCategorie = c.id  where idCategorie in(12)";
                break;
            default:
                break;
        }
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    public static List<Product> pagingProduct(int index, int cid, String sortName, String typeName) {
        List<Product> list = new ArrayList<>();
        String query = "";
        switch (cid) {
            case 0:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount  from products p join product_prices pp on p.id = pp.idProduct order by " + sortName + " " + typeName
                        + "  limit ?,12";
                break;
            case 1:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount from products p join categories c on p.idCategorie = c.id join product_prices pp on p.id = pp.idProduct where idCategorie in(1,2,5,7,9,13) order by "
                        + sortName + " " + typeName + "  limit ?,12";
                break;
            case 2:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount from products p join categories c on p.idCategorie = c.id join product_prices pp on p.id = pp.idProduct where idCategorie in(3,4,6,8,10,11,13) order by "
                        + sortName + " " + typeName + "  limit ?,12";
                break;
            case 3:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount from products p join categories c on p.idCategorie = c.id join product_prices pp on p.id = pp.idProduct where idCategorie in(14,15) order by "
                        + sortName + " " + typeName + "  limit ?,12";
                break;
            case 4:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount from products p join categories c on p.idCategorie = c.id join product_prices pp on p.id = pp.idProduct where pp.listPrice > pp.discountPrice order by "
                        + sortName + " " + typeName + "  limit ?,12";
                break;
            case 5:
                query = "select p.id, p.nameProduct, pp.listPrice, pp.discountPrice, pp.discount from products p join categories c on p.idCategorie = c.id join product_prices pp on p.id = pp.idProduct where idCategorie in(12) order by "
                        + sortName + " " + typeName + "  limit ?,12";
                break;
            default:
                break;
        }

        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, (index - 1) * 12);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(rs.getInt("id"), rs.getString("nameProduct"),
                            rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
                            rs.getInt("discount"), rs.getDouble("discountPrice")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

        System.out.println(pagingProduct(1, 0, "idProduct", "asc"));
    }
}
