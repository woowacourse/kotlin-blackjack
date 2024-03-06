package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HandCardsTest {
    @Test
    fun `손패는 2장 이상이다`() {
        assertThrows<IllegalArgumentException> {
            HandCards(emptyList())
        }
    }

    @Test
    fun `카드를 2장씩 묶어서 손패를 만든다`() {
        val cards: List<Card> = createHandCards(6 + 2)
        val expectedHandCardsSize = 4
        assertThat(cards.chunked(2)).hasSize(expectedHandCardsSize)
        // TODO : 2장씩 뿌리는 함수 만들기
        val hands = cards.chunked(2).map { HandCards(it) }
        val playerHands: List<HandCards> = hands.drop(1)
        val dealer = hands.take(1)
        // List<Hand> -> List<Player> + Dealer
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
