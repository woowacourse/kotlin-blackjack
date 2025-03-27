package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 덱에서 2장의 카드를 뽑았을 때 서로 다른 카드이다`() {
        val cardDeck = CardDeck.create()
        val card1 = cardDeck.pickCard()
        val card2 = cardDeck.pickCard()

        assertThat(card1 != card2).isTrue()
    }

    @Test
    fun `카드 덱의 카드는 52장이다`() {
        val cardDeck = CardDeck.create()
        val actual = cardDeck.size

        val expected = 52

        assertThat(actual).isEqualTo(expected)
    }
}
