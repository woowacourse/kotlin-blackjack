package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `카드의 최종 합을 구한다`() {
        val participant = object : Participant(
            Name("Scott")
        ) {
            override fun isPossibleDrawCard(): Boolean = true
        }
        participant.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        participant.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))

        val actual = participant.curScore()
        val expected = 17
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `버스트 여부를 판단한다`() {
        val participant = object : Participant(
            Name("Scott")
        ) {
            override fun isPossibleDrawCard(): Boolean = true
        }
        participant.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        participant.addCard(Card.of(CardCategory.SPADE, CardNumber.NINE))

        val actual = participant.isBust()
        assertThat(actual).isFalse
    }
}
