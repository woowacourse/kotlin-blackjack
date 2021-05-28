package controller

import domain.BlackJackGame
import domain.player.Player
import view.InputView
import view.OutputView
import view.YesOrNo

class GameController {

    val players = initPlayers()
    val gameService = BlackJackGame(players)

    fun play() {
        giveInitialCards()
        giveAdditionalCards()
        printResult()
    }

    private fun initPlayers(): List<Player> {
        val names: List<String> = InputView.inputPlayerNames()
        return names.map { Player(it, InputView.inputBettingMoney(it)) }
    }

    private fun giveInitialCards(){
        val result = gameService.giveInitialCards()
        OutputView.printResult(result)
    }

    private fun giveAdditionalCards() {
        for(player in players){
            askDrawMore(player)
        }
        gameService.giveDealerAdditionalCards()
    }

    private fun askDrawMore(player: Player) {
        while (InputView.askDrawMore(player) == YesOrNo.YES) {
            val result = gameService.givePlayerCard(player)
            OutputView.printResult(result)
        }
    }

    private fun printResult(){
        val result = gameService.gameResult()
        OutputView.printResult(result)
    }
}