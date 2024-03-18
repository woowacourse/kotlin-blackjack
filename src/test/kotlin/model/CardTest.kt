package model

import fixture.SPADE_ACE
import fixture.SPADE_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드의 Denomination 이 Ace 인지 판단할 수 있다`() {
        assertThat(SPADE_ACE.isAce()).isTrue
        assertThat(SPADE_TWO.isAce()).isFalse
    }
}
