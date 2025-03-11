package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    private fun setCard(vararg card: Card) {
        card.forEach { player.addCard(it) }
    }

    @BeforeEach
    fun clear() {
        player = Player("test")
    }

    @Test
    fun `카드의 총합이 21이 넘으면 카드를 뽑을 수 없다`() {
        setCard(
            Card.of(Rank.TEN, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
            Card.of(Rank.KING, Suit.HEART),
        )

        assertThat(player.canHit()).isEqualTo(false)
    }
}
