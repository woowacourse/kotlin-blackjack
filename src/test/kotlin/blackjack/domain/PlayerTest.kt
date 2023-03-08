package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {
    @Test
    fun `이름과 카드뭉치를 넘겨받아 플레이어를 생성한다`() {
        val name = "krrong"
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        assertDoesNotThrow { Player(name, cardBunch) }
    }

    @Test
    fun `카드를 받아서 cardBunch에 추가한다`() {
        val name = "krrong"
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)

        val player = Player(name, cardBunch)
        val card3 = Card(Shape.HEART, CardNumber.NINE)
        player.receiveCard(card3)

        assertThat(player.cardBunch.cards.size).isEqualTo(3)
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

        assertThat(player.chooseWinner(dealer)).isEqualTo(Consequence.LOSE)
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

        assertThat(player.chooseWinner(dealer)).isEqualTo(Consequence.WIN)
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

        assertThat(player.chooseWinner(dealer)).isEqualTo(Consequence.DRAW)
    }
}
