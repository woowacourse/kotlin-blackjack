package controller

import model.Game
import model.card.Deck
import model.participants.Dealer
import model.participants.Money
import model.participants.ParticipantState
import model.participants.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(ParticipantState.None())
        val players = readPlayers()

        with(Game.create(dealer, players, deck)) {
            handOut(this)
            play(this)
            showResult(this)
            showProfitResult(this)
        }
    }

    private fun showProfitResult(game: Game) {
        game.getProfitResult().apply {
            OutputView.showProfitResult(this)
        }
    }
    private fun handOut(game: Game) {
        game.handOut()
        OutputView.showGameInit(game)
    }

    private fun play(game: Game) {
        retryWhileException {
            playPlayers(game)
            playDealer(game)
        }
    }

    private fun playPlayers(game: Game) {
        game.playPlayers(
            { player -> InputView.readHitDecision(player) },
            { player -> OutputView.showParticipantHand(player) },
        )
    }

    private fun playDealer(game: Game) {
        game.playDealer {
                dealer ->
            OutputView.showDealerHit(dealer)
        }
    }

    private fun readPlayers(): Players {
        val playersName = retryWhileException { readPlayersName() }
        val playersMoney = retryWhileException { readPlayersMoneyByNames(playersName) }
        return Players.ofList(playersName, playersMoney)
    }

    private fun readPlayersName(): List<String> {
        return InputView.readPlayerNames()
    }

    private fun readPlayersMoneyByNames(playerNames: List<String>): List<Money> {
        return playerNames.map { playerName ->
            readPlayerMoneyByName(playerName)
        }.toList()
    }

    private fun readPlayerMoneyByName(playerName: String): Money {
        return InputView.readPlayerMoneyByName(playerName)
    }

    private fun showResult(game: Game) {
        OutputView.showParticipantsHandWithResult(game)
        showWinLossTieInfo(game)
    }

    private fun showWinLossTieInfo(game: Game) {
        val playersResult = game.getPlayersResult()
        val dealerResult = game.getDealerResult()

        OutputView.showResultHeader()
        OutputView.showDealerResult(dealerResult)
        OutputView.showPlayersResult(game.getPlayers(), playersResult)
    }

    private fun <T> retryWhileException(block: () -> T): T =
        runCatching { block() }.getOrElse {
            OutputView.showThrowable(it)
            retryWhileException(block)
        }
}
