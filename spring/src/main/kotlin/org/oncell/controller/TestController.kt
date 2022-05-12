package org.oncell.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping(value = [ "/test"])
open class TestController {

    @RequestMapping(value = ["/test01"])
    @ResponseBody
    fun test(@RequestParam(required = false) test:String?):Any?{



        return "zzzzz";

    }
}