package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerResultsTest {

    lateinit var playerResults: PlayerResults

    @BeforeEach
    fun setUp() {
        playerResults =
            PlayerResults(
                mapOf(
                    "부나" to GameResult.LOSE,
                    "글로" to GameResult.WIN,
                    "반달" to GameResult.DRAW,
                    "제이슨" to GameResult.LOSE
                )
            )
    }

    @Test
    fun `플레이어들의 승부 결과를 반환한다`() {
        assertThat(playerResults.get()).isEqualTo(
            mapOf(
                "부나" to GameResult.LOSE,
                "글로" to GameResult.WIN,
                "반달" to GameResult.DRAW,
                "제이슨" to GameResult.LOSE
            )
        )
    }

    @Test
    fun `딜러의 승부 결과를 반환한다`() {
        assertThat(playerResults.getDealerResult()).isEqualTo(
            mapOf(
                GameResult.WIN to 2,
                GameResult.DRAW to 1,
                GameResult.LOSE to 1
            )
        )
    }
}
