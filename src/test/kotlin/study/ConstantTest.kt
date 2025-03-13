package study

import org.junit.jupiter.api.Test

class ConstantTest {
    @Test
    fun constant() {
        TODO("Not yet implemented")
    }
}

object A {
    private var d = "d"
    private val hi: List<String> = listOf("HI", "DD")

    fun aa(update: String) {
        d = update
    }
}
