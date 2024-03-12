package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `캐싱된 카드를 가져오는지 확인한다`() {
        val actual = Card.from(TestCardProvider)
        assertThat(actual).isSameAs(Card(Denomination.TWO, Suite.HEART))
    }
}
