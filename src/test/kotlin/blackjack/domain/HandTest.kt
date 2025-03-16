package blackjack.domain

import blackjack.domain.fixture.CARD_ACE_SPADE
import blackjack.domain.fixture.CARD_KING_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val hand = Hand(CARD_ACE_SPADE)
        assertThat(hand.cards).hasSameElementsAs(listOf(CARD_ACE_SPADE))
    }

    @Test
    fun `카드 총합을 알 수 있다`() {
        val hand = Hand(CARD_ACE_SPADE, CARD_KING_SPADE)
        assertThat(hand.score.value).isEqualTo(21)
    }
}
