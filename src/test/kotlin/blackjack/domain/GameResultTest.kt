package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `플레이어가 21점을 초과하면 패배한다`() {
        assertThat(GameResult.judgePlayer(dealerScore = 22, playerScore = 22)).isEqualTo(GameResult.패)
    }

    @Test
    fun `딜러만 21점을 초과하면 플레이어가 승리한다`() {
        assertThat(GameResult.judgePlayer(dealerScore = 22, playerScore = 21)).isEqualTo(GameResult.승)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 플레이어가 딜러보다 점수가 높으면 플레이어가 승리한다`() {
        assertThat(GameResult.judgePlayer(dealerScore = 20, playerScore = 21)).isEqualTo(GameResult.승)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 플레이어와 딜러의 점수가 같으면 무승부이다`() {
        assertThat(GameResult.judgePlayer(dealerScore = 21, playerScore = 21)).isEqualTo(GameResult.무)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 딜러가 플레이어보다 점수가 높으면 플레이어가 패배한다`() {
        assertThat(GameResult.judgePlayer(dealerScore = 21, playerScore = 20)).isEqualTo(GameResult.패)
    }
}
