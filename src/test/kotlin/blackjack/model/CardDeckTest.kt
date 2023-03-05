package blackjack.model

import model.Card
import model.CardDeck
import model.Rank
import model.Suit
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
}
