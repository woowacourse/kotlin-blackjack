package blackjack.domain.model.participant

import blackjack.domain.model.participant.bet.Profit
import blackjack.domain.model.participant.fixture.Dealers
import blackjack.domain.model.participant.fixture.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 수익률은 모든 플레이어의 수익률의 합의 역이다`() {
        val dealerProfit =
            Dealers.SCORE_19.allPlayersMatchProfit(
                listOf(
                    Players.SCORE_19_1000BET,
                    Players.SCORE_18_1000BET,
                    Players.BLACKJACK_1000BET,
                    Players.NORMAL_21_1000BET,
                ),
            ) // 무승부 0 + 패배 -1000 + 블랙잭 승 1500 + 일반 승 1000
        assertThat(dealerProfit)
            .isEqualTo(Profit(-1500.0))
    }
}
