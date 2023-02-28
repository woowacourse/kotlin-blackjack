import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `myScore가 높은 경우 승리한다`() {
        assertThat(GameResult.valueOf(myScore = 20, otherScore = 18)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `myScore가 낮은 경우 패배한다`() {
        assertThat(GameResult.valueOf(myScore = 20, otherScore = 21)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `myScore와 otherScore가 같은 경우 무승부`() {
        assertThat(GameResult.valueOf(myScore = 20, otherScore = 20)).isEqualTo(GameResult.DRAW)
    }
}
