package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
import blackjack.view.InputView.inputBettingMoney
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
import blackjack.view.OutputView.printGameSetting
import blackjack.view.OutputView.showPlayerCards

class GameController {
    fun run() {
        val playerGroup = createPlayerGroup()
        val dealer = Dealer()
        val participants = Participants(dealer, playerGroup)

        playGame(participants)

        participants.matchResult()
        OutputView.printMatchResult(participants.dealer.gameResult, participants.playerGroup)
    }

    private fun playGame(participants: Participants) {
        val gameDeck = GameDeck()
        val blackJackGame = BlackJackGame(participants, gameDeck)

        blackJackGame.betMoney(::inputBettingMoney)
        blackJackGame.start(::printGameSetting)
        blackJackGame.runPlayersTurn(
            hitOrStay = ::askHitOrStay,
            getPlayerInfo = ::showPlayerCards,
        )
        blackJackGame.runDealerTurn(alertCountOfDraw = ::printDealerDrawCard)
        blackJackGame.finish(printEveryCards = ::printEveryCards)
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val playerGroup = PlayerGroup()
        playerGroup.addPlayer(playersNames)
        return playerGroup
    }
}
