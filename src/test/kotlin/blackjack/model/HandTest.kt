package blackjack.model

import blackjack.model.TrumpCards.ACE_CARD
import blackjack.model.TrumpCards.FOUR_CARD
import blackjack.model.TrumpCards.JACK_CARD
import blackjack.model.TrumpCards.TEN_CARD
import blackjack.model.TrumpCards.TWO_CARD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `딜링슈에서 초기 카드를 2장 가져온다`() {
        val dealingShoe = DealingShoe(listOf(FOUR_CARD, TWO_CARD))
        val hand = Hand()
        hand.pickCard(dealingShoe, 2)
        val actual = hand.showCard()

        assertThat(actual.size).isEqualTo(2)
        assertThat(actual).isEqualTo(listOf(FOUR_CARD, TWO_CARD))
    }

    @Test
    fun `딜링슈에서 맨 앞의 2하트 카드를 한 개 뽑아 가져온다`() {
        val dealingShoe = DealingShoe(listOf(FOUR_CARD, TWO_CARD))
        val hand = Hand()
        hand.pickCard(dealingShoe)
        val actual = hand.showCard()

        assertThat(actual).isEqualTo(listOf(FOUR_CARD))
    }

    @DisplayName("isBlackJack 테스트")
    @Test
    fun `카드가 2장이고 총 합이 21이면 true를 반환한다`() {
        val hand = creatHand(TEN_CARD, ACE_CARD)
        val actual = hand.isBlackJack()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드가 3장 이상이고 총 합이 21이면 false를 반환한다`() {
        val hand = creatHand(TEN_CARD, TEN_CARD, ACE_CARD)
        val actual = hand.isBlackJack()
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun `카드가 21이 아니면 false를 반환한다`() {
        val hand = creatHand(TEN_CARD, TEN_CARD, TWO_CARD)
        val actual = hand.isBlackJack()
        assertThat(actual).isEqualTo(false)
    }

    @DisplayName("isBusted 테스트")
    @Test
    fun `카드 총 합이 21 이상이면 true를 반환한다`() {
        val hand = creatHand(TEN_CARD, TEN_CARD, TWO_CARD)
        val actual = hand.isBusted()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 20 이하면 false 반환한다`() {
        val hand = creatHand(TEN_CARD, TEN_CARD)
        val actual = hand.isBusted()
        assertThat(actual).isEqualTo(false)
    }

    @DisplayName("isMaxScore 테스트")
    @Test
    fun `카드 총 합이 21과 같으면 true를 반환한다`() {
        val hand = creatHand(TEN_CARD, ACE_CARD)
        val actual = hand.isMaxScore()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 21이 아니면 false를 반환한다`() {
        val hand = creatHand(TEN_CARD, TEN_CARD)
        val actual = hand.isMaxScore()
        assertThat(actual).isEqualTo(false)
    }

    @Nested
    @DisplayName("getCardSum 테스트")
    inner class GetCardSumTest {
        @Test
        fun `JACK과 TWO 카드의 총 합으로 12를 반환한다`() {
            val hand = creatHand(JACK_CARD, TWO_CARD)
            val actual = hand.getCardSum()
            val expected = 12
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `에이스가 11일 때 총 합에 10을 더해 21을 반환한다`() {
            val hand = creatHand(ACE_CARD, JACK_CARD)
            val actual = hand.getCardSum()
            val expected = 21
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `에이스가 1일 때 총 합 16을 반환한다`() {
            val hand = creatHand(ACE_CARD, JACK_CARD, TWO_CARD)
            val actual = hand.getCardSum()
            val expected = 13
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `에이스가 3장일 때 총 합 13을 반환한다`() {
            val hand = creatHand(ACE_CARD, ACE_CARD, ACE_CARD)
            val actual = hand.getCardSum()
            val expected = 13
            assertThat(actual).isEqualTo(expected)
        }
    }

    private fun creatHand(vararg cards: Card): Hand {
        val hand = Hand()
        val dealingShoe = DealingShoe(cards.toList())
        hand.pickCard(dealingShoe, cards.size)
        return hand
    }
}
