package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerWinningTest {
    @Test
    fun `딜러의 최종 승패를 판단한다`() {
        val playerWinning =
            PlayerWinning(
                mapOf(
                    "심지" to WinningResultStatus.DEFEAT,
                    "해나" to WinningResultStatus.DRAW,
                    "악어" to WinningResultStatus.VICTORY,
                    "팡태" to WinningResultStatus.VICTORY,
                ),
            )
        val actual = playerWinning.judgeDealerWinningResult()
        Assertions.assertThat(actual).isEqualTo(
            mapOf(
                WinningResultStatus.VICTORY to 1,
                WinningResultStatus.DRAW to 1,
                WinningResultStatus.DEFEAT to 2,
            ),
        )
    }
}
