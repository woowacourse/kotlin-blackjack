package blackjack.model

import model.Money
import model.Name
import model.Player
import model.Players
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
        assertThrows<IllegalArgumentException> { Players(listOf()) }
    }

    @Test
    fun `players가 9명이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(
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
        }
    }

    companion object {
        private fun Player(name: String): Player = Player(Name(name), Money(1_000L))
        private fun Players(vararg player: String): Players = Players(player.map { Player(it) })

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
