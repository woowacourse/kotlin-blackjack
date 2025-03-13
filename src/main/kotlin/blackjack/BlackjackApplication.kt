package blackjack

import blackjack.controller.BlackjackController

class BlackjackApplication(
    private val controller: BlackjackController = BlackjackController(),
) {
    fun play() {
        controller.run {
            initGame()
            dealCards()
            playPlayerTurn()
            playDealerTurn()
            showParticipantsSummary()
            setResult()
        }
    }
}
