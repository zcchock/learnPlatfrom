import com.zc.api.DataRequest;
import com.zc.api.DataResponse;
import com.zc.mapper.UserMapper;
import com.zc.entity.User;
import com.zc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chock on 2017/4/3.
 */
public class UserDaoTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void testQueryById() throws Exception {
        int userId = 1;
        DataRequest dataRequest = new DataRequest();
        dataRequest.setData(1);
        DataResponse dataResponse = userService.getUserById(dataRequest);
        System.out.println(dataResponse.getData());
    }

    /*@Test
    public void testQueryAll() throws Exception {
        List<User> users = userService.queryAll(0, 4);
        for (User user : users) {
            System.out.println(user);
        }
    }*/

}
