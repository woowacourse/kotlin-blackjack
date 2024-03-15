package blackjack.model

import blackjack.model.result.DealerWinning
import blackjack.model.result.PlayerWinning
import blackjack.model.result.WinningResultStatus
import blackjack.model.role.PlayerName
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerWinningTest {
    @Test
    fun `딜러의 최종 승패를 판단한다`() {
        val playerWinning =
            PlayerWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.DEFEAT,
                    PlayerName("해나") to WinningResultStatus.DRAW,
                    PlayerName("악어") to WinningResultStatus.VICTORY,
                    PlayerName("팡태") to WinningResultStatus.VICTORY,
                ),
            )
        val actual = playerWinning.judgeDealerWinningResult()
        Assertions.assertThat(actual).isEqualTo(
            DealerWinning(
                mapOf(
                    WinningResultStatus.VICTORY to 1,
                    WinningResultStatus.DRAW to 1,
                    WinningResultStatus.DEFEAT to 2,
                ),
            ),
        )
    }
}
