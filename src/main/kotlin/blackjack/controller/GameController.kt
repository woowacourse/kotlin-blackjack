package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
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
            hitOrStay = ::askHitOrStay,
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
}
