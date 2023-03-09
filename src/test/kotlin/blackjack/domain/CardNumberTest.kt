package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardNumberTest {
    @ParameterizedTest(name = "{0} 카드는 {1}점이다.")
    @CsvSource(
        "ACE, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5",
        "SIX, 6", "SEVEN, 7", "EIGHT, 8", "NINE, 9", "TEN, 10",
        "JACK, 10", "QUEEN, 10", "KING, 10"
    )
    fun `카드 숫자는 각각의 점수를 반환한다`(cardNumber: CardNumber, expected: Int) {
        assertThat(cardNumber.score).isEqualTo(expected)
    }
}
