import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gic.task.allocation.common.GlobalInfoParams;
import com.gic.task.allocation.qo.InitTaskQo;
import com.gic.task.allocation.qo.TaskCallbackQo;
import com.gic.task.allocation.service.TaskAllocationSdkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2017/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml","classpath:task-allocation-servlet.xml"})
@WebAppConfiguration
public class taskTest {

    @Autowired
    private TaskAllocationSdkService taskAllocationSdkService;

    @Test
    public void test(){
        InitTaskQo initTaskQo = new InitTaskQo();
        initTaskQo.setOperationUserId("clerkId");
        initTaskQo.setEnterpriseId("enterpriseId");
        initTaskQo.setTaskMqKey("testMq");
        initTaskQo.setTaskType(0);
        initTaskQo.setTaskSignKey("allocationSignKey");
        taskAllocationSdkService.initTask(JSONObject.toJSONString(initTaskQo));
    }

    @Test
    public void testCallback(){
        TaskCallbackQo taskCallbackQo=new TaskCallbackQo();
        taskCallbackQo.setCallbackType(GlobalInfoParams.CALLNACK_TYPE_INIT);
        taskCallbackQo.setIsSuccess(1);
        taskCallbackQo.setTaskAllocationId("c6eb723812484703875960e3ddb915c5");
        taskCallbackQo.setTaskTotal(10);
        taskCallbackQo.setTaskFailNum(0);
        taskAllocationSdkService.resultCallback(JSONObject.toJSONString(taskCallbackQo));
    }
}
