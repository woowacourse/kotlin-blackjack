package blackjack.model.participant

import blackjack.model.DEFAULT_BATTING_AMOUNT
import blackjack.model.HEART_KING
import blackjack.model.HEART_THREE
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_FIVE
import blackjack.model.SPADE_TEN
import blackjack.model.card.Card
import blackjack.model.card.TestCardProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Player(
    amount: Int = DEFAULT_BATTING_AMOUNT,
    cards: List<Card> = listOf(),
): Player {
    return Player("olive", amount, cards)
}

class PlayerTest {
    @Test
    fun `카드의 점수가 21 미만이면 카드를 더 받을 수 있다`() {
        // given
        val player = Player(cards = listOf(HEART_THREE, SPADE_FIVE))

        // when
        val actual = player.receivableMoreCard()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `카드의 점수가 21 이상이면 카드를 더 받을 수 없다`() {
        // given
        val player = Player(cards = listOf(SPADE_TEN, HEART_KING, HEART_THREE))

        // when
        val actual = player.receivableMoreCard()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `게임 시작 시 플레이어가 2장의 카드를 받는다`() {
        // given
        val player = Player()

        // when
        player.initCard(TestCardProvider)

        // then
        val expected = listOf(HEART_KING, HEART_KING)
        assertThat(player.cards()).isEqualTo(expected)
    }

    @Test
    fun `배팅 금액이 1000원이고, 플레이어가 블랙잭인 경우 1500원의 수익을 얻는다`() {
        // given
        val dealer = Dealer(listOf(HEART_THREE))
        val player = Player(1000, listOf(SPADE_TEN, SPADE_ACE))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(1500)
    }

    @Test
    fun `플레이어와 딜러가 블랙잭인 경우 아무런 수익도 얻지 않는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN, SPADE_ACE))
        val player = Player(1000, listOf(SPADE_TEN, SPADE_ACE))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(0)
    }

    @Test
    fun `플레이어가 버스트인 경우 배팅 금액을 잃는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN))
        val player = Player(1000, listOf(SPADE_TEN, SPADE_TEN, SPADE_TEN))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(-1000)
    }

    @Test
    fun `플레이어는 버스트가 아니고 딜러가 버스트인 경우 배팅 금액만큼의 수익을 얻는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN, SPADE_TEN, SPADE_TEN))
        val player = Player(1000, listOf(SPADE_TEN))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(1000)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수와 같은 경우 아무런 수익도 얻지 않는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN))
        val player = Player(1000, listOf(SPADE_TEN))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(0)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 작은 경우 배팅 금액을 잃는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN, SPADE_TEN))
        val player = Player(1000, listOf(SPADE_TEN))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(-1000)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 큰 경우 배팅 금액만큼의 수익을 얻는다`() {
        // given
        val dealer = Dealer(listOf(SPADE_TEN))
        val player = Player(1000, listOf(SPADE_TEN, HEART_THREE))

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(1000)
    }
}
