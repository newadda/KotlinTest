package org.onecell

/**
 * https://kotlinlang.org/docs/operator-overloading.html#unary-prefix-operators
 */
data class Point(val x:Int, val y:Int)

operator fun Point.unaryMinus()=Point(-x, -y)


fun main()
{
    val point=Point(2,2)

    println(-point)

}