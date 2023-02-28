package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `1이상 10이하에 해당하는 숫자를 가진다`(expected: Int) {
        val cardNumbers: List<Int> =
            CardNumber.values().map { it.number }
        assertThat(cardNumbers).contains(expected)
    }
}
