package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {
    @MethodSource("기준치 판단 테스트 데이터")
    @ParameterizedTest
    fun `카드의 합계가 기준치를 넘는지 판단한다`(
        cards: List<Card>,
        expected: Boolean,
    ) {
        // given
        val dealer = Dealer()
        cards.forEach { dealer.receiveCard(it) }

        // when
        val actual = dealer.isBurst()

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `기준치 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card("10", "하트"), Card("6", "다이아몬드")), false),
                Arguments.of(listOf(Card("10", "하트"), Card("7", "다이아몬드")), true),
                Arguments.of(listOf(Card("10", "하트"), Card("8", "다이아몬드")), true),
            )
    }
}
