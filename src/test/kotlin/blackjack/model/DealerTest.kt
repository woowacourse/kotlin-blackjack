package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {
    @Test
    fun `딜러는 이름과 카드들을 가진다`() {
        val cards = listOf((Card(CardShape.HEART, "5")), Card(CardShape.CLOVER, "2"))
        val dealer = Dealer("모찌", cards)

        assertAll({
            assertThat(dealer.name).isEqualTo("모찌")
            assertThat(dealer.cards).isEqualTo(cards)
        })
    }
}
