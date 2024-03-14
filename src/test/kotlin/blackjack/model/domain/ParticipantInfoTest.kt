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
            cards = listOf(
                Card(Shape.DIAMOND, CardRank.TEN),
                Card(Shape.DIAMOND, CardRank.ACE),
            ),
        )
        val actualResult = participantInfo.sumCardValues()

        assertThat(actualResult).isEqualTo(21)
    }

    @Test
    fun `플레이어 카드가 2장, 블랙잭이고 딜러가 블랙잭이 아닐때 플레이어 배팅 금액에 추가 비율이 곱해지는지`() {
        val participantInfo = ParticipantInfo(
            name = "해음",
            batingAmount = 1000,
            cards = listOf(
                Card(Shape.DIAMOND, CardRank.TEN),
                Card(Shape.DIAMOND, CardRank.ACE),
            ),
        )
        val dealerInfo = ParticipantInfo(
            "딜러",
            cards = listOf(
                Card(Shape.HEART, CardRank.TEN),
                Card(Shape.HEART, CardRank.TEN),
            ),
        )

        val actual = participantInfo.findWinner(dealerInfo)
        val expected = 1500

        println(participantInfo.cards.take(2).sumOf { it.cardRank.value })

        assertThat(actual).isEqualTo(expected)
    }
}
