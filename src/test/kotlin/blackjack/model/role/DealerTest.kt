package blackjack.model.role

import blackjack.model.card.state.Done
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest

class DealerTest {
    @RepeatedTest(1000)
    fun `딜러가 턴을 진행하여 완료(블랙잭 혹은, 버스트 혹은, 스테이) 상태가 된다`() {
        val dealer = Dealer()
        val endState = dealer.runPhase(dealer.dealerDecisionCondition) {}
        assertThat(endState is Done).isTrue
    }
}
