package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 문양과 숫자의 조합으로 이루어져있다`() {
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.ACE)))
        val card = cards.first()
        assertThat(card.pattern).isEqualTo(Pattern.HEART)
        assertThat(card.number).isEqualTo(CardNumber.ACE)
        println(CardNumber.ACE.value)
    }
}
