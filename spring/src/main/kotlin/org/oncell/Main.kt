package org.oncell

import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication
import java.util.*

open class Main {
}

fun main(args:Array<String>){


    /// 내장 톰캣 사용시 기본적으로 읽는 properties 파일 이름을 프로젝트명등으로 바꿀수 있다.
    //System.setProperty("spring.config.name", CONFIG_NAME);
    System.setProperty("file.encoding", "UTF-8")

    // 디폴트 타임존 설정


    // 디폴트 타임존 설정
    TimeZone.setDefault(TimeZone.getTimeZone(WebApplication3.TIME_ZONE))

    // 디폴트 locale 설정

    // 디폴트 locale 설정
    Locale.setDefault(Locale("ko", "kr"))

     SpringApplication.run(WebApplication3::class.java, *args)
    // 혹은
   // runApplication<WebApplication3>( *args)



}