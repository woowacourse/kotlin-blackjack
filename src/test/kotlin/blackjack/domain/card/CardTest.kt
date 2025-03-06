package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `숫자와 패턴을 가진 카드를 생성한다`() {
        val card = Card.create(CardNumber.ACE, CardPattern.HEART)

        assertThat(card.number).isEqualTo(CardNumber.ACE)
        assertThat(card.pattern).isEqualTo(CardPattern.HEART)
    }
}
