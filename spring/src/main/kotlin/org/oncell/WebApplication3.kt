package org.oncell

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent

//@SpringBootApplication(scanBasePackages = [], exclude = [HibernateJpaAutoConfiguration::class])
open class WebApplication3 : SpringBootServletInitializer() , ApplicationListener<ContextClosedEvent> {

    companion object{
        val TIME_ZONE = "Asia/Seoul"
    }

    override fun onApplicationEvent(event: ContextClosedEvent) {

    }

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {

        /// 기본적으로 읽는 properties 파일 이름을 프로젝트명등으로 바꿀수 있다.
        /// 예를 들어 기본은 application.properties 이지만 app-test.properteis 로 바꿀수 있다.
        //return builder.sources(WebApplication.class).properties("spring.config.name: "+ CONFIG_NAME);
        return super.configure(builder)
    }
}
