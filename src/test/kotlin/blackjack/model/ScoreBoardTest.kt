package blackjack.model

import blackjack.model.result.PlayerWinning
import blackjack.model.result.Score
import blackjack.model.result.ScoreBoard
import blackjack.model.result.ScoreBoard2
import blackjack.model.result.WinningResultStatus
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

    @Test
    fun `딜러의 점수를 통해 플레이어의 우승 결과를 얻는다2`() {
        val scoreBoard =
            ScoreBoard2(
                mapOf(
                    PlayerName("심지") to Score(21),
                    PlayerName("해나") to Score(24),
                    PlayerName("팡태") to Score(18),
                ),
            )
        val actualPlayerWinning = scoreBoard.calculatePlayerWinning(dealerScore = Score(20))
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
