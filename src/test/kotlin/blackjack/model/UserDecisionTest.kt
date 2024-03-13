package blackjack.model

import blackjack.view.user.UserDecision
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserDecisionTest {
    @Test
    fun `정상적인 사용자 결정 테스트`() {
        assertThat(UserDecision.getUserDecision("y")).isEqualTo(UserDecision.YES)
        assertThat(UserDecision.getUserDecision("n")).isEqualTo(UserDecision.NO)
    }

    @Test
    fun `정상적이지 않은 사용자 결정 테스트`() {
        assertThrows<IllegalArgumentException> {
            UserDecision.getUserDecision("예스")
        }
    }
}
