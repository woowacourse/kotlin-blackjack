package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Ace 카드인지 확인 가능`() {
        // given
        val card = createCard(rank = Rank.ACE)
        // when

        val isAce = card.isAce()
        // then
        assertThat(isAce).isTrue()
    }
}
