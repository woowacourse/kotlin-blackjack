package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 자신이 Ace 카드인지 확인할 수 있다`() {
        // given
        val card = createCard(rank = Rank.ACE)
        // when
        val isAce = card.isAce()
        // then
        assertThat(isAce).isTrue()
    }
}
