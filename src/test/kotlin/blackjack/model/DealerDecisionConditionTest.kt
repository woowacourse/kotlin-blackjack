package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerDecisionConditionTest {
    private val dealerDecisionCondition = DealerDecisionCondition()

    @ParameterizedTest
    @ValueSource(ints = [16, 15, 5])
    fun `딜러의 카드 손 패 합이 16 이하이면 Hit 이다`(cardHandSum: Int) {
        assertThat(dealerDecisionCondition.isHit(cardHandSum)).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(ints = [17, 18, 20])
    fun `딜러의 카드 손 패 합이 16 초과이면 Stay 이다`(cardHandSum: Int) {
        assertThat(dealerDecisionCondition.isHit(cardHandSum)).isEqualTo(false)
    }
}
