import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점을 넘으면 카드를 뽑는데 실패한다`() {
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
            player.drawCard()
        ).isEqualTo(DrawResult.Failure)
    }
}
