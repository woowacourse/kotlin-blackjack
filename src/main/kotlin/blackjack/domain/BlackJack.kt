package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun drawAll() {
        participants.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> = participants.getFirstOpenCards()

    fun play(onDraw: (Participant) -> Unit) {
        participants.takeTurns(deck, onDraw)
    }

    fun getCards(): List<ParticipantCards> = participants.getCards()

    fun getTotalScores(): List<ParticipantScore> = participants.getTotalScores()

    fun getParticipantResults(): ParticipantResults = participants.getParticipantResults()

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
