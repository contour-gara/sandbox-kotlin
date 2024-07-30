package scopefunction

class Let

fun main(args:Array<String>){
    val foo: Int? = 3
    val hoge = foo?.let { it * 2 } ?: "a"
    println(hoge)
}
