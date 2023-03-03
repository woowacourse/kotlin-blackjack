package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    @Test
    fun `초기 카드 덱은 52장이다`() {
        val cardDeck = CardDeck(Cards.all())
        assertThat(cardDeck.size).isEqualTo(52)
    }

    @Test
    fun `카드를 뽑으면 숫자가 줄어든다`() {
        val cardDeck = CardDeck(Cards.all())
        cardDeck.nextCard()
        assertThat(cardDeck.size).isEqualTo(51)
    }

    @Test
    fun `초기 카드가 52장이 아니면 에러난다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(Cards.all().take(51))
        }
    }

    @Test
    fun `초기 카드에 중복이 있으면 에러난다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(Cards.all().take(26) + Cards.all().take(26))
        }
    }
}
