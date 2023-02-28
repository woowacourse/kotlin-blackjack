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

    @Test
    fun `현재 점수가 10 이하인 경우 ACE의 점수는 11이다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank.getScore(10)).isEqualTo(11)
    }

    @Test
    fun `현재 점수가 10 초과인 경우 ACE의 점수는 1이다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank.getScore(15)).isEqualTo(1)
    }
}
