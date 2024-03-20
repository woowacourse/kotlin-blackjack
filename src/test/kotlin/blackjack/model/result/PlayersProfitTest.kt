package blackjack.model.result

import blackjack.model.role.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayersProfitTest {
    @DisplayName("플레이어들의 수익을 가지고 딜러의 수익을 계산한다")
    @Test
    fun `플레이어들의 수익의 합의 반대 부호가 딜러의 수익이다`() {
        val playersProfit =
            PlayersProfit(
                mapOf(
                    PlayerName("심지") to Money(-1_000),
                    PlayerName("해나") to Money(-1_500),
                    PlayerName("팡태") to Money(3_000),
                ),
            )
        val actualDealerProfit = playersProfit.calculateDealerProfit()
        assertThat(actualDealerProfit).isEqualTo(Money(-500))
    }
}
