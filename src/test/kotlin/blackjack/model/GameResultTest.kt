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
}
