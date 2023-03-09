package blackjack.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @Test
    fun `음수의 금액으로 돈을 생성하면 에러가 발생한다`() {
        val amount = -10000

        assertThatIllegalArgumentException().isThrownBy { Money(amount) }
            .withMessage("돈의 금액은 음수일 수 없습니다.")
    }

    @Test
    fun `10으로 나누어 떨어지지 않는 수의 금액으로 돈을 생성하면 에러가 발생한다`() {
        val amount = 10001

        assertThatIllegalArgumentException().isThrownBy { Money(amount) }
            .withMessage("돈의 금액은 10으로 나누어 떨어져야 합니다.")
    }

    @Test
    fun `1억을 초과하는 금액으로 돈을 생성하면 에러가 발생한다`() {
        val amount = 100_000_010

        assertThatIllegalArgumentException().isThrownBy { Money(amount) }
            .withMessage("돈의 금액은 최대 100000000 입니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 10, 100000])
    fun `10으로 나누어 떨어지는 음수가 아닌 1억 이하의 금액으로 돈을 생성할 수 있다`(amount: Int) {
        assertDoesNotThrow { Money(amount) }
    }
}
