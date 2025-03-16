package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드가 Queen이면 10으로 계산한다`() {
        val card = Card(Rank.QUEEN, Suit.CLUBS)
        assertThat(card.getScore()).isEqualTo(10)
    }

    @Test
    fun `카드가 King이면 10으로 계산한다`() {
        val card = Card(Rank.KING, Suit.HEARTS)
        assertThat(card.getScore()).isEqualTo(10)
    }

    @Test
    fun `카드가 Jack이면 10으로 계산한다`() {
        val card = Card(Rank.JACK, Suit.DIAMONDS)
        assertThat(card.getScore()).isEqualTo(10)
    }
}
