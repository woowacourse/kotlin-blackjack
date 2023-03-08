package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @Test
    fun `돈의 금액을 음수로 생성하면 에러가 발생한다`() {
        val amount = -1

        Assertions.assertThatIllegalArgumentException().isThrownBy { Money(amount) }
            .withMessage("돈의 금액은 음수일 수 없습니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 100000])
    fun `0 또는 양수의 금액으로 돈을 생성할 수 있다`(amount: Int) {
        assertDoesNotThrow { Money(amount) }
    }
}
