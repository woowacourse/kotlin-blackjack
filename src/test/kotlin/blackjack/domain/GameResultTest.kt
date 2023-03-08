package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {
    @ParameterizedTest
    @CsvSource("BLACKJACK, 1.5", "WIN, 1.0", "DRAW, 0.0", "LOSE, -1.0")
    fun `게임 결과는 배당률을 갖는다`(result: GameResult, expected: Double) {
        assertThat(result.payout).isEqualTo(expected)
    }
}
