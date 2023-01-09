package org.onecell

import org.junit.jupiter.api.Test

class 배열 {
    @Test
    fun test01():Unit{
        var a = arrayOf("test",1)
        println(a.javaClass)

        var arr = Array<Array<Int>>(3){Array<Int>(3){ 0 } }
        val arr2: Array<Int> = Array(3) { 0 }

        var b = listOf("test","test")
        println(b.javaClass)



    }
}



