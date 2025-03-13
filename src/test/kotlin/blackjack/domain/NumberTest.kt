package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NumberTest {
    @Test
    fun `숫자는 2부터 10까지 존재한다`() {
        assertThrows<IllegalArgumentException> { Number(1) }
        assertThrows<IllegalArgumentException> { Number(11) }
    }

    @Test
    fun `숫자는 해당 숫자로 계산한다`() {
        val number = Number(2)
        assertThat(number.possibleValues).contains(2)
    }
}
