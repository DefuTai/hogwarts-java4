package com.hogwarts.wxwork;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.workwechat.TokenHelper;
import com.hogwarts.workwechat.UserObject;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

/**
 * 描述：
 *
 * @Author defu
 * @Date 2020-12-15 23:55
 * @Version 1.0
 **/
public class BaseTest {

    /**
     * 获取access_token是调用企业微信API接口的第一步，
     * 相当于创建了一个登录凭证，其它的业务API接口，
     * 都需要依赖于access_token来鉴权调用者身份。
     * 因此开发者，在使用业务接口前，
     * 要明确access_token的颁发来源，使用正确的access_token。
     */
    public static String ACCESS_TOKEN;
    public static final String INITIAL_DEPARTMENT_ID = "1";

    /**
     * 企业ID，获取方式参考：
     * <a href="https://work.weixin.qq.com/api/doc/90000/90135/90665#corpid">术语说明-corpid</a>
     */
    private static final String CORP_ID = "ww98102567c218bd46";
    /**
     * 应用的凭证密钥，获取方式参考：
     * <a href="https://work.weixin.qq.com/api/doc/90000/90135/90665#secret">术语说明-secret</a>
     */
    private static final String CORP_SECRET = "RFgsrXD-C5ibl4-YUTPvtuwXsQJ5bQjwH1DJxeRmCFk";

    @BeforeAll
    public static void beforeAll() {
        ACCESS_TOKEN = TokenHelper.getToken(CORP_ID, CORP_SECRET).path("access_token");
    }

    /**
     * 清理部门列表多余部门信息（id为1的根部门除外）
     */
    public void clearDepartmentTask() {
        List<Integer> list = DepartmentObject.listDepartment(ACCESS_TOKEN, "").path("department.id");
        for (int departmentId : list) {
            if (departmentId != 1) {
                DepartmentObject.deleteDepartment(ACCESS_TOKEN, String.valueOf(departmentId));
            }
        }
    }

    /**
     * 清理"DF测试工厂"部门下的所有测试成员（userId为TaiDeFu的成员除外）
     */
    public void clearUserTask() {
        List<String> userIdList = UserObject.simpleList(ACCESS_TOKEN, INITIAL_DEPARTMENT_ID, "0").path("userlist.userid");
        for (String userId : userIdList) {
            if (!"TaiDeFu".equals(userId)) {
                UserObject.deleteUser(ACCESS_TOKEN, userId);
            }
        }
    }

}
