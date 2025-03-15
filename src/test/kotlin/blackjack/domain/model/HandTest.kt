package blackjack.domain.model

import blackjack.domain.blackjackCardList
import blackjack.domain.bustCardList
import blackjack.domain.model.card.Hand
import blackjack.domain.notBustBut21
import blackjack.domain.notBustCardList
import blackjack.domain.twoAceCardList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `21점보다 숫자가 크면 Bust상태이다`() {
        // given
        val hand = Hand(bustCardList().toMutableList())
        // when
        val actual = hand.isBust()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `21점보다 숫자가 작으면 Bust상태가 아니다`() {
        // given
        val hand = Hand(notBustCardList().toMutableList())
        // when
        val actual = hand.isBust()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `카드 숫자의 합을 계산한다`() {
        // given
        val hand = Hand(notBustCardList().toMutableList())
        // when
        val actual = hand.getSumNumber()
        val expected = 5
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace가 두장일 때 하나는 11로 계산된다`() {
        // given
        val hand = Hand(twoAceCardList().toMutableList())
        // when
        val actual = hand.getSumNumber()
        val expected = 12
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드가 2장이고 총합이 21이면 블랙잭이다`() {
        // given
        val hand = Hand(blackjackCardList().toMutableList())
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `카드가 2장이고 총합이 21이 아닌 경우 블랙잭이 아니다`() {
        // given
        val hand = Hand(notBustCardList().toMutableList())
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `카드가 2장이 아니고 총합이 21인 경우 블랙잭이 아니다`() {
        // given
        val hand = Hand(notBustBut21().toMutableList())
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isFalse()
    }
}
