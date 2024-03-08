package blackjack.model.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class GameResultTypeTest {
    @MethodSource("게임 결과를 반전시키는 테스트 데이터")
    @ParameterizedTest
    fun `게임 결과를 반전시킨다`(
        gameResultType: GameResultType,
        expected: GameResultType,
    ) {
        val actual = gameResultType.reverse()
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `게임 결과를 반전시키는 테스트 데이터`() =
            listOf(
                Arguments.of(GameResultType.WIN, GameResultType.LOSE),
                Arguments.of(GameResultType.LOSE, GameResultType.WIN),
                Arguments.of(GameResultType.DRAW, GameResultType.DRAW),
            )
    }
}
