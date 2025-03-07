package blackjack

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드의 총합이 21이 넘으면 카드를 뽑을 수 없다`() {
        val player = Player("player")

        val card1 = Card.of(Rank.TEN, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.KING, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.canHit()).isEqualTo(false)
    }
}
