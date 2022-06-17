package org.onecell

class 싱글톤 {
}

object Singleton{}


class Singleton2{
    companion object{
        val a:String = "aaaaa"
    }
}


class Singleton3{

    companion object{
        @JvmStatic
        val a:String = "aaaaa"
    }
}


fun main(){
    val t1 = Singleton3.a
    val t2 = Singleton2.a

}
