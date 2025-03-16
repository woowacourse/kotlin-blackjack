package blackjack

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.domain.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 총합이 17이 넘으면 추가 카드를 뽑을 수 없다`() {
        val player = Dealer()

        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.NINE, suit = Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(false)
    }

    @Test
    fun `카드의 총합이 17을 넘지 않으면 추가 카드를 뽑을 수 있다`() {
        val player = Dealer()

        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.NINE, suit = Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(true)
    }
}
