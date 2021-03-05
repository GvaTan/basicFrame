package com.zhys.common;

import org.springframework.core.env.Environment;
import org.springframework.util.Assert;


public enum EnvironmentEnum {

    PROD,//生产
    FE,//联调
    QA,//测试
    STG;//仿真

    public static boolean isProdEnv(Environment env) {
        Assert.notNull(env, "env parameter not null.");

        return EnvironmentEnum.PROD.name().equalsIgnoreCase(env.getProperty("spring.profiles.active"));
    }

    @Override
    public String toString() {
        return this.name();
    }

}
