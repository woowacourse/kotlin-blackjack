package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `카드의 최종 합을 구한다`() {
        val participant = object : Participant(
            Name("Scott"),
            Cards(
                listOf(
                    Card(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card(CardCategory.SPADE, CardNumber.NINE)
                )
            )
        ) {
            override fun showInitCards(): List<Card> {
                return cards.cards.take(2)
            }

            override fun isPossibleDrawCard(): Boolean {
                return true
            }
        }

        val actual = participant.getSumStateResult()
        val expected = Cards.State.NoBurst(17)
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `버스트 여부를 판단한다`() {
        val participant = object : Participant(
            Name("Scott"),
            Cards(
                listOf(
                    Card(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card(CardCategory.SPADE, CardNumber.NINE)
                )
            )
        ) {
            override fun showInitCards(): List<Card> {
                return cards.cards.take(2)
            }

            override fun isPossibleDrawCard(): Boolean {
                return true
            }
        }

        val actual = participant.isBurst()
        assertThat(actual).isFalse
    }
}
