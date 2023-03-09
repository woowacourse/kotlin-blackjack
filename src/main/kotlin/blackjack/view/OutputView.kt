package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantProfit
import blackjack.domain.data.ParticipantScore
import blackjack.domain.result.PlayerResults

interface OutputView {
    fun printFirstOpenCards(participantsCards: List<ParticipantCards>)
    fun printCards(name: String, cards: List<Card>)
    fun printDealerHit()
    fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: PlayerResults,
        calculateProfits: List<ParticipantProfit>
    )
}
