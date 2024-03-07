package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserDecisionTest {

    enum class UserDecision(
        private val userDecision: String
    ) {
        YES("y"),
        NO("n");

        companion object {
            private const val ERROR_DECISION = "잘못 된 결정입니다."

            fun getUserDecision(input: String): UserDecision {
                return UserDecision.entries.find {
                    it.userDecision == input
                } ?: throw IllegalArgumentException(ERROR_DECISION)
            }
        }
    }

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
