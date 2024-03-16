package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "CLOVER, ACE",
        "HEART, TWO",
        "SPADE, KING",
    )
    fun `카드는 모양과 숫자를 알고 있다`(
        suit: String,
        score: String,
    ) {
        val card = Card(Suit.valueOf(suit), denomination = Denomination.valueOf(score))
        assertThat(card).isEqualTo(Card(Suit.valueOf(suit), Denomination.valueOf(score)))
    }
}
