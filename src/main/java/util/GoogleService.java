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

    }
}
