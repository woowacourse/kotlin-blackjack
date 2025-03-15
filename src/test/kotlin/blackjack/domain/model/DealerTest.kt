package blackjack.domain.model

import blackjack.domain.SPADE_FOUR
import blackjack.domain.SPADE_NINE
import blackjack.domain.blackjackCardList
import blackjack.domain.bustCardList
import blackjack.domain.model.participant.Dealer
import blackjack.domain.notBustCardList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    private val dealer = Dealer()

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // given
        dealer.receiveCard(notBustCardList().toMutableList())
        // when
        val actual = dealer.cardDeck
        val expected = notBustCardList()
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러의 점수 합계가 16점 미만이라면 더 뽑을 수 있다`() {
        // given
        dealer.receiveCard(listOf(SPADE_NINE, SPADE_FOUR))
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `딜러의 점수 합계가 16점 이상이라면 더 뽑을 수 없다`() {
        // given
        dealer.receiveCard(bustCardList().toMutableList())
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `딜러는 초기 카드로 1장을 보여 준다`() {
        // given
        dealer.receiveCard(blackjackCardList().toMutableList())
        // when
        val actual = dealer.getInitCard().size
        val expected = 1
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
