package org.onecell


open class Tag(val name:String) {
    val list = mutableListOf<Tag>()
}

class Html:Tag("html")
{

}
class P:Tag("p")
{

}



inline fun html(init:Tag.()->Unit):Tag{
    val a=Html()
    a.init()
    return a;
}

inline fun Tag.p(init:Tag.()->Unit):Unit{
    val p = P()
    p.init()
    list.add(p)
}

fun printDoc(doc:Tag,indent:Int=0)
{
    val printIndent:(indent:Int)->Unit={
        for(i in 0..indent)
        {
            print(" ")
        }
    }

    printIndent(indent)
    println("<${doc.name}>")
    doc.list.forEach{
        printDoc(it,indent+1)
    }
    printIndent(indent)
    println("</${doc.name}>")
}





fun main() {

    val doc = html{
        p{
            p{

            }
        }
        p{
            p{

            }
        }
    }

    printDoc(doc)


}