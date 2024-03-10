package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerDecisionCondition {
    @ParameterizedTest
    @ValueSource(ints = [20, 19, 5])
    fun `플레이어의 카드 손 패 합이 21 미만이고 Hit 을 결정하면 Hit 이다`(cardHandSum: Int) {
        val playerDecideHit = true
        val playerDecisionCondition =
            DecisionCondition { _ ->
                playerDecideHit
            }
        assertThat(playerDecisionCondition.isHit(cardHandSum)).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(ints = [20, 19, 5])
    fun `딜러의 카드 손 패 합이 21 미만이고 Stay 를 결정하면 Stay 이다`(cardHandSum: Int) {
        val playerDecideHit = false
        val playerDecisionCondition =
            DecisionCondition { _ ->
                playerDecideHit
            }
        assertThat(playerDecisionCondition.isHit(cardHandSum)).isEqualTo(false)
    }
}
