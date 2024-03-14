package blackjack.model.domain

import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank
import blackjack.model.entitiy.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantInfoTest {

    @Test
    fun `총합이 기준값 이하일 때 ACE 값이 변경되어 계산되는지`() {
        val participantInfo = ParticipantInfo(
            "해음",
            cards = setOf(
                Card(Shape.DIAMOND, CardRank.TEN),
                Card(Shape.DIAMOND, CardRank.ACE),
            ),
        )
        val actualResult = participantInfo.sumCardValues()

        assertThat(actualResult).isEqualTo(21)
    }
}
