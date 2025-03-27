package blackjack.controller

import blackjack.model.BettingTable
import blackjack.model.BlackjackGame
import blackjack.model.PlayerBehavior
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.model.result.PlayerGameResult
import blackjack.model.state.Finish
import blackjack.view.BlackjackView

class BlackjackController(
    private val blackjackView: BlackjackView,
) {
    fun run() {
        val players = blackjackView.startGame()
        val dealer = Dealer()
        val bettingTables = blackjackView.settingBettingAmount(players)
        val blackjackGame = BlackjackGame(players, dealer)
        startGame(blackjackGame, dealer, players)
        playersTurn(players, blackjackGame)
        dealerTurn(blackjackGame)
        val profitRates = playersProfitRate(players, dealer)
        result(dealer, players, bettingTables, profitRates)
    }

    private fun playersProfitRate(
        players: Players,
        dealer: Dealer,
    ): MutableList<Float> {
        val profitRates = mutableListOf<Float>()
        players.value.forEach { player ->
            val playerGameResult = PlayerGameResult(dealer, player)
            val profitRate = playerGameResult.playerProfitRate()
            profitRates.add(profitRate)
        }
        return profitRates
    }

    private fun startGame(
        blackjackGame: BlackjackGame,
        dealer: Dealer,
        players: Players,
    ) {
        blackjackGame.firstTurn()
        blackjackView.giveCardsParticipants(dealer, players)
    }

    private fun playersTurn(
        players: Players,
        blackjackGame: BlackjackGame,
    ) {
        players.value.forEach { player ->
            while (player.state !is Finish) {
                val playerBehavior = blackjackView.getPlayerBehavior(player)
                if (playerBehavior == PlayerBehavior.HIT) {
                    blackjackGame.playerTurn(player)
                } else {
                    player.stop()
                    break
                }
            }
            blackjackView.showHands(player)
        }
    }

    private fun dealerTurn(blackjackGame: BlackjackGame) {
        if (blackjackGame.dealerTurn()) {
            blackjackView.showIsGetDealerCards()
        }
    }

    private fun result(
        dealer: Dealer,
        players: Players,
        bettingTables: List<BettingTable>,
        profitRates: List<Float>,
    ) {
        blackjackView.showResult(dealer, players)
        blackjackView.showBettingResult(dealer, bettingTables, profitRates)
    }
}
