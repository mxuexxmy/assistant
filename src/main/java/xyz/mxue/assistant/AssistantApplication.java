package xyz.mxue.assistant;

import cn.dev33.satoken.SaTokenManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.mxue.assistant.property.FileStorageProperties;


@SpringBootApplication
@MapperScan("xyz.mxue.assistant.mapper")
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class AssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

}
