package blackjack.domain.listener

import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.result.GameResult

interface BlackjackEventListener {
    fun onStartDrawn(participants: Participants)
    fun onFirstDrawn(participant: Participant)
    fun onDrawnMore(participant: Participant)
    fun onEndGame(gameResults: List<GameResult>)
}
