package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16보다 작을경우 딜러는 카드를 받을 수 있다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canGetCard()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 17보다 클경우 딜러는 카드를 받을 수 없다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.NINE)
        val cardBunch = CardBunch(card1, card2, card3)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canGetCard()).isFalse
    }

    @Test
    fun `플레이어보다 딜러의 점수가 높으면 딜러가 승리한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.NINE)
        val playerCardBunch = CardBunch(card1, card2)
        val dealerCardBunch = CardBunch(card2, card3)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어보다 딜러의 점수가 낮으면 플레이어가 승리한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.NINE)
        val playerCardBunch = CardBunch(card2, card3)
        val dealerCardBunch = CardBunch(card1, card2)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.WIN)
    }

    @Test
    fun `딜러의 점수가 21이고 플레이어 점수가 21이 아닌 경우 딜러가 승리한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.NINE)
        val card2 = Card.get(Shape.HEART, CardNumber.JACK)
        val card3 = Card.get(Shape.HEART, CardNumber.ACE)
        val playerCardBunch = CardBunch(card1, card2)
        val dealerCardBunch = CardBunch(card2, card3)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어와 딜러의 점수가 같으면 비긴다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val playerCardBunch = CardBunch(card1, card2)
        val dealerCardBunch = CardBunch(card1, card2)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.DRAW)
    }

    @Test
    fun `플레이어 점수가 21을 넘는 경우 딜러가 이긴다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.JACK)
        val playerCardBunch = CardBunch(card1, card2, card3)
        val dealerCardBunch = CardBunch(card1, card2)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.LOSE)
    }

    @Test
    fun `플레이어의 점수가 21보다 작고 딜러 점수가 21을 넘는 경우 딜러가 진다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.JACK)
        val playerCardBunch = CardBunch(card1, card2)
        val dealerCardBunch = CardBunch(card1, card2, card3)

        val player1 = Player("krrong", playerCardBunch)
        val dealer = Dealer(dealerCardBunch)

        val actual = dealer.versusPlayers(listOf(player1))

        assertThat(actual["krrong"]).isEqualTo(Consequence.WIN)
    }
}
