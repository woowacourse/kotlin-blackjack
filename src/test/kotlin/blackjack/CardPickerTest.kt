package blackjack

import CardPackGenerator
import CardPicker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPickerTest {
    @Test
    fun `카드 한 장을 뽑는다`() {
        val cardPack = CardPackGenerator().createCards()
        val card = CardPicker(cardPack).pick()
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.DIAMOND)
    }
}
