package domain.participant

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    private fun Card(number: Int): Card {
        return Card.of(CardCategory.CLOVER, CardNumber.values().find { it.value == number } ?: CardNumber.FIVE)
    }

    @Test
    fun `처음에 패를 두 장 보여준다`() {
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(2))
        player.draw(Card(3))
        val actual = player.showInitCards().size
        val expected = 2
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `21보다 작으면 더 받을 수 있도록 true를 반환한다`() {
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(2))
        player.draw(Card(3))
        val actual = player.isPossibleDrawCard()
        assertThat(actual).isTrue
    }

    @Test
    fun `승리 일 경우에는 베팅머니의 1배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(10))
        player.draw(Card(9))
        player.stopDraw()
        val dealer = Dealer()
        dealer.draw(Card(5))
        dealer.draw(Card(3))
        dealer.stopDraw()
        // when
        val actual = player.getProfit(dealer.state)
        val expected = 1000.0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `패배일 경우에는 베팅머니의 -1배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(10))
        player.draw(Card(9))
        player.stopDraw()
        val dealer = Dealer()
        dealer.draw(Card(1))
        dealer.draw(Card(10))
        // when
        val actual = player.getProfit(dealer.state)
        val expected = -1000.0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무승부인 경우에는 베팅머니의 0배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(10))
        player.draw(Card(9))
        player.stopDraw()
        val dealer = Dealer()
        dealer.draw(Card(10))
        dealer.draw(Card(9))
        dealer.stopDraw()
        // when
        val actual = player.getProfit(dealer.state)
        val expected = 0.0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `블랙잭인 경우에는 베팅머니의 2분의3배를 수익으로 반환한다`() {
        // given
        val player = Player(
            Name("scott"),
            BettingMoney(1000),
        )
        player.draw(Card(1))
        player.draw(Card(10))
        val dealer = Dealer()
        dealer.draw(Card(5))
        dealer.draw(Card(3))
        dealer.stopDraw()
        // when
        val actual = player.getProfit(dealer.state)
        val expected = 1500.0
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
