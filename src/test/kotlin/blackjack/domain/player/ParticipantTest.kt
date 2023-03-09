package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {

    @Test
    fun `카드를 발급 받을 수 있는지 확인한다`() {
        val participant = Participant("aaa")
        participant.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = participant.canHit()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `승패 결과를 결정하여 상태를 변경한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.ONE, CardShape.CLOVER))
        dealer.addCard(Card(CardNumber.JACK, CardShape.HEART))
        val participant = Participant("aaa")
        participant.addCard(Card(CardNumber.ONE, CardShape.CLOVER))
        participant.addCard(Card(CardNumber.JACK, CardShape.HEART))

        participant.updateGameResult(dealer.getGameResult(dealer.cards.sum()))

        assertThat(participant.gameResult).isEqualTo(GameResult.DRAW)
    }
}
