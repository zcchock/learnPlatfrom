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
    public void testQueryByKey() throws Exception {
        int atcId = 1;
        BlogAtc blogAtc = blogAtcMapper.queryAtc(atcId);
        System.out.println(blogAtc);
        System.out.println(blogAtc.getAtcId());
    }


}
