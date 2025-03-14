package blackjack.domain.participant

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.participant.Dealer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer.create()
    }

    @Test
    fun `딜러의 처음 공개하는 카드는 1장이어야 한다`() {
        // given
        val card1 = Card(CardRank.ACE, CardSuit.HEART)
        val card2 = Card(CardRank.TEN, CardSuit.HEART)
        dealer.addAll(listOf(card1, card2))

        // when
        val initialVisibleCards = dealer.showInitialCards()

        // then
        assertEquals(1, initialVisibleCards.size)
        assertEquals(card1, initialVisibleCards.first())
    }

    @Test
    fun `딜러의 점수가 16 이하이면 추가로 카드를 뽑을 수 있다`() {
        // given
        val card1 = Card(CardRank.TEN, CardSuit.HEART)
        val card2 = Card(CardRank.SIX, CardSuit.HEART)
        dealer.addAll(listOf(card1, card2))

        // when & then
        assertTrue(dealer.isDrawable())
    }

    @Test
    fun `딜러의 점수가 17 이상이면 추가로 카드를 뽑을 수 없다`() {
        // given
        val card1 = Card(CardRank.TEN, CardSuit.HEART)
        val card2 = Card(CardRank.SEVEN, CardSuit.HEART)
        dealer.addAll(listOf(card1, card2))

        // when & then
        assertFalse(dealer.isDrawable())
    }

    @Test
    fun `딜러가 처음 2장을 받은 후 추가로 카드를 뽑은 개수를 반환한다`() {
        // given
        val initialCards =
            listOf(
                Card(CardRank.TEN, CardSuit.HEART),
                Card(CardRank.SIX, CardSuit.HEART),
            )
        dealer.addAll(initialCards)

        // when
        dealer.addAll(listOf(Card(CardRank.TWO, CardSuit.CLUB)))

        // then
        assertEquals(1, dealer.additionalDrawCount)
    }
}
