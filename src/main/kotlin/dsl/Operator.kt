package dsl
class Operator(val first: String, val last: String) {
    operator fun rem(other: Operator): String {
        return this.first + other.last
    }
}

fun main() {
    val op1 = Operator("1first", "1last")
    val op2 = Operator("2first", "2last")
    println(op1 % op2)
}
