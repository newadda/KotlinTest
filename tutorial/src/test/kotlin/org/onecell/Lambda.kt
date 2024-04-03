package org.onecell


fun main()
{

    /// 람다식에서 return 사용 방법
    /// label 을 만들고 해당 라벨을 명확히 작성해야 한다.
    Iterable point@{
        val numbers = mutableListOf("one", "four", "four")
        return@point numbers.listIterator();
    }.forEach {
        println("iter : $it")
    }

    Iterable {
        val numbers = mutableListOf("one", "four", "four")
         numbers.listIterator()/// return 을 사용하면 안된다. 마지막 라인이 암시적 return 이다.
    }.forEach {
        println("iter : $it")
    }

    val testFun01:()->String ={"hello"}
    val testFun02:(String)->String={it+"hello"}
    val testFun03:(String)->String={str->str+"hello"}

}