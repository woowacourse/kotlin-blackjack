package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameState
import blackjack.model.GameState.End
import blackjack.model.GameState.Play
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
import blackjack.view.InputView.inputPlayerBettingAmount
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
import blackjack.view.OutputView.printGameResult
import blackjack.view.OutputView.printGameSetting
import blackjack.view.OutputView.showPlayerCards

class GameController(private var gameState: GameState = Play) {
    fun run() {
        while (true) {
            when (gameState) {
                Play -> play()
                End -> break
            }
        }
    }

    private fun play() {
        createParticipants().onSuccess { participants ->
            playGame(participants)
        }.onFailure { e ->
            OutputView.printError(e)
        }
    }

    private fun playGame(participants: Participants) {
        participants.startGame(
            inputPlayerBettingAmount = ::inputPlayerBettingAmount,
            printGameSetting = ::printGameSetting,
            askHitOrStay = ::askHitOrStay,
            showPlayerCards = ::showPlayerCards,
            printDealerDrawCard = ::printDealerDrawCard,
            printEveryCards = ::printEveryCards,
        ).onSuccess { profitResults ->
            printGameResult(profitResults)
            gameState = End
        }.onFailure { e ->
            participants.resetHand()
            OutputView.printError(e)
        }
    }

    private fun createParticipants(): Result<Participants> {
        return runCatching {
            val playerGroup = createPlayerGroup()
            val dealer = Dealer()
            Participants(dealer = dealer, playerGroup = playerGroup)
        }
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val playerGroup = PlayerGroup()
        playerGroup.addPlayer(playerNames = playersNames)
        return playerGroup
    }
}
