package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.CardsMaker
import blackjack.model.CardsStatus
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.PlayerBehavior
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(CardsMaker.CARDS)

    fun run() {
        outputView.printStartMessage()
        val players: Players = inputView.readPlayers()
        val dealer = Dealer()

        getCards(players, dealer)
        playGames(players, dealer)
    }

    private fun getCards(
        players: Players,
        dealer: Dealer,
    ) {
        repeat(2) {
            getCardsToPlayer(players)
            getCardsToDealer(dealer)
        }
        outputView.printPlayersCards(dealer, players.value)
    }

    private fun getCardsToPlayer(players: Players) {
        players.value.forEach { player ->
            player.appendCard(cardDeck)
        }
    }

    private fun getCardsToDealer(dealer: Dealer) {
        dealer.appendCard(cardDeck)
    }

    private fun playGames(
        players: Players,
        dealer: Dealer,
    ) {
        if (dealer.isBlackjack()) {
            val blackjackPlayers: List<Player> = players.findBlackjackPlayer()
            updateGameResult(players, dealer)
            outputView.printDealerBlackjackMessage(dealer, blackjackPlayers)
            displayResult(players, dealer)
            return
        }
        players.value.forEach { player ->
            playGame(player, dealer)
        }
        displayResult(players, dealer)
    }

    private fun updateGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        players.value.forEach { player ->
            if (isAllPlayerBlackjack(player, dealer)) return@forEach
            player.updateResult(GameResult.WIN)
            dealer.updateResult(player.cards.calculateScore())
        }
    }

    private fun isAllPlayerBlackjack(
        player: Player,
        dealer: Dealer,
    ): Boolean {
        if (player.isBlackjack()) {
            player.updateResult(GameResult.PUSH)
            dealer.updateResult(player.cards.calculateScore())
            return true
        }
        return false
    }

    private fun playGame(
        player: Player,
        dealer: Dealer,
    ) {
        if (isPlayerBlackjack(player, dealer)) return
        executePlayerGameLogic(player)
        executeDealerGameLogic(dealer)
        val dealerResult: GameResult = dealer.updateResult(dealer.cards.calculateScore())
        player.updateResult(dealerResult)
    }

    private fun isPlayerBlackjack(
        player: Player,
        dealer: Dealer,
    ): Boolean {
        if (player.isBlackjack()) {
            val dealerResult: GameResult = dealer.updateResult(CardsStatus.BLACKJACK_SCORE)
            player.updateResult(dealerResult)
            return true
        }
        return false
    }

    private fun executePlayerGameLogic(player: Player) {
        while (!player.isBust()) {
            outputView.printPlayerBehaviorGuide(player)
            val playerBehavior: PlayerBehavior = inputView.readPlayerBehavior()

            if (executePlayerBehavior(playerBehavior, player)) break
        }
    }

    private fun executePlayerBehavior(
        playerBehavior: PlayerBehavior,
        player: Player,
    ): Boolean {
        when (playerBehavior) {
            PlayerBehavior.HIT -> {
                player.appendCard(cardDeck)
                outputView.printPlayerCard(player)
                if (isPlayerBust(player)) return true
            }

            PlayerBehavior.STAY -> return true
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
        while (dealer.isHit()) {
            dealer.appendCard(cardDeck)
            outputView.printDealerGettingCard()
            if (isDealerBust(dealer)) break
        }
    }

    private fun isDealerBust(dealer: Dealer): Boolean {
        if (dealer.isBust()) {
            outputView.printBust(dealer)
            return true
        }
        return false
    }

    private fun displayResult(
        players: Players,
        dealer: Dealer,
    ) {
        outputView.printResult(dealer, players)
    }
}
