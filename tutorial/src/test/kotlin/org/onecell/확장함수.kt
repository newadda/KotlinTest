package org.onecell

class 확장함수 {
}

fun String.half():String=this.substring(0,this.length/2)

fun <T> Collection<T>.half():Collection<T>{
    val list= mutableListOf<T>()
    val half=this.size/2;
    for (i:Int in 0..half)
    {
        list.add(this.elementAt(i))
    }
    return list
}


fun main()
{
    val halfSize = "1234567".half()
    println("halfszie = $halfSize")

    val list = listOf<String>("a","b","c","d")
    val halfList = list.half()
    println("halfList = $halfList")
}


