package controller

import model.card.Deck
import model.participants.Dealer
import model.participants.Game
import model.participants.Hand
import model.participants.ParticipantState
import model.participants.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val players = handleException { readPlayers() }
        val game = Game.of(dealer, players, deck)

        handOut(game)
        handleException { play(game) }

        showResult(game)
    }

    private fun handOut(game: Game) {
        game.handOut()
        OutputView.showGameInit(game)
    }

    private fun play(game: Game) {
        game.playPlayers(
            { player -> InputView.readHitDecision(player) },
            { player -> OutputView.showParticipantHand(player) },
        )
        game.playDealer {
                dealer ->
            OutputView.showDealerHit(dealer)
        }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.ofList(this)
        }
    }

    private fun showResult(game: Game) {
        OutputView.showParticipantsHandWithResult(game)
        judgeResult(game)
    }

    private fun judgeResult(game: Game) {
        val playersResult = game.getPlayersResult()
        val dealerResult = game.getDealerResult()

        OutputView.showResultHeader()
        OutputView.showDealerResult(dealerResult)
        OutputView.showPlayersResult(game.getPlayers(), playersResult)
    }

    private fun <T> handleException(block: () -> T): T =
        runCatching { block() }.getOrElse {
            OutputView.showThrowable(it)
            handleException(block)
        }
}
