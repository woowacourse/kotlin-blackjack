package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

class BlackJack(private val deck: CardDeck, private val participants: Participants) {
    fun start(onFirstDraw: (List<ParticipantCards>) -> Unit, onDraw: (Participant) -> Unit) {
        participants.drawAll(deck)
        participants.drawAll(deck)
        onFirstDraw(participants.getFirstOpenCards())

        play(onDraw)
    }

    private fun play(onDraw: (Participant) -> Unit) {
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
