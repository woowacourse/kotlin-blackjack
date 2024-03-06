package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class GameResultTest {
    @MethodSource("게임 결과를 반전시키는 테스트 데이터")
    @ParameterizedTest
    fun `게임 결과를 반전시킨다`(
        gameResult: GameResult,
        expected: GameResult,
    ) {
        val actual = gameResult.reverse()
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `게임 결과를 반전시키는 테스트 데이터`() =
            listOf(
                Arguments.of(GameResult.WIN, GameResult.LOSE),
                Arguments.of(GameResult.LOSE, GameResult.WIN),
                Arguments.of(GameResult.DRAW, GameResult.DRAW),
            )
    }
}
