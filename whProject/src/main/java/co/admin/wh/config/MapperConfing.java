package co.admin.wh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan(basePackages = "co.admin.wh.**.mapper")
public class MapperConfing {
	
}
