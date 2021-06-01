package domain.gamer

import domain.card.Card
import domain.card.Symbol
import domain.card.Value
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HandTest {

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("점수를 계산한다.")
    internal fun getScoreWithAce(cards: MutableList<Card>, expected: Int) {
        val hand = Hand(cards)

        Assertions.assertThat(hand.totalScore()).isEqualTo(expected)
    }

    @Test
    @DisplayName("버스트인지 확인한다.")
    internal fun isBust() {
        val hand = Hand(mutableListOf(Card(Symbol.HEART, Value.SEVEN)))

        assertThat(hand.isBust()).isFalse
    }

    @Test
    @DisplayName("점수를 계산한다.")
    internal fun getScore() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun parameterProvider() = Stream.of(
            Arguments.of(
                mutableListOf(
                    Card(Symbol.DIAMOND, Value.SEVEN),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), 17
            ),
            Arguments.of(
                mutableListOf(

                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), 21
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.ACE),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), 12
            ),
            Arguments.of(
                mutableListOf(
                    Card(Symbol.HEART, Value.ACE),
                    Card(Symbol.DIAMOND, Value.JACK),
                    Card(Symbol.HEART, Value.JACK),
                    Card(Symbol.CLOVER, Value.QUEEN)
                ), 31
            )
        )
    }
}