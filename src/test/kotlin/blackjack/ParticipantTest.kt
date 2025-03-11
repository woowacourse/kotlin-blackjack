package blackjack

import blackjack.model.Card
import blackjack.model.Number
import blackjack.model.Participant
import blackjack.model.ResultCalculator
import blackjack.model.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantTest {
    private lateinit var person: Participant

    @BeforeEach
    fun setUp() {
        person =
            object : Participant() {
                override fun isBust(): Boolean {
                    return true
                }
            }
    }

    @Test
    fun `참여자는 카드 한 장을 받을 수 있다`() {
        person.addCard(Card(Shape.SPADE, Number.NINE))
        assertThat(person.cards.size).isEqualTo(1)
    }

    @Test
    fun `참여자 카드의 총 합을 계산한다`() {
        person.addCard(Card(Shape.SPADE, Number.NINE))
        person.addCard(Card(Shape.SPADE, Number.SEVEN))
        val expect = 16
        val actual = ResultCalculator.calculateTotalScore(person.cards)

        assertThat(actual).isEqualTo(expect)
    }
}
