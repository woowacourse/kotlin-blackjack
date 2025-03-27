package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드의 총합이 21이 넘으면 카드를 뽑을 수 없다`() {
        val player = Player("player", BetAmount(10000))

        val card1 = Card.of(rank = Rank.TEN, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.KING, suit = Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(false)
    }

    @Test
    fun `카드의 총합이 21을 넘지 않으면, 카드를 뽑을 수 있다`() {
        val player = Player("player", BetAmount(10000))

        val card1 = Card.of(rank = Rank.TEN, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.TWO, suit = Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(true)
    }
}
