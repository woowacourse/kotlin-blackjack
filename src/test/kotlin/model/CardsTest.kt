package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `중복된 카드가 포함되면 예외가 발생한다`() {
        val duplicateCards =
            listOf(
                Card.of(CardRank.ACE, Shape.CLUB),
                Card.of(CardRank.ACE, Shape.CLUB),
            )

        assertThrows<IllegalArgumentException> {
            Cards(duplicateCards)
        }
    }

    companion object {
        fun cardOf(vararg card: Card): Cards {
            return Cards(card.toList())
        }
    }
}
