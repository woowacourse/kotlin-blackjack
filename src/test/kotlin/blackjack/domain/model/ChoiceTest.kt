package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChoiceTest {
    @Test
    fun `y가 입력될 시 true를 반환한다`() {
        assertThat(Choice("y").isHit()).isEqualTo(true)
    }

    @Test
    fun `n이 입력될 시 true를 반환한다`() {
        assertThat(Choice("n").isHit()).isEqualTo(false)
    }
}
