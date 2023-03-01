package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 13])
    fun `카드 숫자의 범위는 1부터 13이다`(number: Int) {
        assertDoesNotThrow { CardNumber(number) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 14])
    fun `카드 숫자의 범위는 1부터 13을 벗어날 수 없다`(number: Int) {
        assertThrows<IllegalArgumentException> { CardNumber(number) }
    }

    @ParameterizedTest
    @CsvSource(
        "1, A", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6",
        "7, 7", "8, 8", "9, 9", "10, 10", "11, J", "12, Q", "13, K",
    )
    fun `카드 숫자를 문자로 반환한다`(number: Int, expected: String) {
        val cardNumber = CardNumber(number)
        assertThat(cardNumber.toLetter()).isEqualTo(expected)
    }

    @Test
    fun `카드 숫자를 정수형으로 반환한다`() {
        assertThat(CardNumber(10).toInt()).isEqualTo(10)
    }
}
