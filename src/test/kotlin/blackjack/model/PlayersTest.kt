package blackjack.model

import model.Card
import model.Cards
import model.Name
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `players를 생성할 수 있다`() {
        val players = Players(
            Player("jason", Card(Rank.ACE, Suit.HEART)),
            Player("pobi", Card(Rank.DEUCE, Suit.HEART))
        )
        assertThat(players.size).isEqualTo(2)
        assertThat(players.players[0].name.value).isEqualTo("jason")
        assertThat(players.players[1].name.value).isEqualTo("pobi")
    }

    companion object {
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
