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
