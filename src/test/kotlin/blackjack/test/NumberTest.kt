package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Number(
    value: Int,
) : Rank {
    override val possibleValues: List<Int> = listOf(value)

    init {
        require(possibleValues.all { possibleValue: Int -> possibleValue in RANGE })
    }

    companion object {
        private const val MIN = 2
        private const val MAX = 10
        val RANGE = MIN..MAX
    }
}

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
