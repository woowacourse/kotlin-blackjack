package blackjack.controller

import blackjack.model.action.Action
import blackjack.model.dispatcher.Dispatcher
import blackjack.model.domain.CardDeck
import blackjack.model.store.Store
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val dispatcher: Dispatcher,
    private val store: Store,
) {
    fun startGame() {
        initPlayers()
        initializeParticipantsCards()
        playRound()
        displayResult()
    }

    private fun initPlayers() {
        dispatcher.dispatch(Action.ReadNames(getPlayerNames()))
        dispatcher.dispatch(Action.ReadBatingAmount(::getBatingAmount))
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            InputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }

    private fun getBatingAmount(name: String): Int {
        return runCatching {
            InputView.readPlayerBatingAmount(name) ?: getBatingAmount(name)
        }.onFailure {
            println(it.message)
            return getBatingAmount(name)
        }.getOrThrow()
    }

    private fun initializeParticipantsCards() {
        dispatcher.dispatch(Action.InitDealerCard { CardDeck.pick() })
        dispatcher.dispatch(Action.InitPlayersCard { CardDeck.pick() })
        displayInitializedCards()
    }

    private fun playRound() {
        with(OutputView) {
            dispatcher.dispatch(
                Action.DrawUntilPlayersSatisfaction(
                    CardDeck::pick,
                    ::printSinglePlayerCards,
                    ::askPlayerHit,
                ),
            )
            printNewLine()
            dispatcher.dispatch(
                action = Action.DrawUntilDealerSatisfaction(
                    CardDeck::pick,
                    ::printDealerHit,
                ),
            )
        }
    }

    private fun displayResult() {
        dispatcher.dispatch(Action.FindWinner(store.dealerInfo))

        with(OutputView) {
            printFinalCards(store.playersInfo, store.dealerInfo)
            printResult(store.playersInfo, store.dealerInfo)
        }
    }

    private fun askPlayerHit(playerName: String): String =
        InputView.readContinueInput(playerName) ?: askPlayerHit(playerName)

    private fun displayInitializedCards() {
        OutputView.printInitialStats(store.playersInfo, store.dealerInfo)
    }
}
