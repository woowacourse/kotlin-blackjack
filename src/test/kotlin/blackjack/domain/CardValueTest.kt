package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardValueTest {

    @ParameterizedTest
    @CsvSource(value = ["ACE,1", "KING,10", "QUEEN,10", "JACK,10", "TEN,10", "NINE,9", "EIGHT,8", "SEVEN,7", "SIX,6", "FIVE,5", "FOUR,4", "THREE,3", "TWO,2"])
    fun `카드의 값들이 나온다`(cardValue: CardValue, value: Int) {
        assertThat(cardValue.value).isEqualTo(value)
    }
}
