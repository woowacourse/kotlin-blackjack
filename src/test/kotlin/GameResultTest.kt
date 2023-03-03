import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `player의 점수가 21점이 넘는 경우 player는 무조건 패배한다`() {
        assertThat(GameResult.valueOf(playerScore = 23, dealerScore = 19)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `player와 dealer의 점수가 모두 21점이 넘는 경우 player는 패배한다`() {
        assertThat(GameResult.valueOf(playerScore = 23, dealerScore = 24)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `dealer의 점수가 21점이 넘고 player의 점수는 21점이 넘지 않는 경우 player는 승리한다`() {
        assertThat(GameResult.valueOf(playerScore = 20, dealerScore = 24)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `dealer와 player의 점수가 21점이 넘지 않고, 같은 경우 무승부이다`() {
        assertThat(GameResult.valueOf(playerScore = 18, dealerScore = 18)).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `dealer와 player의 점수가 21점이 넘지 않고, player의 점수가 더 낮은 경우 player는 패배한다`() {
        assertThat(GameResult.valueOf(playerScore = 20, dealerScore = 21)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `dealer와 player의 점수가 21점이 넘지 않고, player의 점수가 더 높은 경우 player는 승리한다`() {
        assertThat(GameResult.valueOf(playerScore = 20, dealerScore = 18)).isEqualTo(GameResult.WIN)
    }
}
