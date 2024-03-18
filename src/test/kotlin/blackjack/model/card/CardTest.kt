package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 생성 시 캐싱된 카드뭉치에서 카드를 가져오는지 확인한다`() {
        val actual = Card.of(Denomination.ACE, Suite.HEART)
        val expected = Card.of(Denomination.ACE, Suite.HEART)
        assertThat(actual).isSameAs(expected)
    }
}
