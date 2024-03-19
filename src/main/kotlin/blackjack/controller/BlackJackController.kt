package blackjack.controller

import blackjack.Dealer
import blackjack.Players
import blackjack.model.domain.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun startGame() {
        initPlayers()
        initializeParticipantsCards()
        playRound()
        displayResult()
    }

    private fun initPlayers() {
        players.readNames(getPlayerNames(), ::askPlayerHit)
        players.readBatingAmounts(::getBatingAmount)
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
        dealer.drawCard { CardDeck.pick() }
        players.initPlayersCard { CardDeck.pick() }
        displayInitializedCards()
    }

    private fun playRound() {
        with(OutputView) {
            players.drawUntilPlayersSatisfaction(
                CardDeck::pick,
                ::printSinglePlayerCards,
            )

            printNewLine()

            dealer.drawUntilSatisfaction(
                generateCard = CardDeck::pick,
                printCards = ::printDealerHit,
            )
        }
    }

    private fun displayResult() {
        with(OutputView) {
            printFinalCards(players, dealer)
            printResult(players, dealer)
        }
    }

    private fun askPlayerHit(playerName: String): String =
        InputView.readContinueInput(playerName) ?: askPlayerHit(playerName)

    private fun displayInitializedCards() {
        OutputView.printInitialStats(players, dealer)
    }
}
