package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Denomination, Suit 조합으로 된 객체 생성`() {
        val denomination = Denomination.ACE
        val suit = Suit.HEARTS
        val card = Card(denomination, suit)

        assertThat(card.denomination).isEqualTo(denomination)
        assertThat(card.suit).isEqualTo(suit)
    }
}
