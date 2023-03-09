package blackjack.domain.result

import blackjack.domain.participant.BettingPlayer
import blackjack.domain.participant.Player
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
        val results = playerResults.get()

        assertAll(
            { assertThat(results[0].name to results[0].result).isEqualTo("부나" to GameResult.LOSE) },
            { assertThat(results[1].name to results[1].result).isEqualTo("글로" to GameResult.WIN) },
            { assertThat(results[2].name to results[2].result).isEqualTo("반달" to GameResult.DRAW) },
            { assertThat(results[3].name to results[3].result).isEqualTo("제이슨" to GameResult.LOSE) }
        )
    }

    @Test
    fun `딜러의 승부 결과를 반환한다`() {
        with(playerResults.getDealerResult()) {
            assertAll(
                { assertThat(win).isEqualTo(2) },
                { assertThat(draw).isEqualTo(1) },
                { assertThat(lose).isEqualTo(1) }
            )
        }
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
