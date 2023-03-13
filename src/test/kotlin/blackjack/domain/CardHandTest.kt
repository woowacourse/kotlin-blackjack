package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardHandTest {
    @Test
    fun `게임을 시작할때 카드가 두장이 아니면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            CardHand(listOf(Card(CardNumber.A, Shape.SPADE)))
        }
    }

    @Test
    fun `게임을 시작할때 카드가 두장이어야한다`() {
        assertDoesNotThrow {
            CardHand(listOf(Card(CardNumber.A, Shape.SPADE), Card(CardNumber.A, Shape.HEART)))
        }
    }

    @Test
    fun `Ace를 1로 처리했을 떄의 합을 구한다`() {
        assertThat(
            cards(CardNumber.K, CardNumber.A).getMinimumCardsScore()
        ).isEqualTo(11)
    }

    @Test
    fun `가지고 있는 카드 중 Ace 카드가 한장인 경우 무조건 11로 계산`() {
        assertThat(
            cards(CardNumber.A, CardNumber.SEVEN).getTotalCardsScore()
        ).isEqualTo(18)
    }

    @Test
    fun `가지고 있는 Ace 카드가 2장 이상이면 한장만 11, 나머지는 1로 점수 계산`() {
        assertThat(
            cards(CardNumber.A, CardNumber.A).getTotalCardsScore()
        ).isEqualTo(12)
    }

    @Test
    fun `처음 받은 2장의 합이 21이면 blackjack 이다`() {
        assertThat(cards(CardNumber.A, CardNumber.K).isBlackJack()).isTrue
    }

    private fun cards(firstCardNumber: CardNumber, secondCardNumber: CardNumber): CardHand {
        return CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE)))
    }
}
