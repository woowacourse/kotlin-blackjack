package blackjack.model.participant

import blackjack.model.DIAMOND_TWO
import blackjack.model.HEART_KING
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_FIVE
import blackjack.model.SPADE_TEN
import blackjack.model.card.TestCardProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 점수가 17 미만이면 카드를 더 받을 수 있다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN, SPADE_FIVE, SPADE_ACE))

        // when
        val actual = dealer.receivableMoreCard()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `카드의 점수가 17 이상이면 카드를 더 받을 수 없다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN, SPADE_ACE))

        // when
        val actual = dealer.receivableMoreCard()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `게임 시작 시 딜러가 2장의 카드를 받는다`() {
        // given
        val dealer = Dealer()

        // when
        dealer.initCard(TestCardProvider)

        // then
        val expected = listOf(HEART_KING, HEART_KING)
        assertThat(dealer.cards()).isEqualTo(expected)
    }

    @Test
    fun `딜러는 모든 플레이어들과의 점수를 비교해서 수익을 얻는다`() {
        // given
        val dealer = Dealer(listOf(DIAMOND_TWO))
        val players =
            Players.from(
                listOf("abc", "def"),
                listOf(1000, 1000),
            )

        // when
        players.playerGroup[0].receiveCard(listOf(SPADE_TEN))
        players.playerGroup[1].receiveCard(listOf(SPADE_ACE, HEART_KING))
        val actual = dealer.profit(players)

        // then
        assertThat(actual.price).isEqualTo(-2500)
    }
}
