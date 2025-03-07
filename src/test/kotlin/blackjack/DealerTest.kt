package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 총합이 17이 넘으면 추가 카드를 뽑을 수 없다`() {
        val player = Dealer()

        val card1 = Card.of(Rank.ACE, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.NINE, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(false)
    }
}
