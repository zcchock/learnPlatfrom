import com.zc.mapper.BlogAtcMapper;
import com.zc.entity.BlogAtc;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chock on 2017/4/3.
 */
public class BlogAtcTest extends BaseTest {

    @Autowired
    private BlogAtcMapper blogAtcMapper;

    @Test
    public void testInsert() throws Exception {
        int atcId = 1;
        int flag = blogAtcMapper.insertBlogAtc(atcId);
        System.out.println("insert=" + flag);
    }

    @Test
    public void testQueryByKey() throws Exception {
        int atcId = 1;
        BlogAtc blogAtc = blogAtcMapper.queryByKey(atcId);
        System.out.println(blogAtc);
        System.out.println(blogAtc.getAtcId());
    }


}
