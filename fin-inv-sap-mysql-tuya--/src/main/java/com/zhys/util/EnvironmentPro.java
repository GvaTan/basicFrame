package com.zhys.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvironmentPro {

    public static   String dev_or_prod;
    @Value("${httpclient.dev_or_prod}")
    public  void setDev_or_prod(String dev_or_prod) {
        EnvironmentPro.dev_or_prod = dev_or_prod;
    }
}
