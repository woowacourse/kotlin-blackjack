package blackjack

import blackjack.controller.BlackjackController

fun main() {
    BlackjackApplication().play()
}

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
