package blackjack.domain

import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = [5_000, 500_000])
    fun `배팅 금액은 5000원 이상 50만원 이하이다`(amount: Int) {
        assertDoesNotThrow { BettingAmount(amount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [4_000, 501_000])
    fun `배팅 금액이 5000원 미만이거나 50만원 초과라면 에러가 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> { BettingAmount(amount) }
    }

    @Test
    fun `배팅 금액은 천원 단위로만 받을 수 있다`() {
        assertDoesNotThrow { BettingAmount(8000) }
    }

    @Test
    fun `배팅 금액이 천원 단위가 아니라면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { BettingAmount(5001) }
    }

    @Test
    fun `게임 결과를 받아 페이아웃을 반환한다`() {
        assertAll(
            { assertThat(BettingAmount(5000).getPayout(GameResult.WIN)).isEqualTo(10000) },
            { assertThat(BettingAmount(5000).getPayout(GameResult.BLACKJACK)).isEqualTo(7500) },
            { assertThat(BettingAmount(5000).getPayout(GameResult.DRAW)).isEqualTo(5000) },
            { assertThat(BettingAmount(5000).getPayout(GameResult.LOSE)).isEqualTo(0) }
        )
    }
}
