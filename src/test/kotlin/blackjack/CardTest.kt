package blackjack

import Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드를 생성한다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.CLOVER)
    }

    @Test
    fun `카드에 맞는 점수를 반환한다`() {
        val card = Card(Rank.DEUCE, Suit.CLOVER)
        assertThat(card.rank.getScore(10)).isEqualTo(2)
    }
}
