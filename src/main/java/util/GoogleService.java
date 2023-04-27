package util;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

public class GoogleService {

    /*
        - Hàm này dùng để kiểm tra sự tồn tại của id_user_gg trong Db
        - Nếu
            + tồn tại : => sẽ trả về id_user của tài khoản Google đó
            + không tồn tại hoặc xảy ra ngoại lệ : => sẽ trả về giá trị là -1
     */
    public static int checkExistAccReturnId(Jdbi jdbi, String id_user_gg) {
        int id_user_customer = -1;
        try {
            id_user_customer = jdbi.withHandle(handle -> {
                Query query = handle.createQuery("SELECT id FROM user WHERE id_gg = :id_gg ");
                query.bind("id_gg", id_user_gg);
                return query.mapTo(Integer.class).one();//--> Phương thức one() sẽ trả về giá trị của cột đầu tiên nếu có bản ghi được trả về từ câu truy vấn, ngược lại sẽ ném ra một ngoại lệ.
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    return id_user_customer;
    }
    /*
        - Hàm này dùng để tạo tài khoản mới trong Db dựa vào thông tin trả về từ Google
        => kết quả trả về là id_user của tài khoản đó trong Db
        - nếu xảy ra ngoại lệ thì giá trị trả về sẽ là -1
        - ghi lại Log tạo tài khoản
     */
    public static int createAccProReturnId(Jdbi jdbi, UserCustomer infor, Log logCreateAcc) {
        int id_user = -1;
        try {
            id_user = jdbi.withHandle(handle -> {
                Update update = handle.createUpdate("INSERT INTO user(name,email,password, id_gg) VALUES (:name,:email,:password, :id_gg)");
                update.bind("name", infor.getName()).bind("email", infor.getEmail()).bind("password", infor.getId_gg() + "123@").bind("id_gg", infor.getId_fb());

                int row_insert = update.execute(); // trả về số dòng được insert vào bảng account_customers

                if (row_insert == 1) {
                    Query query = handle.createQuery("SELECT LAST_INSERT_ID()"); // Khi chèn dữ liệu vào bảng trong MySQL bằng câu lệnh INSERT, một giá trị ID mới thường được tạo ra tự động cho mỗi bản ghi. Giá trị này thường được tạo ra bởi một trường ID tự động trong bảng hoặc một cột có thuộc tính AUTO_INCREMENT. Nếu bạn muốn truy vấn giá trị ID vừa được tạo ra để sử dụng cho các mục đích khác, bạn có thể sử dụng câu lệnh SELECT LAST_INSERT_ID().
                    return query.mapTo(Integer.class).one(); // trả về id_user ở dòng mới được thêm vào
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            logCreateAcc.setUser_id(id_user + "");
            logCreateAcc.setContent("Tạo tài khoản bằng thông tin tài khoản Gg");
            logCreateAcc.insert(jdbi);
        }
    return id_user;
    }
}
