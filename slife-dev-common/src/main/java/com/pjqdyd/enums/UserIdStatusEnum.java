package com.pjqdyd.enums;

import lombok.Getter;

/**   
 * @Description:  [用户身份状态枚举]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Getter
public enum UserIdStatusEnum implements EnumCodeGetter{

    CUSTOMER(0, "顾客"),
    SHOPER(1, "店主");

    private Integer code;
    private String statusName;

    //自身构造器
    UserIdStatusEnum(Integer code, String statusName){
        this.code = code;
        this.statusName = statusName;
    }

}
