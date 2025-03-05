package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {
    @ParameterizedTest
    @CsvSource(value = ["PUSH, 무", "WIN, 승", "LOSE, 패"])
    fun `각각의 게임 결과는 한국어 게임 결과를 가진다`(
        gameResult: GameResult,
        expected: String,
    ) {
        val actual = gameResult.koreanTitle

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["20, 20, PUSH", "20, 19, WIN", "19, 20, LOSE"])
    fun `기준 점수와 비교 점수를 받아서 기준 점수에 대한 게임 결과를 반환한다`(
        standardScore: Int,
        comparedScore: Int,
        expected: GameResult,
    ) {
        val actual = GameResult.of(standardScore, comparedScore)

        assertThat(actual).isEqualTo(expected)
    }
}
