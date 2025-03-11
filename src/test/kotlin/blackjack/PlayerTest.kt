package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("미플")
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("미플")
    }

    @Test
    fun `플레이어가 처음 공개하는 카드는 2장이다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        player.addCard(Card(Shape.CLOVER, CardNumber.QUEEN))
        val expect = 2

        val actual = player.getInitialCard().size

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어는 카드 총 합이 21을 넘으면 isBust를 true를 반환한다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        player.addCard(Card(Shape.CLOVER, CardNumber.QUEEN))
        player.addCard(Card(Shape.CLOVER, CardNumber.SEVEN))
        val expect = true

        val actual = player.isBust()

        assertThat(actual).isEqualTo(expect)
    }
}
