package blackjack.domain.model.participant

import blackjack.domain.model.participant.bet.Profit
import blackjack.domain.model.participant.fixture.Dealers
import blackjack.domain.model.participant.fixture.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `딜러를 이길 경우 플레이어는 100%의 수익을 얻는다`() {
        assertThat(Players.SCORE_19_1000BET.calculateProfit(Dealers.SCORE_18))
            .isEqualTo(Profit(1000.0))
    }

    @Test
    fun `딜러를 블랙잭으로 이길 경우 플레이어는 150%의 수익을 얻는다`() {
        assertThat(Players.BLACKJACK_1000BET.calculateProfit(Dealers.SCORE_18))
            .isEqualTo(Profit(1500.0))
    }

    @Test
    fun `딜러와 무승부일 경우 플레이어의 수익은 발생하지 않는다`() {
        assertThat(Players.SCORE_18_1000BET.calculateProfit(Dealers.SCORE_18))
            .isEqualTo(Profit(0.0))
    }

    @Test
    fun `딜러에게 질 경우 플레이어는 -100%의 손실을 얻는다`() {
        assertThat(Players.BUST_1000BET.calculateProfit(Dealers.BLACKJACK))
            .isEqualTo(Profit(-1000.0))
    }
}
