package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드들에서 카드를 반환한다`() {
        val cards = Cards(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(cards.draw()).isEqualTo(Card(Suit.HEART, Rank.SIX))
    }

    @Test
    fun `카드들에서 카드를 제거한다`() {
        val cards = Cards(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(cards.remove(Card(Suit.HEART, Rank.SIX))).isEqualTo(Cards(listOf()))
    }
}