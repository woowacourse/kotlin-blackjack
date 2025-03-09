package blackjack

import blackjack.model.Participant
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantTest {
    private lateinit var participant: Participant

    @BeforeEach
    fun setUp() {
        participant =
            object : Participant("참가자이름") {
                override fun isBust(): Boolean {
                    return true
                }
            }
    }

    @Test
    fun `참여자는 카드 한 장을 받을 수 있다`() {
        participant.addCard(Card(Shape.SPADE, Number.NINE))
        assertThat(participant.cards.size).isEqualTo(1)
    }

    @Test
    fun `참여자 카드의 총 합을 계산한다`() {
        participant.addCard(Card(Shape.SPADE, Number.NINE))
        participant.addCard(Card(Shape.SPADE, Number.SEVEN))
        val expect = 16
        val actual = participant.calculateTotalScore()

        assertThat(actual).isEqualTo(expect)
    }
}
