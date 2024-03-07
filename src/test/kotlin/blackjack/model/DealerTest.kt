package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {

    class Dealer(name: String = DEFAULT_DEALER_NAME) : Participant(name) {

        fun openFirstCard(): Card {
            return getCards().firstOrNull() ?: throw IllegalArgumentException(ERROR_CARD_INDEX)
        }

        fun checkDealerScoreCondition(): Boolean {
            return getBlackJackScore() <= MIN_HAND_CARD_SCORE
        }

        companion object {
            private const val DEFAULT_DEALER_NAME = "딜러"
            private const val ERROR_CARD_INDEX = "가지고 있는 카드가 없습니다."
            private const val MIN_HAND_CARD_SCORE: Int = 16
        }
    }


    @Test
    fun `딜러는 첫번째 카드를 공개할 수 있다`() {
        val dealer = Dealer()
        val card = Card(Denomination.NINE, Suit.DIAMOND)

        dealer.draw(card)
        val firstDealerCard = dealer.openFirstCard()
        assertThat(firstDealerCard.getCardSuit()).isEqualTo(card.getCardSuit())
        assertThat(firstDealerCard.getCardDenomination()).isEqualTo(card.getCardDenomination())
    }

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 없으면, 에러를 던진다`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> { dealer.openFirstCard() }
    }
}
