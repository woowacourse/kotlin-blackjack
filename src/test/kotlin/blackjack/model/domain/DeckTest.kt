package blackjack.model.domain

import blackjack.model.strategy.FalseShuffle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    private val deck: Deck = Deck(FalseShuffle())

    @Test
    fun `카드를 셔플해서 첫번째 장에 있는 카드를 나누어준다`() {
        val card = deck.spreadCard()

        assertThat(card).isEqualTo(Card(Shape.Diamond, CardNumber.Ace))
    }
}
