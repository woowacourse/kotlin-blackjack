package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Name
import model.Player
import model.Players
import model.Rank
import model.Result
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

    @Test
    fun `3명의 플레이어 중 딜러의 카드 합보다 모든 플레이어의 합이 높으면 모두 승리한다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.HEART), Card(Rank.JACK, Suit.SPADE))
        val players = Players(
            Player("jason", Card(Rank.ACE, Suit.HEART), Card(Rank.TEN, Suit.CLOVER)),
            Player("pobi", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.QUEEN, Suit.HEART)),
            Player("sunny", Card(Rank.JACK, Suit.CLOVER), Card(Rank.KING, Suit.SPADE)),
        )
        val playersResult = players.getGameResult(dealer)
        players.players.forEach {
            assertThat(playersResult[it.name]).isEqualTo(Result.WIN)
        }
    }

    companion object {
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
