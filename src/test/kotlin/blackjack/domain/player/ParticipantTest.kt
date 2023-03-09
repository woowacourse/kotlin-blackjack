package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {

    @Test
    fun `카드를 발급 받을 수 있는지 확인한다`() {
        val participant = Participant("aaa")
        participant.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = participant.checkProvideCardPossible()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `플레이어1은 Burst 딜러는 17일때, 플레이어1의 승패를 계산하면, 패배이다`() {
        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )
        val player1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.SEVEN, CardShape.HEART),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual = player1.calculateResult(dealer)

        // then
        assertThat(actual.result).isEqualTo(Pair("aaa", Result.LOSE))
    }

    @Test
    fun `플레이어1은 17 딜러는 Burst 일때, 플레이어1의 승패를 계산하면, 승리이다`() {
        // given
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.QUEEN, CardShape.DIAMOND),
                    Card(CardNumber.KING, CardShape.DIAMOND),
                    Card(CardNumber.THREE, CardShape.SPADE)
                )
            )
        )
        val player1 = Participant(
            "aaa",
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    Card(CardNumber.NINE, CardShape.DIAMOND)
                )
            )
        )

        // when
        val actual = player1.calculateResult(dealer)

        // then
        assertThat(actual.result).isEqualTo(Pair("aaa", Result.WIN))
    }
}
