package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

class BlackjackGame(private val deck: CardDeck, private val participants: Participants) {
    fun start(
        onFirstDraw: (List<ParticipantCards>) -> Unit,
        onDraw: (Participant) -> Unit,
        onResult: (ParticipantResults) -> Unit
    ) {
        participants.drawAll(deck)
        participants.drawAll(deck)
        onFirstDraw(participants.getFirstOpenCards())

        play(onDraw)

        showResult(onResult)
    }

    private fun play(onDraw: (Participant) -> Unit) {
        participants.takeTurns(deck, onDraw)
    }

    private fun showResult(onResult: (ParticipantResults) -> Unit) {
        onResult(participants.getParticipantResults())
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        fun blackjackScore(): Int = BLACKJACK_SCORE
    }
}
