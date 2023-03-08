package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.CardPack
import blackjack.view.OutputView

class BlackJackController(
    private val blackJackGame: BlackJackGame = BlackJackGame(CardPack())
) {
    fun run() {
        runCatching {
            blackJackGame.entryPlayers()
            blackJackGame.showDividingCards()
            blackJackGame.drawAdditionalCards()
            blackJackGame.drawAdditionalCardForDealer()
            blackJackGame.showFinalCards()
            blackJackGame.judgeGameResults()
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }
}
