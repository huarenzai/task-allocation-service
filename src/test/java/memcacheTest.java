import com.gic.task.allocation.util.MemCachedUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml","classpath:task-allocation-servlet.xml"})
public class memcacheTest {

    @Test
    public void testMemcache(){
        MemCachedUtil.setValue("a","test",1000);
        Object a = MemCachedUtil.getValue("a");
        System.out.println(a);
    }
}
