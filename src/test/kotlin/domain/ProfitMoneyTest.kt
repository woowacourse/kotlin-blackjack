package domain

import blackjack.domain.BattingMoney
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.ProfitMoney
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitMoneyTest {

    @Test
    fun `1000원을 배팅하고 블랙잭 승리인 경우에는 1500의 이득을 얻는다`() {
        assertThat(
            ProfitMoney(
                battingMoney = BattingMoney(1000),
                gameResult = GameResult.BLACKJACK_WIN
            )
        ).isEqualTo(ProfitMoney(1500))
    }

    @Test
    fun `1000원을 배팅하고 승리인(블랙잭 승리x) 경우에는 1000의 이득을 얻는다`() {
        assertThat(
            ProfitMoney(
                battingMoney = BattingMoney(1000),
                gameResult = GameResult.WIN
            )
        ).isEqualTo(ProfitMoney(1000))
    }

    @Test
    fun `1000원을 배팅하고 무승부인 경우에는 0의 이득을 얻는다`() {
        assertThat(
            ProfitMoney(
                battingMoney = BattingMoney(1000),
                gameResult = GameResult.DRAW
            )
        ).isEqualTo(ProfitMoney(0))
    }

    @Test
    fun `1000원을 배팅하고 패배하는 경우에는 1000의 손해를 본다`() {
        assertThat(
            ProfitMoney(
                battingMoney = BattingMoney(1000),
                gameResult = GameResult.LOSE
            )
        ).isEqualTo(ProfitMoney(-1000))
    }
}