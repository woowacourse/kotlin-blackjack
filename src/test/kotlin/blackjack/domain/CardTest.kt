package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드가 Jack이면 10으로 계산한다`() {
        val card = Card(Rank.JACK, Suit.CLUB)
        assertThat(card.getNumber()).isEqualTo(10)
    }

    @Test
    fun `카드가 Queen이면 10으로 계산한다`() {
        val card = Card(Rank.QUEEN, Suit.CLUB)
        assertThat(card.getNumber()).isEqualTo(10)
    }

    @Test
    fun `카드가 King이면 10으로 계산한다`() {
        val card = Card(Rank.KING, Suit.CLUB)
        assertThat(card.getNumber()).isEqualTo(10)
    }
}
