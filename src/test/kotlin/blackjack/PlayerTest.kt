package blackjack

import Player
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Player 생성`() {
        val hand = Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES)))
        val player = Player("하디", hand)

        assertThat(player.name).isEqualTo("하디")
        assertThat(player.hand).isEqualTo(hand)
    }

    @Test
    fun `Player의 Hit 상태여부`() {
        val hand = Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES)))
        val player = Player("하디", hand)

        assertThat(player.state).isEqualTo(State.Running.Hit)
    }
}
