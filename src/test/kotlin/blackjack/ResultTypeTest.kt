package blackjack

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.ResultType
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTypeTest {
    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        val dealer = Dealer()
        val player = Player("플레이어")

        dealer.addCard(Card(Shape.CLOVER, Number.NINE))
        dealer.addCard(Card(Shape.CLOVER, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.SEVEN))
        val expect = ResultType.LOSS

        val actual = ResultType.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }
}
