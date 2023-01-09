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
}

class ScopeFunction {
    fun test01():Unit{

        var scopeTest = ScopeTest()

        val let = scopeTest.let {
            it.a = 2
            // it.testPrivate() // 오류, private 함수라 컴파일 오류
            //it.b=4 // 오류, setter 는 private 라 컴파일 오류
            it.b
            "test"
        }

        scopeTest.run {
            //this.b=1 // this 로 접근하지만 private 라 컴파일 오류. 생각상에는 접근 가능해야 하는데 확장함수라 그런가봄.
            //this.c //
            this.b

        }

        with(scopeTest){
            this.a

        }

        scopeTest.apply {
            //this.b=1 // this 로 접근하지만 private 라 컴파일 오류. 생각상에는 접근 가능해야 하는데 확장함수라 그런가봄.
            this.a=1

        }

    }
}