package org.onecell

class ScopeTest(){
    var a=1;
    var b=2
    private set

    private var c=3


    fun test(){
        this.b=4
    }

    private fun testPrivate(){

    }

    public infix fun String.eq(val1:String):String
    {
        return "$this $val1"
    }
}

class ScopeFunction {
    fun test01():Unit{

        var scopeTest = ScopeTest()

        /// 변수를 인자로 하는 메소드의 호출
        val let = scopeTest.let {
            it.a = 2
            // it.testPrivate() // 오류, private 함수라 컴파일 오류
            //it.b=4 // 오류, setter 는 private 라 컴파일 오류
            it.b
            "test"
        }

        /// 변수내에서 동작하는 메소드 호출
        val run=scopeTest.run {
            //this.b=1 // this 로 접근하지만 private 라 컴파일 오류. 생각상에는 접근 가능해야 하는데 확장함수라 그런가봄.
            //this.c //
            this.b
            "zzz" eq "zxxxx"// 클래스 내부의 infix 함수를 사용할 수 있다.
        }

        /// 변수내에서 동작하는 메소드 호출
        /// run 과 같다.
       val with= with(scopeTest){
            this.a
            "zzz" eq "zxxxx"// 클래스 내부의 infix 함수를 사용할 수 있다.
            "test"
        }

        /// run 과 비슷하다 하지만 반환이 자기자신이다.
        scopeTest.apply {
            //this.b=1 // this 로 접근하지만 private 라 컴파일 오류. 생각상에는 접근 가능해야 하는데 확장함수라 그런가봄.
            this.a=1
            "zzz" eq "zxxxx"
        }

    }
}