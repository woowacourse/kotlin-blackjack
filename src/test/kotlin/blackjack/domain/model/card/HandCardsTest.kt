package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardsTest {
    @Test
    fun `손패에 카드를 추가할 수 있다`() {
        val handCards: HandCards = HandCards()
        val card = Card(CardNumber.ACE, Suit.CLUB)

        handCards.add(card)

        val cardsOfHand = handCards.show()
        assertThat(cardsOfHand).hasSize(1)
    }

    @Test
    fun `손패에 카드 목록을 확인할 수 있다`() {
        val handCards: HandCards = HandCards()
        val queenHeart = Card(CardNumber.QUEEN, Suit.HEART)
        val aceSpade = Card(CardNumber.ACE, Suit.SPADE)

        handCards.add(queenHeart)
        handCards.add(aceSpade)

        val firstCardOfHand = handCards.show()
        assertThat(firstCardOfHand).isEqualTo(listOf(queenHeart, aceSpade))
    }

    @Test
    fun `손패에 카드 값이 21이 넘으면 버스트인지 알 수 있다`() {
        val handCards: HandCards = HandCards()
        val cards =
            listOf(
                Card(CardNumber.TEN, Suit.HEART),
                Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.TWO, Suit.CLUB),
            )
        cards.map { handCards.add(it) }

        val isBurst = handCards.isBurst()

        assertThat(isBurst).isEqualTo(true)
    }

    @Test
    fun `손패에 카드 값이 21이 넘지 않으면 버스트가 아닌지 알 수 있다`() {
        val handCards: HandCards = HandCards()
        val cards =
            listOf(
                Card(CardNumber.TEN, Suit.HEART),
                Card(CardNumber.TWO, Suit.CLUB),
            )
        cards.map { handCards.add(it) }

        val isBurst = handCards.isBurst()

        assertThat(isBurst).isEqualTo(false)
    }

    @Test
    fun `손패에 카드 값이 특정한 값보다 이하인지 알 수 있다`() {
        val handCards: HandCards = HandCards()
        val cards =
            listOf(
                Card(CardNumber.TEN, Suit.SPADE),
                Card(CardNumber.SIX, Suit.CLUB),
            )
        cards.map { handCards.add(it) }

        val isLessOrSameThanSixteen = handCards.isLessOrSameThan(16)

        assertThat(isLessOrSameThanSixteen).isEqualTo(true)
    }
}
