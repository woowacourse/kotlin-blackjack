package blackjack

import Player
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Player 생성`() {
        val cards = Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES)))
        val player = Player("하디", cards)

        assertThat(player.name).isEqualTo("하디")
        assertThat(player.hand).isEqualTo(cards)
    }
}
