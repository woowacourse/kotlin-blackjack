package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {

    @Test
    fun `딜러와 참가자 각각의 카드들을 받아 참가자의 승패를 판단한다`() {
        val dealerCards: Cards = Cards()
        dealerCards.addCard(Card(CardNumber.JACK, CardShape.CLOVER))
        dealerCards.addCard(Card(CardNumber.ONE, CardShape.CLOVER))
        val participantCards: Cards = Cards()
        participantCards.addCard(Card(CardNumber.JACK, CardShape.CLOVER))
        participantCards.addCard(Card(CardNumber.ONE, CardShape.CLOVER))

        assertThat(Score.getParticipantResult(dealerCards, participantCards)).isEqualTo(Result.DRAW)
    }

    @Test
    fun `결과를 받아 그와 상반되는 결과를 돌려준다`() {
        assertThat(Score.reversResult(Result.WIN)).isEqualTo(Result.LOSE)
    }
}

// fun valueOf(dealerSum: Int, participantSum: Int): Result {
//    when {
//        ((dealerSum > Cards.MAX_SUM_NUMBER) and (participantSum > Cards.MAX_SUM_NUMBER)) -> return DRAW
//        (participantSum > Cards.MAX_SUM_NUMBER) -> return LOSE
//        (dealerSum > Cards.MAX_SUM_NUMBER) -> return WIN
//        (dealerSum > participantSum) -> return LOSE
//        (dealerSum == participantSum) -> return DRAW
//    }
//    return WIN
// }
//
// fun reverse(result: Result) = when (result) {
//    WIN -> LOSE
//    LOSE -> WIN
//    else -> DRAW
// }
