package blackjack.controller

import blackjack.model.BettingMoney
import blackjack.model.BettingMoney.Companion.BLACKJACK_MULTIPLE
import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardsStatus.Companion.BUST_SCORE
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Money
import blackjack.model.Player
import blackjack.model.Player.Behavior
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(Card.whole_cards)

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
            player.getBettingMoney(money)
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
        calculateResult(players, dealer)
        displayResult(players, dealer)
    }

    private fun handleDealerBlackjack(
        dealer: Dealer,
        players: Players,
    ): Boolean {
        if (dealer.isBlackjack()) {
            executeBlackjackPlayersLogic(players, dealer)
            executeNotBlackjackPlayersLogic(players, dealer)
            outputView.printDealerBlackjack()
            displayResult(players, dealer)
            return true
        }
        return false
    }

    private fun executeBlackjackPlayersLogic(
        players: Players,
        dealer: Dealer,
    ) {
        val blackjackPlayers: List<Player> = players.getBlackjackPlayers()

        blackjackPlayers.forEach { player ->
            player.bettingMoney.multiple(BLACKJACK_MULTIPLE)
            executePlayerGainMoney(dealer, player.bettingMoney, player)
        }
    }

    private fun executeNotBlackjackPlayersLogic(
        players: Players,
        dealer: Dealer,
    ) {
        val notBlackjackPlayers: List<Player> = players.getNotBlackjackPlayers()
        notBlackjackPlayers.forEach { player ->
            val money: Money = player.bettingMoney
            executeDealerGainMoney(dealer, money, player)
        }
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
            Behavior.HIT -> {
                player.pickCard(cardDeck)
                outputView.printPlayerCard(player)
                if (isPlayerBust(player)) return true
            }

            Behavior.STAY -> return true
        }
        return false
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

    private fun calculateResult(
        players: Players,
        dealer: Dealer,
    ) {
        val playersWhichNotDying: List<Player> = players.getNotDyingPlayers()
        val playersWhichDying: List<Player> = players.getDyingPlayers()

        playersWhichDying.forEach { player ->
            val dealerResult: GameResult = dealer.getResult(BUST_SCORE)
            executeMoneyLogic(dealerResult, player, dealer)
        }
        playersWhichNotDying.forEach { player ->
            if (player.isBlackjack()) return executeBlackjackPlayersLogic(players, dealer)
            val dealerResult: GameResult = dealer.getResult(player.getScore())
            executeMoneyLogic(dealerResult, player, dealer)
        }
    }

    private fun executeMoneyLogic(
        dealerResult: GameResult,
        player: Player,
        dealer: Dealer,
    ) {
        when (dealerResult) {
            GameResult.PUSH -> Unit
            GameResult.WIN -> {
                val money: Money = player.bettingMoney
                executeDealerGainMoney(dealer, money, player)
            }

            GameResult.LOSE -> {
                val money: Money = player.bettingMoney
                executePlayerGainMoney(dealer, money, player)
            }
        }
    }

    private fun executeDealerGainMoney(
        dealer: Dealer,
        money: Money,
        player: Player,
    ) {
        dealer.gainMoney(money)
        player.lossMoney(money)
    }

    private fun executePlayerGainMoney(
        dealer: Dealer,
        money: Money,
        player: Player,
    ) {
        dealer.lossMoney(money)
        player.gainMoney(money)
    }

    private fun displayResult(
        players: Players,
        dealer: Dealer,
    ) {
        outputView.printResult(dealer, players)
    }
}
