package blackjack.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
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
}
