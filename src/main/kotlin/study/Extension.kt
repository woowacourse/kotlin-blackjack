package study

fun main() {
    println("better".lastChar())
    println("100".lastChar())
    println(listOf("abc", "rookie", "Better", "12ㅎ").getFirstByBetter())
    println(3 addByBetter 5)
    println(3.addByBetter(5))
    println(5.sum(10))
    println(5.sum2(10))
    println(sb.apply {
        this.append("Yes")
        append("No")
    })
}

fun String.lastChar(): Char {
    return this.get(this.length - 1)
}

fun List<String>.getFirstByBetter(): List<Char> {
    val firsts = mutableListOf<Char>()
    for (item in this) {
        firsts.add(item[0])
    }
    return firsts
}

infix fun Int.addByBetter(before: Int): Int {
    return this + before;
}

data class Point(val x: Int, val y: Int) {
    fun plus(value: Point): Point = Point(x + value.x, y + value.y)
}

val sum = fun Int.(other: Int): Int = this + other
val sum2: Int.(Int) -> Int = { other -> plus(other) }

val sb = StringBuilder()
