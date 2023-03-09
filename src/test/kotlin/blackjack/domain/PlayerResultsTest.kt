package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerResultsTest {

    lateinit var playerResults: PlayerResults

    @BeforeEach
    fun setUp() {
        playerResults =
            PlayerResults(
                mapOf(
                    BettingPlayer(Player("부나"), 0) to GameResult.LOSE,
                    BettingPlayer(Player("글로"), 0) to GameResult.WIN,
                    BettingPlayer(Player("반달"), 0) to GameResult.DRAW,
                    BettingPlayer(Player("제이슨"), 0) to GameResult.LOSE
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

    @Test
    fun `플레이어가 블랙잭이면 플레이어가 딜러로부터 배당금의 3분의 2배를 얻는다`() {
        val results = PlayerResults(mapOf(BettingPlayer(Player("glo"), 1000) to GameResult.BLACKJACK))

        val profits = results.calculateProfits()

        assertAll(
            { assertThat(profits[0].profit).isEqualTo(-1500) },
            { assertThat(profits[1].profit).isEqualTo(1500) }
        )
    }

    @Test
    fun `플레이어가 우승하면 플레이어가 딜러로부터 배당금을 받는다`() {
        val results = PlayerResults(mapOf(BettingPlayer(Player("glo"), 1000) to GameResult.WIN))

        val profits = results.calculateProfits()

        assertAll(
            { assertThat(profits[0].profit).isEqualTo(-1000) },
            { assertThat(profits[1].profit).isEqualTo(1000) }
        )
    }

    @Test
    fun `플레이어가 패배하면 딜러가 플레이어로부터 배당금을 받는다`() {
        val results = PlayerResults(mapOf(BettingPlayer(Player("glo"), 1000) to GameResult.LOSE))

        val profits = results.calculateProfits()

        assertAll(
            { assertThat(profits[0].profit).isEqualTo(1000) },
            { assertThat(profits[1].profit).isEqualTo(-1000) }
        )
    }

    @Test
    fun `무승부면 딜러와 플레이어 모두 수익이 없다`() {
        val results = PlayerResults(mapOf(BettingPlayer(Player("glo"), 1000) to GameResult.DRAW))

        val profits = results.calculateProfits()

        assertAll(
            { assertThat(profits[0].profit).isEqualTo(0) },
            { assertThat(profits[1].profit).isEqualTo(0) }
        )
    }
}
