package domain

import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.PlayerGameResults
import blackjack.domain.gameResult.ProfitMoney
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerGameResultsTest {

    @Test
    fun `woogi가 1000원 boogi가 5000원 on이 6000원의 이득을 본 경우 게임에 참가한 플레이어들의 이득 총합(12000)을 얻을 수 있다`() {
        val playerGameResults = PlayerGameResults(
            listOf(
                PlayerGameResult("woogi", ProfitMoney(1000)),
                PlayerGameResult("boogi", ProfitMoney(5000)),
                PlayerGameResult("on", ProfitMoney(6000)),
            )
        )

        assertThat(
            playerGameResults.getPlayersTotalProfit()
        ).isEqualTo(ProfitMoney(12000))
    }
}
