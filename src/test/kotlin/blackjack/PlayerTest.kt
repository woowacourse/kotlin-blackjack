package blackjack

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
    fun `플레이어는 카드 한 장을 받을 수 있다`() {
        player.addCard(Card(Shape.SPADE, Number.NINE))
        assertThat(player.cards.size).isEqualTo(1)
    }
}
