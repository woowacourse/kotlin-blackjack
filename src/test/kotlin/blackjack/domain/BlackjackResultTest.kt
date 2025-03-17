package blackjack.domain

import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackResultTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("포르")
    }

    @Test
    fun `플레이어가 1000원 배팅해서 이기면 딜러의 수익은 -1000원이다`() {
        val blackjackResult =
            BlackjackResult(
                dealerResult = mapOf(player to GameResult.LOSE),
                playersResult = mapOf(player to GameResult.WIN),
            )

        val bettingInfo = mapOf(player to BettingAmount(1000))

        val dealerProfit = blackjackResult.dealerProfit(bettingInfo)

        assertThat(dealerProfit.value).isEqualTo(-1000.0)
    }

    @Test
    fun `플레이어가 1000원 배팅해서 이기면 플레이어의 수익은 1000원이다`() {
        val blackjackResult =
            BlackjackResult(
                dealerResult = mapOf(player to GameResult.LOSE),
                playersResult = mapOf(player to GameResult.WIN),
            )

        val bettingInfo = mapOf(player to BettingAmount(1000))

        val playersProfit = blackjackResult.playersProfit(bettingInfo)

        assertThat(playersProfit[player]?.value).isEqualTo(1000.0)
    }
}
