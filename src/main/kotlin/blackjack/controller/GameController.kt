package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.GameState
import blackjack.model.GameState.End
import blackjack.model.GameState.Play
import blackjack.model.Participants
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
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
            GameDeck.resetCurrentDeck()
            startGame(participants = participants)
        }.onFailure { e ->
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

    private fun startGame(participants: Participants) {
        playGame(participants = participants).onSuccess {
            OutputView.printMatchResult(dealer = participants.dealer, playerGroup = participants.playerGroup)
            gameState = End
        }.onFailure { e ->
            handReset(participants = participants)
            OutputView.printError(e)
        }
    }

    private fun playGame(participants: Participants): Result<Unit> {
        return runCatching {
            with(participants) {
                start(printGameSetting = ::printGameSetting)
                runPlayersTurn(hitOrStay = ::askHitOrStay, showPlayerCards = ::showPlayerCards)
                runDealerTurn(printDealerDrawCard = ::printDealerDrawCard)
                finish(printEveryCards = ::printEveryCards)
            }
        }
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val playerGroup = PlayerGroup()
        playerGroup.addPlayer(playerNames = playersNames)
        return playerGroup
    }

    private fun handReset(participants: Participants) {
        participants.playerGroup.players.forEach { player -> player.hand.reset() }
        participants.dealer.hand.reset()
    }
}
