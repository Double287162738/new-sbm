package com.sbm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.sbm.mapper")
public class NewSbmApplication {
    private static final Logger LOGGER = LogManager.getLogger(NewSbmApplication.class);

    public static void main(String[] args) {
      SpringApplication.run(NewSbmApplication.class, args);
       /* ApplicationContext applicationContext =
                new SpringApplicationBuilder(NewSbmApplication.class)
                        .initializers().run(args);
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();

        for (String profile : activeProfiles) {
            LOGGER.info("SpringBoot active profile:" + profile);
        }*/
    }


}
