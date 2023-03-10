package blackjack.model

import model.cards.Card
import model.cards.Rank
import model.cards.Suit
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
    fun `ACE가 플레이어에게 11이 더 유리한 경우, 유리한 숫자로 반환된다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank.getScore(1)).isEqualTo(11)
    }

    @Test
    fun `ACE가 플레이어에게 1이 더 유리한 경우, 유리한 숫자로 반환된다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank.getScore(20)).isEqualTo(1)
    }
}
