package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16이하인지 판단한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.isOverCondition()).isFalse
    }

    @Test
    fun `딜러의 점수가 높을때 (플레이어총합13, 딜러총합20) 플레이어가 패배한다`() {
        val card1 = Card(Shape.HEART, CardNumber.JACK)
        val card2 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.SIX)
        val card4 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch2 = CardBunch(card3, card4)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.LOSE,
        )
    }

    @Test
    fun `플레이어의 점수가 높을때 (플레이어총합20, 딜러총합13) 플레이어가 승리한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.JACK)
        val card4 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch2 = CardBunch(card3, card4)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.WIN,
        )
    }

    @Test
    fun `같은 점수라면 비긴다 각 총합 20`() {
        val card1 = Card(Shape.HEART, CardNumber.QUEEN)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.JACK)
        val card4 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch2 = CardBunch(card3, card4)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.DRAW,
        )
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어는 블랙잭이 아닌 21일떄 딜러가 승리한다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.SIX)
        val card4 = Card(Shape.HEART, CardNumber.SEVEN)
        val card5 = Card(Shape.HEART, CardNumber.EIGHT)
        val cardBunch2 = CardBunch(card3, card4, card5)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.LOSE,
        )
    }

    @Test
    fun `딜러가 블랙잭이 아닌 21이고 플레이어는 블랙잭일떄 플레이가 승리한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card(Shape.HEART, CardNumber.EIGHT)
        val cardBunch1 = CardBunch(card1, card2, card3)
        val dealer = Dealer(cardBunch1)

        val card4 = Card(Shape.HEART, CardNumber.ACE)
        val card5 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch2 = CardBunch(card4, card5)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.WIN,
        )
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어는 블랙잭일떄 비긴다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.ACE)
        val card4 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch2 = CardBunch(card3, card4)
        val player = Player("krrong", cardBunch2)

        assertThat(dealer.compareScore(player.cardBunch.getTotalScore(), player.cardBunch.isBlackJack())).isEqualTo(
            Consequence.DRAW,
        )
    }
}
