package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Number
import blackjack.model.Player
import blackjack.model.ResultType
import blackjack.model.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTypeTest {
    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        val dealer = Dealer()
        val player = Player("플레이어")
        dealer.addCards(listOf(Card(Shape.CLOVER, Number.NINE), Card(Shape.CLOVER, Number.EIGHT)))
        player.addCards(listOf(Card(Shape.HEART, Number.EIGHT), Card(Shape.HEART, Number.SEVEN)))
        val expect = ResultType.LOSS

        val actual = ResultType.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)

    }
}