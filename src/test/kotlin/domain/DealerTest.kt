package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 총합이 16이하면 카드를 더 받아야 한다`() {
        val dealer = Dealer(
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.SIX)
            )
        )
        val actual = dealer.isPossibleDrawCard()
        assertThat(actual).isTrue
    }
}
