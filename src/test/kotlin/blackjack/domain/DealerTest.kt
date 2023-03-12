package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16보다 작을경우 딜러는 카드를 받을 수 있다`() {
        val cardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canHit()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 17보다 클경우 딜러는 카드를 받을 수 없다`() {
        val cardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.NINE)
        )
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canHit()).isFalse
    }

    @Test
    fun `플레이어보다 딜러의 점수가 높으면 딜러가 승리한다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.NINE)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어보다 딜러의 점수가 낮으면 딜러가 진다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.NINE)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.WIN)
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 비긴다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.DRAW)
    }

    @Test
    fun `플레이어 점수가 21을 넘는 경우 딜러가 이긴다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.JACK)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어의 점수가 21보다 작고 딜러 점수가 21을 넘는 경우 딜러가 진다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.JACK)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.WIN)
    }

    @Test
    fun `플레이어와 딜러의 점수가 모두 21을 넘는경우 딜러가 이긴다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.JACK)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.SIX),
            Card.get(Shape.HEART, CardNumber.SEVEN),
            Card.get(Shape.HEART, CardNumber.JACK)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭인 경우 비긴다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.ACE),
            Card.get(Shape.HEART, CardNumber.JACK)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.ACE),
            Card.get(Shape.HEART, CardNumber.JACK)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.DRAW)
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 경우 딜러가 이긴다`() {
        val playerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.TWO),
            Card.get(Shape.HEART, CardNumber.JACK)
        )
        val dealerCardBunch = CardBunch(
            Card.get(Shape.HEART, CardNumber.ACE),
            Card.get(Shape.HEART, CardNumber.JACK)
        )

        val player1 = Player("krrong", playerCardBunch, BettingMoney(1_000))
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual[player1]).isEqualTo(Consequence.LOSE)
    }
}
