package blackjack.domain.model

import blackjack.domain.model.betting.BettingPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BettingPlayerTest {
    private lateinit var bettingPlayer: BettingPlayer

    @BeforeEach
    fun setUp() {
        bettingPlayer = BettingPlayer("동전", Money(1000.0))
    }

    @Test
    fun `승리 수익 금액을 계산한다`() {
        val actual = 1000.0
        assertThat(bettingPlayer.calculate(MatchResult.WIN).value).isEqualTo(actual)
    }

    @Test
    fun `패배 수익 금액을 계산한다`() {
        val actual = -1000.0
        assertThat(bettingPlayer.calculate(MatchResult.LOSE).value).isEqualTo(actual)
    }

    @Test
    fun `무승부 수익 금액을 계산한다`() {
        val actual = 0.0
        assertThat(bettingPlayer.calculate(MatchResult.DRAW).value).isEqualTo(actual)
    }

    @Test
    fun `블랙잭 수익 금액을 계산한다`() {
        val actual = 1500.0
        assertThat(bettingPlayer.calculate(MatchResult.BLACKJACK).value).isEqualTo(actual)
    }
}
