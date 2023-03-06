package blackjack.model

import model.Card
import model.Cards
import model.Rank
import model.Suit
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `중복된 카드가 있으면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Cards(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.ACE, Suit.CLOVER),
                ),
            )
        }
    }
}
