package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefereeTest {
    @Test
    fun `플레이어 합계가 13이고, 딜러 합계가 7이면 딜러가 진다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.TWO)
        val playerCardBunch = CardBunch(card1, card2)
        val player = Player("krrong", playerCardBunch)

        val card3 = Card(Shape.HEART, CardNumber.THREE)
        val card4 = Card(Shape.HEART, CardNumber.FOUR)
        val dealerCardBunch = CardBunch(card3, card4)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.WIN

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어 합계가 7이고, 딜러 합계가 13이면 딜러가 이긴다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.THREE)
        val card2 = Card(Shape.HEART, CardNumber.FOUR)
        val playerCardBunch = CardBunch(card1, card2)
        val player = Player("krrong", playerCardBunch)

        val card3 = Card(Shape.HEART, CardNumber.ACE)
        val card4 = Card(Shape.HEART, CardNumber.TWO)
        val dealerCardBunch = CardBunch(card3, card4)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.LOSE

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어 합계가 7이고, 딜러 합계가 7이면 비긴다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.THREE)
        val card2 = Card(Shape.HEART, CardNumber.FOUR)
        val playerCardBunch = CardBunch(card1, card2)
        val player = Player("krrong", playerCardBunch)

        val dealerCardBunch = CardBunch(card1, card2)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.DRAW

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어 합계가 21이고, 딜러 합계가 21이면 딜러가 이긴다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val playerCardBunch = CardBunch(card1, card2)
        val player = Player("krrong", playerCardBunch)

        val dealerCardBunch = CardBunch(card1, card2)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.LOSE

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어 합계가 23이면, 딜러가 이긴다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val card3 = Card(Shape.HEART, CardNumber.JACK)
        val playerCardBunch = CardBunch(card1, card2, card3)
        val player = Player("krrong", playerCardBunch)

        val dealerCardBunch = CardBunch(card1, card2)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.LOSE

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러의 합이 23이면 플레이어가 이긴다`() {
        // given
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val card3 = Card(Shape.HEART, CardNumber.JACK)
        val playerCardBunch = CardBunch(card1, card2)
        val player = Player("krrong", playerCardBunch)

        val dealerCardBunch = CardBunch(card1, card2, card3)
        val dealer = Dealer(dealerCardBunch)

        val referee = Referee()
        val expected = Consequence.LOSE

        // when
        referee.chooseWinner(dealer, player)
        val actual = referee.gameResult["krrong"]

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
