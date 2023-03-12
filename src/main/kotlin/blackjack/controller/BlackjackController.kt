package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.BlackjackEventListener
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.result.GameResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        Blackjack(deck = CardDeck(), players = InputView.inputPlayers()).start(object : BlackjackEventListener {
            override fun onStartDrawn(participants: Participants) {
                OutputView.printFirstDrawnMessage(participants)
            }

            override fun onFirstDrawn(participant: Participant) {
                OutputView.printFirstOpenCards(participant)
            }

            override fun onDrawnMore(participant: Participant) {
                OutputView.printAllCards(participant)
            }

            override fun onEndGame(gameResults: List<GameResult>) {
                OutputView.printBlackjackResult(gameResults)
            }
        })
    }
}
