package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackTest {
    @Test
    fun `모든 플레이어의 승패 결과를 반환한다`() {
        val blackJack = BlackJackBuilder {
            cardDeck(TestCardGenerator(mutableListOf(9, 1, 14, 27, 12, 13, 2, 8)))
            participants {
                dealer()
                players(listOf("buna", "glo", "bandal"))
            }
        }
        blackJack.drawAll()
        blackJack.drawAll()

        val results = blackJack.getGameResults()

        assertAll(
            { assertThat(results["buna"]).isEqualTo("승") },
            { assertThat(results["glo"]).isEqualTo("패") },
            { assertThat(results["bandal"]).isEqualTo("무") }
        )
    }

    class TestCardGenerator(private val numbers: MutableList<Int>) : CardGenerator {
        override fun generate(): Card = Card.of(numbers.removeAt(0))
    }
}
