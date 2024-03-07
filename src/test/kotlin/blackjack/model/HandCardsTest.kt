package blackjack.model

import blackjack.fixture.createCard
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
    fun `HandCards의 합이 null이면 버스트된다`() {
        // given
        val handCards =
            HandCards(
                createCard(rank = Rank.Seven),
                createCard(rank = Rank.Eight),
                createCard(rank = Rank.Nine),
            )
        // when
        val actual = handCards.isBust()
        // then
        assertThat(actual).isTrue()
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
