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
    fun `플레이어는 카드 총 합이 21을 넘으면 true를 반환한다`() {
        player.addCards(
            listOf(
                Card(Shape.SPADE, Number.NINE),
                Card(Shape.CLOVER, Number.QUEEN),
                Card(Shape.CLOVER, Number.SEVEN),
            ),
        )
        val expect = true

        val actual = player.isBust()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `카드 총 합이 21을 넘고 ACE가 존재하면 점수 조정을 진행한다`() {
        player.addCards(
            listOf(
                Card(Shape.SPADE, Number.ACE),
                Card(Shape.CLOVER, Number.ACE),
                Card(Shape.DIAMOND, Number.ACE),
            ),
        )
        val expect = 13

        val actual = player.adjustScore()

        assertThat(actual).isEqualTo(expect)
    }
}
