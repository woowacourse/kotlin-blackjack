package blackjack.model.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `카드 손 패 점수의 대소를 비교한다`() {
        val score = Score(19)
        val score2 = Score(17)
        assertTrue(score > score2)
    }

    @Test
    fun `플레이어 점수가 21 보다 크면 딜러의 점수와 상관없이 플레이어가 패배한다`() {
        val playerScore = Score(23)
        val dealerScore = Score(23)

        val actualPlayerWinning = playerScore.determineWinning(dealerScore)
        assertThat(actualPlayerWinning).isEqualTo(WinningResultStatus.DEFEAT)
    }

    @Test
    fun `플레이어 점수가 21 보다 작거나 같고 딜러의 점수가 21 보다 크면 플레이어가 승리한다`() {
        val playerScore = Score(20)
        val dealerScore = Score(23)

        val actualPlayerWinning = playerScore.determineWinning(dealerScore)
        assertThat(actualPlayerWinning).isEqualTo(WinningResultStatus.VICTORY)
    }

    @Test
    fun `플레이어와 딜러의 점수가 21 보다 작을 때 플레이어 점수가 더 낮으면 플레이어가 패배한다`() {
        val playerScore = Score(19)
        val dealerScore = Score(20)
        val actualPlayerWinning = playerScore.determineWinning(dealerScore)

        assertThat(actualPlayerWinning).isEqualTo(WinningResultStatus.DEFEAT)
    }

    @Test
    fun `플레이어와 딜러의 점수가 21 보다 작을 때 플레이어 점수가 더 높으면 플레이어가 승리한다`() {
        val playerScore = Score(20)
        val dealerScore = Score(19)
        val actualPlayerWinning = playerScore.determineWinning(dealerScore)

        assertThat(actualPlayerWinning).isEqualTo(WinningResultStatus.VICTORY)
    }

    @Test
    fun `플레이어와 딜러의 점수가 21 보다 작을 때 점수가 서로 같은면 무승부이다`() {
        val playerScore = Score(19)
        val dealerScore = Score(19)
        val actualPlayerWinning = playerScore.determineWinning(dealerScore)

        assertThat(actualPlayerWinning).isEqualTo(WinningResultStatus.DRAW)
    }
}
