package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HandCardsTest {
    @Test
    fun `손패는 2장 이상이다`() {
        assertThrows<IllegalArgumentException> {
            HandCards(emptyList())
        }
    }
}

fun createHandCards(): HandCards {
    val deck = Cards.createDeck()
    return HandCards(
        deck.cards
            .shuffled()
            .take(2),
    )
}

// 중복 있이 손패 뿌리기
fun createHandCards(size: Int): List<Card> {
    val deck = Cards.createDeck()
    return deck.cards
        .shuffled()
        .take(size)
}
