package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.CardNumber
import blackjack.model.deck.HandCards
import blackjack.model.deck.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandCardsTest {
    private lateinit var handCards: HandCards

    @BeforeEach
    fun setUp() {
        handCards = HandCards()
        handCards.add(ADD_CARD_EXAMPLE)
    }

    @Test
    fun `Cards는 새로운 카드를 가질 수 있다`() {
        assertThat(handCards.cards.size).isEqualTo(ADD_CARD_SIZE)
    }

    @Test
    fun `Cards 점수를 계산할 수 있다`() {
        assertThat(handCards.calculateCardScore()).isEqualTo(ADD_CARD_SCORE)
    }

    @Test
    fun `ACE가 있을 경우, 총합이 21보다 작으면 11로 계산해서 반환한다`() {
        handCards.add(listOf(Card(CardNumber.ACE, Shape.HEART))) // Ace 추가
        assertThat(handCards.calculateCardScore()).isEqualTo(ADD_CARD_SCORE + 11)
    }

    companion object {
        private const val ADD_CARD_SIZE = 2
        private const val ADD_CARD_SCORE = 10
        private val ADD_CARD_EXAMPLE =
            listOf(Card(CardNumber.THIRD, Shape.HEART), Card(CardNumber.SEVENTH, Shape.DIAMOND))
    }
}
