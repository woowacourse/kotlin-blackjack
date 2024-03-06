package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 한장씩 뽑아서 반환한다`() {
        val deck =
            CardDeck(
                mutableListOf(
                    Card(CardNumber.ACE, CardShape.CLOVER),
                    Card(CardNumber.TEN, CardShape.DIAMOND),
                    Card(CardNumber.FIVE, CardShape.SPADE),
                ),
            )
        assertThat(deck.pick()).isEqualTo(Card(CardNumber.FIVE, CardShape.SPADE))
    }
}
