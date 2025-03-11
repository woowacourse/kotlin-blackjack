package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `getAllCard는 각 Card 인스턴스의 원본을 담은 복제본 리스트를 반환한다`() {
        val cards = Card.getAllCard()
        val copied = cards.toList()

        for (i in cards.indices) {
            assertThat(cards[i]).isSameAs(copied[i])
        }
    }

    @Test
    fun `getAllCard는 원본이 아닌 복제본을 반환한다`() {
        val cards = Card.getAllCard()
        cards.toMutableList()[0] = Card.of(Rank.TEN, Suit.CLUB)
        assertThat(Card.getAllCard()).isNotSameAs(cards)
    }
}
