package blackjack.model.participant

import blackjack.model.result.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ProfitTest {
    @MethodSource("수익 계산 테스트 데이터")
    @ParameterizedTest
    fun `경기 결과에 따라 수익을 계산한다`(
        battingAmount: BattingAmount,
        gameResultType: GameResultType,
        expected: Profit,
    ) {
        // given
        val profit = Profit()

        // when
        val actual = profit.calculateProfit(battingAmount, gameResultType)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `수익 계산 테스트 데이터`() =
            listOf(
                Arguments.of(BattingAmount(1000), GameResultType.BLACKJACK, Profit(1500.0)),
                Arguments.of(BattingAmount(1000), GameResultType.WIN, Profit(1000.0)),
                Arguments.of(BattingAmount(1000), GameResultType.DRAW, Profit(0.0)),
                Arguments.of(BattingAmount(1000), GameResultType.LOSE, Profit(-1000.0)),
            )
    }
}
