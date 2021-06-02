package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.Symbol
import blackjack.domain.card.Value
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Player
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GameResultTest {

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("승패 결과를 찾는다.")
    fun find(playerCards: MutableList<Card>, dealerCards: MutableList<Card>, gameResult: GameResult) {
        val player = Player("mazzi", Hand(playerCards))
        val dealer = Dealer("딜러", Hand(dealerCards))

        assertThat(GameResult.find(player, dealer)).isEqualTo(gameResult)
    }

    @ParameterizedTest
    @CsvSource(
        "WIN,LOSE",
        "LOSE,WIN",
        "STAND_OFF,STAND_OFF"
    )
    @DisplayName("결과를 뒤집는다.")
    internal fun reverse(origin: GameResult, expected: GameResult) {
        val result = origin.reverse()

        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun parameterProvider() = Stream.of(
            Arguments.of(
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.SEVEN),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ),
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ),
                GameResult.LOSE
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ),
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.SEVEN)
                ),
                GameResult.WIN
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ),
                mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ),
                GameResult.STAND_OFF
            )
        )
    }
}
