package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.HumanName
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
import blackjack.view.OutputView.printGameSetting
import blackjack.view.OutputView.showPlayerCards

class GameController {
    fun run() {
        val playerGroup = createPlayerGroup()
        val gameDeck = GameDeck()
        val dealer = Dealer()
        val participants = Participants(dealer, playerGroup)

        playGame(gameDeck, participants)

        participants.matchResult()
        OutputView.printMatchResult(participants.dealer, participants.playerGroup)
    }

    private fun playGame(
        gameDeck: GameDeck,
        participants: Participants,
    ) {
        val blackJackGame = BlackJackGame(participants, gameDeck)

        blackJackGame.start(::printGameSetting)
        blackJackGame.runPlayersTurn(
            hitOrStay = ::hitOrStay,
            showPlayerCards = ::showPlayerCards,
        )
        blackJackGame.runDealerTurn(printDealerDrawCard = ::printDealerDrawCard)
        blackJackGame.finish(printEveryCards = ::printEveryCards)
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val participants = PlayerGroup()
        participants.addPlayer(playersNames)
        return participants
    }

    private fun hitOrStay(humanName: HumanName): Boolean {
        val hitOrStay = InputView.askHitOrStay(humanName)
        return when (hitOrStay) {
            YES -> true
            NO -> false
            else -> false
        }
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
    }
}
