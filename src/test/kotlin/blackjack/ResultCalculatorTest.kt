package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Number
import blackjack.model.Player
import blackjack.model.ResultCalculator
import blackjack.model.ResultType
import blackjack.model.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultCalculatorTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("미플")
    }

    @Test
    fun `카드 총 합이 21을 넘고 ACE가 존재하면 점수 조정을 진행한다`() {
        player.addCard(Card(Shape.SPADE, Number.ACE))
        player.addCard(Card(Shape.CLOVER, Number.ACE))
        player.addCard(Card(Shape.DIAMOND, Number.ACE))
        val expect = 13

        val actual = ResultCalculator.adjustScore(player.cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        val dealer = Dealer()
        val player = Player("플레이어")
        dealer.addCard(Card(Shape.CLOVER, Number.NINE))
        dealer.addCard(Card(Shape.CLOVER, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.SEVEN))
        val expect = ResultType.LOSS

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }
}
