package blackjack.model

import blackjack.model.role.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreBoardTest {
    @Test
    fun `딜러의 점수를 통해 플레이어의 우승 결과를 얻는다`() {
        val scoreBoard =
            ScoreBoard(
                mapOf(
                    PlayerName("심지") to 21,
                    PlayerName("해나") to 24,
                    PlayerName("팡태") to 18,
                ),
            )
        val actualPlayerWinning = scoreBoard.calculatePlayerWinning(dealerScore = 20)
        val expectedPlayerWinning =
            PlayerWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.VICTORY,
                    PlayerName("해나") to WinningResultStatus.DEFEAT,
                    PlayerName("팡태") to WinningResultStatus.DEFEAT,
                ),
            )
        assertThat(actualPlayerWinning).isEqualTo(expectedPlayerWinning)
    }
}
