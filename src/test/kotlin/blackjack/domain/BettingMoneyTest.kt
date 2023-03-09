package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class BettingMoneyTest {
    @Test
    fun `배팅금액은 0원보다 커야한다`() {
        assertDoesNotThrow { BettingMoney(1000) }
    }

    @Test
    fun `베팅금액이 0원보다 작으면 에러가 난다`() {
        assertThrows<IllegalArgumentException> { BettingMoney(0) }
    }

    @Test
    fun `1000원을 2배 하여 반환한다`() {
        assertThat(BettingMoney(1000).multipleTwo()).isEqualTo(2000)
    }

    @Test
    fun `1000원을 2점5배 하여 반환한다`() {
        assertThat(BettingMoney(1000).multipleTwoPointFive()).isEqualTo(2500)
    }

    @Test
    fun `1000원을 1배 하여 반환한다`() {
        assertThat(BettingMoney(1000).multipleOne()).isEqualTo(1000)
    }

    @Test
    fun `1000원을 0배 하여 반환한다`() {
        assertThat(BettingMoney(1000).multipleZero()).isEqualTo(0)
    }
}
