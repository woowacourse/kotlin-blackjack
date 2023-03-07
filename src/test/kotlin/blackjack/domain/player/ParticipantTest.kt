package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
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
    fun `딜러의 카드 합을 받아 자신의 승패를 결정한다`() {
        // given
        val participant = Participant("aaa")
        participant.addCard(Card(CardNumber.ACE, CardShape.CLOVER))
        participant.addCard(Card(CardNumber.JACK, CardShape.HEART))
        val dealerSum = 15

        // when
        participant.updateResult(dealerSum)

        // then
        assertThat(participant.result).isEqualTo(Result.WIN)
    }
}
