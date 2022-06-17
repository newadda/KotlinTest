package org.onecell

import org.junit.jupiter.api.Test

class 배열 {
    @Test
    fun test01():Unit{
        var a = arrayOf("test",1)
        println(a.javaClass)

        var b = listOf("test","test")
        println(b.javaClass)


    }
}



