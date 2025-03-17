package blackjack.controller

import blackjack.model.BettingMoney
import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Player.Behavior
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(Card.wholeCards)

    fun run() {
        outputView.printStartMessage()
        val players: Players = inputView.readPlayers()
        val dealer = Dealer()

        getBettingMoney(players)
        getCards(players, dealer)
        playGames(players, dealer)
    }

    private fun getBettingMoney(players: Players) {
        players.value.forEach { player ->
            outputView.printBettingMessage(player)
            val money: BettingMoney = inputView.readBettingMoney()
            player.updateBettingMoney(money)
        }
    }

    private fun getCards(
        players: Players,
        dealer: Dealer,
    ) {
        players.pickCard(cardDeck, 2)
        dealer.pickCard(cardDeck, 2)
        outputView.printPlayersCards(dealer, players.value)
    }

    private fun playGames(
        players: Players,
        dealer: Dealer,
    ) {
        if (handleDealerBlackjack(dealer, players)) return
        players.value.forEach { player ->
            executePlayerGame(player)
        }
        executeDealerGameLogic(dealer)
        players.updateProfit(dealer)
        displayResult(players, dealer)
    }

    private fun handleDealerBlackjack(
        dealer: Dealer,
        players: Players,
    ): Boolean {
        if (dealer.isBlackjack()) {
            players.updateProfit(dealer)
            outputView.printDealerBlackjack()
            displayResult(players, dealer)
            return true
        }
        return false
    }

    private fun executePlayerGame(player: Player) {
        while (player.canHit()) {
            outputView.printPlayerBehaviorGuide(player)
            val playerBehavior: Behavior = inputView.readPlayerBehavior()

            if (isTurnOver(playerBehavior, player)) break
        }
    }

    private fun isTurnOver(
        playerBehavior: Behavior,
        player: Player,
    ): Boolean {
        when (playerBehavior) {
            Behavior.HIT -> if (isHitPlayerBust(player)) return true
            Behavior.STAY -> return true
        }
        return false
    }

    private fun isHitPlayerBust(player: Player): Boolean {
        player.pickCard(cardDeck)
        outputView.printPlayerCard(player)
        return isPlayerBust(player)
    }

    private fun isPlayerBust(player: Player): Boolean {
        if (player.isBust()) {
            outputView.printBust(player)
            return true
        }
        return false
    }

    private fun executeDealerGameLogic(dealer: Dealer) {
        while (dealer.canHit()) {
            dealer.pickCard(cardDeck)
            outputView.printDealerGettingCard()
            if (dealer.isBust()) break
        }
    }

    private fun displayResult(
        players: Players,
        dealer: Dealer,
    ) {
        outputView.printResult(dealer, players)
    }
}
