package blackjack

import blackjack.model.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    @BeforeEach
    fun deckInitialize() {
        Deck.initialize()
    }

    @Test
    fun `Card의 개수가 52개인지 확인`() {
        assertThat(Deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `카드 한장 빼기`() {
        Deck.dealCard()
        Deck.dealCard()
        assertThat(Deck.cards.size).isEqualTo(50)
    }
}
