package study

import org.junit.jupiter.api.Test

sealed class Dog(val name: String) {
    class Baby : Dog("아기강아지")

    class Old : Dog("늙은 강아지")

    class Puppy3 : Dog("")

    fun bulk() = println("왈오라")
}

class SealedClass {
    @Test
    fun `abstract class`() {
        Dog.Baby().bulk()
        Dog.Old().bulk()
    }
}
