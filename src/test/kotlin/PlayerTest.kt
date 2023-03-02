import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점을 넘으면 false를 반환한다`() {
        val player = Player(
            "우기",
            Cards(
                listOf(
                    Card(CardNumber.K, Shape.HEART),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )
        player.cards.draw(Card(CardNumber.TWO, Shape.CLOVER))

        assertThat(
            player.isPossibleToDraw()
        ).isFalse
    }
}
