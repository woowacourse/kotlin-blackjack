package model

import io.kotest.matchers.shouldBe
import model.card.Card
import model.card.Denomination
import model.card.Suit
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.util.stream.Stream

class CardTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 52, -200, 99])
    fun `입력받은 숫자의 범위가 0 ~ 51를 넘어가면 예외 발생`(number: Int) {
        assertThatThrownBy {
            Card.from(number)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Card.INVALID_RANGE)
    }

    @ParameterizedTest
    @MethodSource("markProvider")
    fun `입력받은 숫자에 대해 생성되는 카드의 문양 검증`(
        number: Int,
        suit: Suit,
    ) {
        Card.from(number).suit shouldBe suit
    }

    @ParameterizedTest
    @MethodSource("valueProvider")
    fun `입력받은 숫자에 대해 생성되는 카드의 값 검증`(
        number: Int,
        denomination: Denomination,
    ) {
        Card.from(number).denomination shouldBe denomination
    }

    companion object {
        @JvmStatic
        fun markProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, Suit.SPADE),
                Arguments.of(13, Suit.CLOVER),
                Arguments.of(26, Suit.HEART),
                Arguments.of(39, Suit.DIAMOND),
            )
        }

        @JvmStatic
        fun valueProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, Denomination.ACE),
                Arguments.of(1, Denomination.TWO),
                Arguments.of(10, Denomination.JACK),
                Arguments.of(11, Denomination.QUEEN),
                Arguments.of(12, Denomination.KING),
            )
        }
    }
}
