package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeParticipant : Participant("fake") {
    override fun getInitialCard(): List<Card> = cards
}

class ParticipantTest {
    private lateinit var participant: FakeParticipant

    @BeforeEach
    fun setUp() {
        participant = FakeParticipant()
    }

    @Test
    fun `참여자는 카드가 2장이고 카드 합이 21이면 블랙잭이다`() {
        participant.addCard(Card(Shape.SPADE, CardNumber.ACE))
        participant.addCard(Card(Shape.CLOVER, CardNumber.JACK))
        val expect = true

        val actual = participant.isBlackjack()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `참여자는 카드 한 장을 받을 수 있다`() {
        participant.addCard(Card(Shape.SPADE, CardNumber.NINE))
        assertThat(participant.cards.size).isEqualTo(1)
    }

    @Test
    fun `참여자는 카드의 총 합을 계산한다`() {
        participant.addCard(Card(Shape.SPADE, CardNumber.NINE))
        participant.addCard(Card(Shape.SPADE, CardNumber.SEVEN))
        val expect = 16
        val actual = participant.score

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `참여자는 카드 총 합이 21을 넘으면 Bust한다`() {
        participant.addCard(Card(Shape.SPADE, CardNumber.NINE))
        participant.addCard(Card(Shape.CLOVER, CardNumber.QUEEN))
        participant.addCard(Card(Shape.CLOVER, CardNumber.SEVEN))
        val expect = true

        val actual = participant.isBust()

        assertThat(actual).isEqualTo(expect)
    }
}
