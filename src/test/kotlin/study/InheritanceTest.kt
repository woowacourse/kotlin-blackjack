package study

open class Player1(
    val name: String,
) {
    val a: Int = 10
    open val b: Int = 15
}

open class Player2(
    val name: String,
)

class Player4(
    val name: String,
)

abstract class Player3(
    val name: String,
) {
    fun getter(): String = "$name + 1"
}

class A(
    name: String,
) : Player1(name) {
    override val b: Int = 25

    fun start() {
        println(a)
        println(b)
        println(this.b)
    }
}

class Three(
    name: String,
) : Player3(name)

fun main() {
    val a = A("뭉치")
    a.start()
}
