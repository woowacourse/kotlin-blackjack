package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `카드의 최종 합을 구한다`() {
        // given
        val participant = object : Participant(
            Name("Scott"),
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
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
        val expected = Score(17, false)

        // then
        assertThat(actual.getValue()).isEqualTo(expected.getValue())
    }
}
