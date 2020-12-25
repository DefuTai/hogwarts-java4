package com.hogwarts.workwechat;

import com.hogwarts.dto.UserDTO;
import com.hogwarts.utils.HttpRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：成员管理
 *
 * @Author defu
 * @Time 2020/12/23 1:22 下午
 * @Version 1.0
 **/
public class UserObject {

    /**
     * 创建成员
     *
     * @param accessToken      调用接口凭证。获取方法查看“<a href="https://work.weixin.qq.com/api/doc/10013#%E7%AC%AC%E4%B8%89%E6%AD%A5%EF%BC%9A%E8%8E%B7%E5%8F%96access_token">获取access_token</a>”
     * @param userId           成员UserID
     * @param name             成员名称。长度为1~64个utf8字符
     * @param alias            成员别名。长度1~32个utf8字符
     * @param mobile           手机号码。企业内必须唯一，mobile/email二者不能同时为空
     * @param department       成员所属部门id列表,不超过100个
     * @param order            部门内的排序值，默认为0，成员次序以创建时间从小到大排列。个数必须和参数department的个数一致，数值越大排序越前面。有效的值范围是[0, 2^32)
     * @param position         职务信息。长度为0~128个字符
     * @param gender           性别。1表示男性，2表示女性
     * @param email            邮箱。长度6~64个字节，且为有效的email格式。企业内必须唯一，mobile/email二者不能同时为空
     * @param telephone        座机。32字节以内，由纯数字或’-‘号组成。
     * @param isLeaderInDept   个数必须和参数department的个数一致，表示在所在的部门内是否为上级。1表示为上级，0表示非上级。在审批等应用里可以用来标识上级审批人
     * @param avatarMediaId    成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
     * @param enAble           启用/禁用成员。1表示启用成员，0表示禁用成员
     * @param extAttr          自定义字段。自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。与对外属性一致，不过只支持type=0的文本和type=1的网页类型，详细描述查看对外属性
     * @param toInvite         是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param externalProfile  成员对外属性，字段详情见对外属性
     * @param externalPosition 对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。长度12个汉字内
     * @param address          地址。长度最大128个字符
     * @param mainDepartment
     * @return
     */
    public static Response createUser(String accessToken, String userId, String name, String alias, String mobile,
                                      String department, String order, String position, String gender, String email,
                                      String telephone, String isLeaderInDept, String avatarMediaId, String enAble,
                                      String extAttr, String toInvite, String externalProfile, String externalPosition,
                                      String address, String mainDepartment) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userId);
        map.put("name", name);
        map.put("alias", alias);
        map.put("mobile", mobile);
        map.put("department", department);
        map.put("order", order);
        map.put("position", position);
        map.put("gender", gender);
        map.put("email", email);
        map.put("telephone", telephone);
        map.put("is_leader_in_dept", isLeaderInDept);
        map.put("avatar_mediaid", avatarMediaId);
        map.put("enable", enAble);
        map.put("extattr", extAttr);
        map.put("to_invite", toInvite);
        map.put("external_profile", externalProfile);
        map.put("external_position", externalPosition);
        map.put("address", address);
        map.put("main_department", mainDepartment);

        return HttpRequest.post(url, map, ContentType.JSON);
    }

    /**
     * 创建成员
     *
     * @param accessToken 调用接口凭证
     * @param user        用户业务对象
     * @return
     */
    public static Response createUser(String accessToken, UserDTO user) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("userid", user.getUserId());
        map.put("name", user.getName());
        map.put("alias", user.getAlias());
        map.put("mobile", user.getMobile());
        map.put("department", user.getDepartment());
        map.put("order", user.getOrder());
        map.put("position", user.getPosition());
        map.put("gender", user.getGender());
        map.put("email", user.getEmail());
        map.put("telephone", user.getTelephone());
        map.put("is_leader_in_dept", user.getIsLeaderInDept());
        map.put("avatar_mediaid", user.getAvatarMediaId());
        map.put("enable", user.getEnable());
        map.put("extattr", user.getExtAttr());
        map.put("to_invite", user.getToInvite());
        map.put("external_profile", user.getExternalProfile());
        map.put("external_position", user.getExternalPosition());
        map.put("address", user.getAddress());
        map.put("main_department", user.getMainDepartment());

        return HttpRequest.post(url, map, ContentType.JSON);
    }

    /**
     * 读取成员
     *
     * @param accessToken 调用接口凭证
     * @param userId      成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     * @return
     */
    public static Response getUser(String accessToken, String userId) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&userid=" + userId;
        return HttpRequest.get(url);
    }

    /**
     * 修改成员
     *
     * @param accessToken      调用接口凭证。获取方法查看“<a href="https://work.weixin.qq.com/api/doc/10013#%E7%AC%AC%E4%B8%89%E6%AD%A5%EF%BC%9A%E8%8E%B7%E5%8F%96access_token">获取access_token</a>”
     * @param userId           成员UserID
     * @param name             成员名称。长度为1~64个utf8字符
     * @param alias            成员别名。长度1~32个utf8字符
     * @param mobile           手机号码。企业内必须唯一。若成员已激活企业微信，则需成员自行修改（此情况下该参数被忽略，但不会报错）
     * @param department       成员所属部门id列表,不超过100个
     * @param order            部门内的排序值，默认为0，成员次序以创建时间从小到大排列。个数必须和参数department的个数一致，数值越大排序越前面。有效的值范围是[0, 2^32)
     * @param position         职务信息。长度为0~128个字符
     * @param gender           性别。1表示男性，2表示女性
     * @param email            邮箱。长度不超过64个字节，且为有效的email格式。企业内必须唯一。若是绑定了腾讯企业邮箱的企业微信，则需要在腾讯企业邮箱中修改邮箱（此情况下该参数被忽略，但不会报错）
     * @param telephone        座机。32字节以内，由纯数字或’-‘号组成。
     * @param isLeaderInDept   个数必须和参数department的个数一致，表示在所在的部门内是否为上级。1表示为上级，0表示非上级。在审批等应用里可以用来标识上级审批人
     * @param avatarMediaId    成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
     * @param enAble           启用/禁用成员。1表示启用成员，0表示禁用成员
     * @param extAttr          自定义字段。自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。与对外属性一致，不过只支持type=0的文本和type=1的网页类型，详细描述查看对外属性
     * @param toInvite         是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     * @param externalProfile  成员对外属性，字段详情见对外属性
     * @param externalPosition 对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。长度12个汉字内
     * @param address          地址。长度最大128个字符
     * @param mainDepartment
     * @return
     */
    public static Response updateUser(String accessToken, String userId, String name, String alias, String mobile,
                                      String department, String order, String position, String gender, String email,
                                      String telephone, String isLeaderInDept, String avatarMediaId, String enAble,
                                      String extAttr, String toInvite, String externalProfile, String externalPosition,
                                      String address, String mainDepartment) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userId);
        map.put("name", name);
        map.put("alias", alias);
        map.put("mobile", mobile);
        map.put("department", department);
        map.put("order", order);
        map.put("position", position);
        map.put("gender", gender);
        map.put("email", email);
        map.put("telephone", telephone);
        map.put("is_leader_in_dept", isLeaderInDept);
        map.put("avatar_mediaid", avatarMediaId);
        map.put("enable", enAble);
        map.put("extattr", extAttr);
        map.put("to_invite", toInvite);
        map.put("external_profile", externalProfile);
        map.put("external_position", externalPosition);
        map.put("address", address);
        map.put("main_department", mainDepartment);

        return HttpRequest.post(url, map, ContentType.JSON);
    }

    /**
     * 修改成员
     *
     * @param accessToken 调用接口凭证
     * @param user        用户业务对象
     * @return
     */
    public static Response updateUser(String accessToken, UserDTO user) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("userid", user.getUserId());
        map.put("name", user.getName());
        map.put("alias", user.getAlias());
        map.put("mobile", user.getMobile());
        map.put("department", user.getDepartment());
        map.put("order", user.getOrder());
        map.put("position", user.getPosition());
        map.put("gender", user.getGender());
        map.put("email", user.getEmail());
        map.put("telephone", user.getTelephone());
        map.put("is_leader_in_dept", user.getIsLeaderInDept());
        map.put("avatar_mediaid", user.getAvatarMediaId());
        map.put("enable", user.getEnable());
        map.put("extattr", user.getExtAttr());
        map.put("to_invite", user.getToInvite());
        map.put("external_profile", user.getExternalProfile());
        map.put("external_position", user.getExternalPosition());
        map.put("address", user.getAddress());
        map.put("main_department", user.getMainDepartment());

        return HttpRequest.post(url, map, ContentType.JSON);
    }

    /**
     * 删除用户
     *
     * @param accessToken 调用接口凭证
     * @param userId      成员UserID。对应管理端的帐号
     * @return
     */
    public static Response deleteUser(String accessToken, String userId) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + accessToken + "&userid=" + userId;
        return HttpRequest.get(url);
    }

    /**
     * 获取部门成员
     *
     * @param accessToken  调用接口凭证
     * @param departmentId 获取的部门id
     * @param fetchChild   是否递归获取子部门下面的成员：1-递归获取，0-只获取本部门
     * @return
     */
    public static Response simpleList(String accessToken, String departmentId, String fetchChild) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + accessToken + "&department_id=" + departmentId + "&fetch_child=" + fetchChild;
        return HttpRequest.get(url);
    }

}
