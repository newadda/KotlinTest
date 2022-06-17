package org.onecell

class Annotation {
    @JvmName("fooListString")
    fun foo(a : List<String>) {
        println("foo(a : List<String>)")
    }

    @JvmName("fooListInt")
    fun foo(a : List<Int>) {
        println("foo(a : List<Int>)")
    }
}

fun main(args : Array<String>)
{

}