package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.view.OutputView

class FakeOutputView : OutputView {
    lateinit var firstOpenCards: List<ParticipantCards>
    private val cards: MutableList<Pair<String, List<Card>>> = mutableListOf()
    var dealerHitCount: Int = 0
    lateinit var participantCards: List<ParticipantCards>
    lateinit var totalScores: List<ParticipantScore>
    lateinit var results: ParticipantResults

    override fun printFirstOpenCards(participantsCards: List<ParticipantCards>) {
        firstOpenCards = participantsCards
    }

    override fun printCards(name: String, cards: List<Card>) {
        this.cards.add(name to cards)
    }

    override fun printDealerHit(name: String) {
        dealerHitCount++
    }

    override fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: ParticipantResults
    ) {
        participantCards = cards
        this.totalScores = totalScores
        this.results = results
    }
}
