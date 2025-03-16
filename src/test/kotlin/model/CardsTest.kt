package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import util.TestCards

class CardsTest {
    @Test
    fun `중복된 카드가 포함되면 예외가 발생한다`() {
        val duplicateCards =
            listOf(
                TestCards.CLUB_ACE,
                TestCards.CLUB_ACE,
            )

        assertThrows<IllegalArgumentException> {
            Cards(duplicateCards)
        }
    }
}
