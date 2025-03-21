package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        val handCards = HandCards()

        handCards.cardAdd(Card(CardNumber.ACE))

        assertThat(handCards.cards).containsExactly(Card(CardNumber.ACE))
    }

    @Test
    fun `특정 인덱스의 카드를 반환할 수 있다`() {
        val handCards = HandCards(mutableListOf(Card(CardNumber.ACE), Card(CardNumber.QUEEN), Card(CardNumber.SEVEN)))

        assertThat(handCards.retrieveCard(1)).isEqualTo(Card(CardNumber.QUEEN))
    }

    @Test
    fun `카드가 만들 수 있는 최선의 값을 반환할 수 있다1`() {
        val handCards = HandCards()

        handCards.cardAdd(Card(CardNumber.ACE)) // 11 (1취급시 최선의 값x)
        handCards.cardAdd(Card(CardNumber.QUEEN)) // 10

        assertThat(handCards.bestCardValue).isEqualTo(21)
    }

    @Test
    fun `카드가 만들 수 있는 최선의 값을 반환할 수 있다2`() {
        val handCards = HandCards()

        handCards.cardAdd(Card(CardNumber.QUEEN)) // 10
        handCards.cardAdd(Card(CardNumber.SEVEN)) // 7
        handCards.cardAdd(Card(CardNumber.ACE)) // 1 (10취급시 bust)
        handCards.cardAdd(Card(CardNumber.ACE)) // 1 (10취급시 bust)

        assertThat(handCards.bestCardValue).isEqualTo(19)
    }
}
