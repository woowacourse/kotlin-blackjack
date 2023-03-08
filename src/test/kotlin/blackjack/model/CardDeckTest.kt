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
    fun `카드덱에서 드로우 시 가장 앞에 있는 A다이아몬드 카드를 뽑을 수 있다 `() {
        // given
        val cards = listOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.DEUCE, Suit.CLOVER), Card(Rank.JACK, Suit.HEART))
        val cardDeck = CardDeck(cards)

        // when
        val actual = cardDeck.drawCard()

        // then
        val expected = Card(Rank.ACE, Suit.DIAMOND)
        assertThat(actual.rank).isEqualTo(expected.rank)
        assertThat(actual.suit).isEqualTo(expected.suit)
    }

    @Test
    fun `생성한 카드덱의 사이즈는 52이다`() {
        val cardDeck = CardDeck.createCardDeck()
        assertThat(cardDeck.size).isEqualTo(52)
    }
}
