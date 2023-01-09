package org.oncell


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import java.util.*


@SpringBootApplication(scanBasePackages = [], exclude = [HibernateJpaAutoConfiguration::class])
open class WebApplication : SpringBootServletInitializer() , ApplicationListener<ContextClosedEvent> {

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


fun main(args:Array<String>){


    /// 내장 톰캣 사용시 기본적으로 읽는 properties 파일 이름을 프로젝트명등으로 바꿀수 있다.
    //System.setProperty("spring.config.name", CONFIG_NAME);
    System.setProperty("file.encoding", "UTF-8")



    SpringApplication.run(WebApplication::class.java, *args)
    // 혹은
     //runApplication<WebApplication>( *args)

}

fun init(){

    // 디폴트 타임존 설정
    TimeZone.setDefault(TimeZone.getTimeZone(WebApplication.TIME_ZONE))

    // 디폴트 locale 설정
    Locale.setDefault(Locale("ko", "kr"))

}