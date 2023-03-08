package blackjack.model

import model.Card
import model.CardDeck
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드덱에 중복된 카드가 들어가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.ACE, Suit.CLOVER)
                )
            )
        }
    }

    @Test
    fun `카드덱 생성 시 52장이 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            CardDeck(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER)
                )
            )
        }
    }

    @Test
    fun `카드덱에서 카드를 A다이아몬드 카드를 뽑을 수 있다 `() {
        val cardDeck = CardDeck.createCardDeck()
        val card = cardDeck.drawCard()
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.DIAMOND)
    }

    @Test
    fun `생성한 카드덱의 사이즈는 52이다`() {
        val cardDeck = CardDeck.createCardDeck()
        assertThat(cardDeck.size).isEqualTo(52)
    }
}
