package blackjack.model

import model.Card
import model.CardDeck
import model.Dealer
import model.Name
import model.Player
import model.Players
import model.Rank
import model.Result
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayersTest {
    @ParameterizedTest
    @MethodSource("createPlayers")
    fun `players는 1명부터 8명까지의 플레이어로 구성된다`(players: List<Player>) {
        assertDoesNotThrow { Players(players) }
    }

    @Test
    fun `players가 아무도 없으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Players() }
    }

    @Test
    fun `players가 9명이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(
                listOf(
                    "jason",
                    "Pobi",
                    "Sunny",
                    "Scot",
                    "Dooly",
                    "Sudal",
                    "Mendel",
                    "Met",
                    "Ring"
                )
            )
        }
    }

    @Test
    fun `2명의 플레이어 중 딜러의 카드 합보다 모든 플레이어의 합이 높으면 모두 승리한다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.HEART),
            Card(Rank.JACK, Suit.SPADE),
            Card(Rank.ACE, Suit.DIAMOND),
            Card(Rank.TEN, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER),
            Card(Rank.KING, Suit.SPADE)
        )
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val player1 = Player.from("jason")
        player1.drawFirst(cardDeck)
        val player2 = Player.from("pobi")
        player2.drawFirst(cardDeck)
        val players = Players(player1, player2)
        val playersResult = players.getGameResult(dealer)
        players.players.forEach {
            assertThat(playersResult[it.name]).isEqualTo(Result.WIN)
        }
    }

    companion object {
        private fun Player(name: String): Player = Player(Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())

        @JvmStatic
        fun createPlayers(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        Player("jason")
                    )
                ),
                Arguments.of(
                    listOf(
                        Player("jason"),
                        Player("Pobi"),
                        Player("Sunny"),
                        Player("Scot"),
                        Player("Dooly"),
                        Player("Sudal"),
                        Player("Mendel"),
                        Player("Met"),
                    )
                )
            )
        }
    }
}
