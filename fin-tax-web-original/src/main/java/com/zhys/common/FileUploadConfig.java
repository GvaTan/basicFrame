package com.zhys.common;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class FileUploadConfig {
	/**
	 * 文件上传配置
	 * 
	 * @return MultipartConfigElement
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement(
			@Value("${multipart.maxFileSize}") String maxFileSize,
			@Value("${multipart.maxRequestSize}") String maxRequestSize) {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大
		DataSize maxFileSize1 = DataSize.ofMegabytes(Long.parseLong(maxFileSize));
		factory.setMaxFileSize(maxFileSize1 );
		// 设置总上传数据总大小
		DataSize maxRequestSize2= DataSize.ofMegabytes(Long.parseLong(maxRequestSize)); 
		factory.setMaxRequestSize(maxRequestSize2);
		return factory.createMultipartConfig();
	}
 
}
