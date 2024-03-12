package blackjack.model.winning

import blackjack.model.playing.participants.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerWinningTest {
    @Test
    fun `딜러의 최종 결과를 가져온다`() {
        val playerWinning =
            PlayerWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.DEFEAT,
                    PlayerName("해나") to WinningResultStatus.PUSH,
                    PlayerName("악어") to WinningResultStatus.VICTORY,
                    PlayerName("팡태") to WinningResultStatus.VICTORY,
                ),
            )
        val dealerFinalResult = DealerWinning(playerWinning).getFinalResult()
        val expectedFinalResult =
            mapOf(
                WinningResultStatus.VICTORY to 1,
                WinningResultStatus.PUSH to 1,
                WinningResultStatus.DEFEAT to 2,
            )

        assertThat(dealerFinalResult).isEqualTo(expectedFinalResult)
    }
}
