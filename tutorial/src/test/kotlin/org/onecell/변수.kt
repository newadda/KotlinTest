package org.onecell

/*
  데이터 타입과 변수 선언
 */

// Unit 는 아무것도 안 던지겠다는 것이다.


fun test01():Unit{
    // val 는 읽기 전용 변수이다. 한번만 초기화 되고 그후 값 할당은 불가
    // var 는 읽기쓰기 변수이다.


    val v_byte : Byte = 8
    val v_short : Short = 8
    val v_int : Int = 8
    val v_long : Long = 8L
    val v_long2 : Long = 800_000L

    val one = 1 // 암시적으로 Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long

    val v_float : Float = 23.3F
    val v_double : Double = 23.3

    val pi = 3.14 // Double
    val oneDouble = 1.0 // Double

    123 // Decials
    123L // Long
    0x0f // 16진수
    0b000001011 // 2진수


}


