package blackjack

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.Pattern
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 문양과 숫자의 조합으로 이루어져있다`() {
        val card = Card(Pattern.HEART, CardNumber.ACE)
        assertThat(card.pattern).isEqualTo(Pattern.HEART)
        assertThat(card.number).isEqualTo(CardNumber.ACE)
        println(CardNumber.ACE.value)
    }
}




