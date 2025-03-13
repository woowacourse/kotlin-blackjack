package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        val handCards = HandCards()

        handCards.addCard(Card(Number.ACE))

        assertThat(handCards.cards).containsExactly(Card(Number.ACE))
    }

    @Test
    fun `특정 인덱스의 카드를 반환할 수 있다`() {
        val handCards = HandCards()

        handCards.addCard(Card(Number.ACE))
        handCards.addCard(Card(Number.QUEEN))
        handCards.addCard(Card(Number.SEVEN))

        assertThat(handCards.getCardByIndex(1)).isEqualTo(Card(Number.QUEEN))
    }

    @Test
    fun `카드가 만들 수 있는 최선의 값을 반환할 수 있다1`() {
        val handCards = HandCards()

        handCards.addCard(Card(Number.ACE)) // 11
        handCards.addCard(Card(Number.QUEEN)) // 10

        assertThat(handCards.calculateBestCardValue()).isEqualTo(21)
    }

    @Test
    fun `카드가 만들 수 있는 최선의 값을 반환할 수 있다2`() {
        val handCards = HandCards()

        handCards.addCard(Card(Number.QUEEN)) // 10
        handCards.addCard(Card(Number.SEVEN)) // 7
        handCards.addCard(Card(Number.ACE)) // 1 (10취급시 bust)
        handCards.addCard(Card(Number.ACE)) // 1 (10취급시 bust)

        assertThat(handCards.calculateBestCardValue()).isEqualTo(19)
    }
}
