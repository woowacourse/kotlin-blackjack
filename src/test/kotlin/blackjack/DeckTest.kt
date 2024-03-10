package blackjack

import blackjack.model.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var deck: Deck // 클래스 레벨 변수 선언

    @BeforeEach
    fun deckInitialize() {
        deck = Deck()
    }

    @Test
    fun `Card의 개수가 52개인지 확인`() {
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `카드 한장 빼기`() {
        deck.dealCard()
        deck.dealCard()
        assertThat(deck.cards.size).isEqualTo(50)
    }
}
