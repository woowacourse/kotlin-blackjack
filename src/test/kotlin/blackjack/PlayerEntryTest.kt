package blackjack

import Player
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.player.PlayerEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerEntryTest {
    @Test
    fun `Player List 생성`() {
        val cards1 = Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES)))
        val player1 = Player("하디", cards1)

        val cards2 = Hand(mutableListOf(Card(Denomination.TWO, Suit.SPADES), Card(Denomination.SIX, Suit.HEARTS)))
        val player2 = Player("채드", cards2)

        val players = listOf(player1, player2)
        val playerEntry = PlayerEntry(players)

        assertThat(playerEntry.players).isEqualTo(players)
    }

    @Test
    fun `PlayerEntry의 수는 1명 이상이어야 한다`() {
        val players = listOf<Player>()
        assertThrows<IllegalArgumentException> { PlayerEntry(players) }
    }

    @Test
    fun `PlayerEntry의 수는 8명이하 이어야 한다`() {
        val players = mutableListOf<Player>()
        val hand = Hand(mutableListOf())
        repeat(9) {
            players.add(Player("Player $it", hand))
        }
        assertThrows<IllegalArgumentException> {
            PlayerEntry(players)
        }
    }
}
