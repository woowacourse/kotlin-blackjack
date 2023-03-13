package domain.participants

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NamesTest {

    @Test
    fun `8명보다 많은 인원을 입력한 경우 예외를 발생시킨다`() {
        val names = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        assertThrows<IllegalArgumentException> { Names(names) }
    }

    @Test
    fun `중복된 값을 입력한 경우 예외를 발생시킨다`() {
        val names = listOf("pingu", "pingu")
        assertThrows<IllegalArgumentException> { Names(names) }
    }
}
