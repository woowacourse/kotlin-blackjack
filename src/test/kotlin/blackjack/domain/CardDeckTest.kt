package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    @Test
    fun `카드덱 잘 있는지 확인`() {
        val cardDeck = CardDeck(Cards.all())
        assertThat(cardDeck.size).isEqualTo(52)
    }

    @Test
    fun `카드 draw가 잘 되었는지 확인`() {
        val cardDeck = CardDeck(Cards.all())
        cardDeck.nextCard()
        assertThat(cardDeck.size).isEqualTo(51)
    }

    @Test
    fun `초기 카드는 52장이다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(Cards.all().take(51))
        }
    }

    @Test
    fun `카드는 중복이 없어야 합니다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(Cards.all() + Cards.all())
        }
    }
}
