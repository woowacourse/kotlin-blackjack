package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Card는 52개의 인스턴스만 가질 수 있다`() {
        val cards = Card.getAllCard()
        val copied = cards.toList()

        for (i in cards.indices) {
            assertThat(cards[i]).isSameAs(copied[i])
        }
    }

    @Test
    fun `Card의 POOL은 바뀌지 않는다`() {
        val cards = Card.getAllCard()
        cards.toMutableList()[0] = Card.of(Rank.TEN, Suit.CLUB)
        assertThat(Card.getAllCard()).isNotSameAs(cards)
    }
}
