package blackjack

import blackjack.model.Card
import blackjack.model.Number
import blackjack.model.Player
import blackjack.model.Shape
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
    fun `플레이어는 카드 총 합이 21을 넘으면 isBust를 true를 반환한다`() {
        player.addCard(Card(Shape.SPADE, Number.NINE))
        player.addCard(Card(Shape.CLOVER, Number.QUEEN))
        player.addCard(Card(Shape.CLOVER, Number.SEVEN))
        val expect = true

        val actual = player.isBust()

        assertThat(actual).isEqualTo(expect)
    }
}
