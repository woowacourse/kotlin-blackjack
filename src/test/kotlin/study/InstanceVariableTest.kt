package study

import org.junit.jupiter.api.Test

class InstanceVariableTest {
    @Test
    fun test() {
        val moongchi = Moongchi("뭉치", 20)
        moongchi.name
        moongchi.age
        moongchi.height
    }
}

abstract class Participant(
    val name: String,
    val age: Int = 0,
)

class Moongchi(
    name: String,
    age: Int,
) : Participant(name, age) {
    val height: Int = 170
}
