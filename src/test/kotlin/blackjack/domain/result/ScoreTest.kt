package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ScoreTest {

    @Test
    fun `딜러와 참가자 각각의 카드들을 받아 참가자의 승패를 판단한다`() {
        val dealerCards: Cards = Cards()
        dealerCards.addCard(Card(CardNumber.JACK, CardShape.CLOVER))
        dealerCards.addCard(Card(CardNumber.ONE, CardShape.CLOVER))
        val participantCards: Cards = Cards()
        participantCards.addCard(Card(CardNumber.ONE, CardShape.CLOVER))
        participantCards.addCard(Card(CardNumber.ONE, CardShape.CLOVER))

        assertThat(Score.getParticipantResult(dealerCards, participantCards)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `결과를 받아 그와 상반되는 결과를 돌려준다`() {
        assertAll(
            { assertThat(Score.reversResult(Result.WIN)).isEqualTo(Result.LOSE) },
            { assertThat(Score.reversResult(Result.LOSE)).isEqualTo(Result.WIN) },
            { assertThat(Score.reversResult(Result.DRAW)).isEqualTo(Result.DRAW) }
        )
    }
}
