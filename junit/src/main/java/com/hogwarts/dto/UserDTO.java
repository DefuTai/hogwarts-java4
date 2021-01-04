package com.hogwarts.dto;

/**
 * 描述：用户业务对象
 *
 * @Author ruoqian
 * @Time 2020/12/23 8:25 下午
 * @Version 1.0
 **/
public class UserDTO {

    /**
     * 成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     */
    private String userId;
    /**
     * 成员名称。长度为1~64个utf8字符
     */
    private String name;
    /**
     * 别名。长度为1-32个utf8字符
     */
    private String alias;
    /**
     * 手机号码。企业内必须唯一。若成员已激活企业微信，则需成员自行修改（此情况下该参数被忽略，但不会报错）
     */
    private String mobile;
    /**
     * 成员所属部门id列表，不超过100个
     */
    private Integer[] department;
    /**
     * 部门内的排序值，默认为0。当有传入department时有效。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
     */
    private Integer[] order;
    /**
     * 职务信息。长度为0~128个字符
     */
    private String position;
    /**
     * 性别。1表示男性，2表示女性
     */
    private String gender;
    /**
     * 邮箱。长度不超过64个字节，且为有效的email格式。企业内必须唯一。若是绑定了腾讯企业邮箱的企业微信，则需要在腾讯企业邮箱中修改邮箱（此情况下该参数被忽略，但不会报错）
     */
    private String email;
    /**
     * 上级字段，个数必须和department一致，表示在所在的部门内是否为上级。
     */
    private String isLeaderInDept;
    /**
     * 启用/禁用成员。1表示启用成员，0表示禁用成员
     */
    private String enable;
    /**
     * 成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
     */
    private String avatarMediaId;
    /**
     * 座机。由1-32位的纯数字或’-‘号组成
     */
    private String telephone;
    /**
     * 地址。长度最大128个字符
     */
    private String address;
    /**
     * 主部门
     */
    private Integer mainDepartment;
    /**
     * 自定义字段。自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。与对外属性一致，不过只支持type=0的文本和type=1的网页类型，详细描述查看对外属性
     */
    private ExtAttrDTO extAttr;
    /**
     * 是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
     */
    private Boolean toInvite;
    /**
     * 对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。长度12个汉字内
     */
    private String externalPosition;
    /**
     * 成员对外属性，字段详情见对外属性
     */
    private ExternalProfileDTO externalProfile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        this.department = department;
    }

    public Integer[] getOrder() {
        return order;
    }

    public void setOrder(Integer[] order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsLeaderInDept() {
        return isLeaderInDept;
    }

    public void setIsLeaderInDept(String isLeaderInDept) {
        this.isLeaderInDept = isLeaderInDept;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getAvatarMediaId() {
        return avatarMediaId;
    }

    public void setAvatarMediaId(String avatarMediaId) {
        this.avatarMediaId = avatarMediaId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(Integer mainDepartment) {
        this.mainDepartment = mainDepartment;
    }

    public ExtAttrDTO getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(ExtAttrDTO extAttr) {
        this.extAttr = extAttr;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public String getExternalPosition() {
        return externalPosition;
    }

    public void setExternalPosition(String externalPosition) {
        this.externalPosition = externalPosition;
    }

    public ExternalProfileDTO getExternalProfile() {
        return externalProfile;
    }

    public void setExternalProfile(ExternalProfileDTO externalProfile) {
        this.externalProfile = externalProfile;
    }
}
