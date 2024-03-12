package blackjack

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandCardsTest {
    private lateinit var handCards: HandCards

    @BeforeEach
    fun setUp() {
        handCards = HandCards(Deck(NormalCardMachine()))
    }

    @Test
    fun `Cards는 새로운 카드를 가질 수 있다`() {
        handCards.add()
        assertThat(handCards.getCardsSize()).isEqualTo(INIT_CARD_SIZE + 1)
    }

    @Test
    fun `Cards는 첫번째 카드를 반환할 수 있다`() {
        assertThat(handCards.getFirstCard()).isEqualTo(TEST_FIRST_CARD)
    }

    @Test
    fun `Cards는 모든 카드를 반환할 수 있다`() {
        assertThat(handCards.getAllCards()).isEqualTo(TEST_ALL_CARDS)
    }

    @Test
    fun `Cards 점수를 합할 수 있다`() {
        assertThat(handCards.getCardsScore()).isEqualTo(TEST_INIT_CARD_SCORE)
    }

    @Test
    fun `Cards는 ACE가 있는지 판단 할 수 있다(ACE가 없는 경우)`() {
        assertThat(handCards.hasAce()).isFalse()
    }

    @Test
    fun `Cards는 ACE가 있는지 판단 할 수 있다(ACE가 있는 경우)`() {
        handCards.add()
        assertThat(handCards.hasAce()).isTrue()
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
        private const val TEST_INIT_CARD_SCORE = 10
        private const val TEST_FIRST_CARD = "2스페이드"
        private const val TEST_ALL_CARDS = "2스페이드, 8클로버"
    }
}
