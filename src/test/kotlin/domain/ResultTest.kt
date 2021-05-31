package domain

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ResultTest {

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("승패 결과를 찾는다.")
    fun find(playerCards: MutableList<Card>, dealerCards: MutableList<Card>, result: Result) {
        val player = Player("mazzi", Hand(playerCards))
        val dealer = Dealer("딜러", Hand(dealerCards))

        assertThat(Result.find(player, dealer)).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun parameterProvider() = Stream.of(
            Arguments.of(
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.SEVEN),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), Result.LOSE
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), mutableListOf(
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.SEVEN)
                ), Result.WIN
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), Result.STAND_OFF
            )
        )
    }
}