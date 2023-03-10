package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore

interface OutputView {
    fun printFirstOpenCards(participantsCards: List<ParticipantCards>)
    fun printCards(name: String, cards: List<Card>)
    fun printDealerHit(name: String)
    fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: ParticipantResults
    )
}
