package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.card.Denomination
import blackjack.model.card.Suit
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

    @Test
    fun `덱에 A 스페이드가 한 장 있을 때, 카드를 뽑으면 A 스페이드이다`() {
        val customDeck = Deck(mutableListOf(Card(Denomination.ACE, Suit.SPADES)))
        val drawnCard = customDeck.dealCard()
        assertThat(drawnCard.suit).isEqualTo(Suit.SPADES)
        assertThat(drawnCard.denomination).isEqualTo(Denomination.ACE)
    }
}
