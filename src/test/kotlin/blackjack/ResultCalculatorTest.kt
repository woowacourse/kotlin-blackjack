package blackjack

import blackjack.model.ResultCalculator
import blackjack.model.ResultType
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
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
    fun `ACE를 11로 계산할 수 있을 때 11로 처리한다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.TWO))
        player.addCard(Card(Shape.CLOVER, CardNumber.THREE))
        player.addCard(Card(Shape.DIAMOND, CardNumber.ACE))
        val expect = 16

        val actual = ResultCalculator.calculate(player.cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `ACE를 11로 계산할 수 없을 때 1로 처리한다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        player.addCard(Card(Shape.CLOVER, CardNumber.TEN))
        player.addCard(Card(Shape.DIAMOND, CardNumber.ACE))
        val expect = 21

        val actual = ResultCalculator.calculate(player.cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 진다`() {
        val dealer = Dealer()
        val player = Player("플레이어")
        dealer.addCard(Card(Shape.CLOVER, CardNumber.NINE))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.SEVEN))
        val expect = ResultType.LOSS

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }
}
