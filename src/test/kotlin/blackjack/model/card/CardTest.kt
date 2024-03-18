package blackjack.model.card

import blackjack.fixture.ACE_CARD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Ace 카드인지 확인 가능`() {
        // given
        val aceCard = ACE_CARD
        // when
        val isAce = aceCard.isAce()
        // then
        assertThat(isAce).isTrue()
    }
}
