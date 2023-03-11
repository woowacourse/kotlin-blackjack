package blackjack.domain.result

import blackjack.domain.data.ParticipantResults
import blackjack.domain.participant.BettingPlayer
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultsTest {
    private lateinit var results: ParticipantResults

    @BeforeEach
    fun setUp() {
        results =
            GameResults(
                Dealer(),
                mapOf(
                    BettingPlayer(Player("부나"), 1000) to GameResult.BLACKJACK,
                    BettingPlayer(Player("글로"), 2000) to GameResult.WIN,
                    BettingPlayer(Player("반달"), 3000) to GameResult.DRAW,
                    BettingPlayer(Player("제이슨"), 4000) to GameResult.LOSE
                )
            ).getResults()
    }

    @Test
    fun `딜러의 승부 결과를 반환한다`() {
        assertAll(
            { assertThat(results.dealerResult.win).isEqualTo(1) },
            { assertThat(results.dealerResult.draw).isEqualTo(1) },
            { assertThat(results.dealerResult.lose).isEqualTo(2) }
        )
    }

    @Test
    fun `플레이어들의 승부 결과를 반환한다`() {
        assertAll(
            { assertThat(results.playerResults[0].name to results.playerResults[0].gameResult).isEqualTo("부나" to GameResult.BLACKJACK) },
            { assertThat(results.playerResults[1].name to results.playerResults[1].gameResult).isEqualTo("글로" to GameResult.WIN) },
            { assertThat(results.playerResults[2].name to results.playerResults[2].gameResult).isEqualTo("반달" to GameResult.DRAW) },
            { assertThat(results.playerResults[3].name to results.playerResults[3].gameResult).isEqualTo("제이슨" to GameResult.LOSE) }
        )
    }

    @Test
    fun `딜러의 수익은 모든 플레이어의 수익을 뺀 것이다`() {
        assertThat(results.dealerResult.profit).isEqualTo(500)
    }

    @Test
    fun `플레이어가 블랙잭이면 플레이어가 딜러로부터 배팅 금액의 3분의 2배를 얻는다`() {
        assertThat(results.playerResults[0].profit).isEqualTo(1500)
    }

    @Test
    fun `플레이어가 우승하면 플레이어가 딜러로부터 배팅 금액을 받는다`() {
        assertThat(results.playerResults[1].profit).isEqualTo(2000)
    }

    @Test
    fun `무승부면 딜러와 플레이어 모두 수익이 없다`() {
        assertThat(results.playerResults[2].profit).isEqualTo(0)
    }

    @Test
    fun `플레이어가 패배하면 플레이어는 딜러에게 배팅 금액을 준다`() {
        assertThat(results.playerResults[3].profit).isEqualTo(-4000)
    }
}
