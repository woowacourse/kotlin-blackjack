package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SplitTest {
    private lateinit var input: String

    @BeforeEach
    fun setUp() {
        input = "kmkim,,kkm"
    }

    @Test
    fun splitTest() {
        val names = input.split(",").map { it.trim() }
        assertThat(names.size).isEqualTo(3)
    }

    @Test
    fun splitElementTest() {
        val names = input.split(",").map { it.trim() }
        names.forEach {
            assertThat(it).isNotNull()
        }
    }
}
