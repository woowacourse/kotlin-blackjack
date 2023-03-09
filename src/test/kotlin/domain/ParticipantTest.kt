package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    private fun Cards(vararg cards: Int): Cards {
        return Cards(
            cards.map { number ->
                Card.of(
                    CardCategory.CLOVER,
                    CardNumber.values().find { it.value == number } ?: CardNumber.FIVE,
                )
            },
        )
    }

    @Test
    fun `카드의 최종 합을 구한다`() {
        // given
        val participant = object : Participant(
            Name("Scott"),
            Cards(
                8,
                9,
            ),
        ) {
            override fun showInitCards(): List<Card> {
                return cards.list.take(2)
            }

            override fun isPossibleDrawCard(): Boolean {
                return true
            }
        }

        // when
        val actual = participant.getScore()
        val expected = Score(Cards(10, 7))

        // then
        assertThat(actual.getValue()).isEqualTo(expected.getValue())
    }
}
