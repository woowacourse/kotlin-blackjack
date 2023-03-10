package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `1이상 10이하에 해당하는 숫자를 가진다`(expected: Int) {
        val cardNumbers: List<Int> =
            CardNumber.values().map { it.value }
        assertThat(cardNumbers).contains(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J"])
    fun `숫자에 해당하는 이름을 가진다`(expected: String) {
        val cardNames: List<String> =
            CardNumber.values().map { it.word }
        assertThat(cardNames).contains(expected)
    }
}
