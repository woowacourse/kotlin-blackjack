package domain.money

import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultInfoTest {
    @Test
    fun `블랙잭일 때 수익금을 계산한다`() {
        val playerResultInfo = PlayerResultInfo(Result.BLACKJACK_WIN, Money(50))
        assertThat(playerResultInfo.calculateRevenue()).isEqualTo(75)
    }

    @Test
    fun `승리시 수익금을 계산한다`() {
        val playerResultInfo = PlayerResultInfo(Result.WIN, Money(50))
        assertThat(playerResultInfo.calculateRevenue()).isEqualTo(50)
    }

    @Test
    fun `무승부시 수익금을 계산한다`() {
        val playerResultInfo = PlayerResultInfo(Result.DRAW, Money(50))
        assertThat(playerResultInfo.calculateRevenue()).isEqualTo(0)
    }

    @Test
    fun `패배시 수익금을 계산한다`() {
        val playerResultInfo = PlayerResultInfo(Result.LOSS, Money(50))
        assertThat(playerResultInfo.calculateRevenue()).isEqualTo(-50)
    }
}

