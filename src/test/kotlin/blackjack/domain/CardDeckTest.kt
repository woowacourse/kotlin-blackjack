package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드는 53장 이상 반환될 수 없다`() {
        // given
        val cardDeck = CardDeck()
        val exceedCount = 53

        // when & then
        assertThrows<IllegalArgumentException> {
            repeat(exceedCount) { cardDeck.draw() }
        }
    }

    @Test
    fun `카드는 총 52장까지 뽑을 수 있다`() {
        // given
        val cardDeck = CardDeck()
        val cardCount = 52

        // when
        val cards: List<Card> = List(cardCount) { cardDeck.draw() }

        // then
        assertThat(cards.size).isEqualTo(cardCount)
    }
}
