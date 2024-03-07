package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.GameInfo
import blackjack.model.Judge
import blackjack.model.PickingState
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val cardDeck: CardDeck,
) {
    fun startGame() {
        val playerNames = getPlayerNames()
        val dealer = Dealer(playerNames.toSet())
        val players =
            List(playerNames.size) { Player(GameInfo(playerNames[it]), onInputDecision = { askPlayerHit(playerNames[it]) }) }
        dealer.drawCard { cardDeck.pick() }
        initializePlayerCards(players)
        displayInitializedCards(dealer.gameInfo, players.map { it.gameInfo })

        players.forEach { player ->
            drawSinglePlayer(player)
        }

        println()

        while (true) {
            val pickingState = dealer.drawCard { cardDeck.pick() }
            when (pickingState) {
                PickingState.CONTINUE -> {
                    OutputView.printDealerHit(dealer.gameInfo)
                }
                PickingState.STOP -> break
            }
        }

        val dealerStat = dealer.gameInfo
        val playerStat = players.map { it.gameInfo }

        val judge = Judge(dealerStat, playerStat)

        OutputView.printFinalCards(dealerStat, playerStat)
        OutputView.printResult(judge.getDealerResult(), judge.getPlayerResults(), playerStat, dealerStat)
    }

    private fun drawSinglePlayer(player: Player) {
        while (true) {
            val pickingState = player.drawCard { cardDeck.pick() }
            OutputView.printSinglePlayerCards(gameInfo = player.gameInfo)
            when (pickingState) {
                PickingState.CONTINUE -> continue
                PickingState.STOP -> break
            }
        }
    }

    private fun askPlayerHit(playerName: String): String = InputView.readContinueInput(playerName) ?: askPlayerHit(playerName)

    private fun displayInitializedCards(
        dealerInfo: GameInfo,
        playersInfo: List<GameInfo>,
    ) {
        OutputView.printInitialStats(dealerInfo, playersInfo)
    }

    private fun initializePlayerCards(players: List<Player>) {
        players.forEach { player ->
            player.initializeCards { cardDeck.pick() }
        }
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            InputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }
}
