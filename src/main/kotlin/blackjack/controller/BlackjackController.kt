package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Cards
import blackjack.model.CardsStatus.Companion.BUST_SCORE
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(Cards.WHOLE_CARDS)

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
        players.pickCard(cardDeck, 2)
        dealer.pickCard(cardDeck, 2)
        outputView.printPlayersCards(dealer, players.value)
    }

    private fun playGames(
        players: Players,
        dealer: Dealer,
    ) {
        players.value.forEach { player ->
            executePlayerGame(player)
        }
        executeDealerGameLogic(dealer)
        calculateResult(players, dealer)
        displayResult(players, dealer)
    }

    private fun executePlayerGame(player: Player) {
        while (!player.isBust()) {
            outputView.printPlayerBehaviorGuide(player)
            val playerBehavior: Player.Behavior = inputView.readPlayerBehavior()

            if (executePlayerBehavior(playerBehavior, player)) break
        }
    }

    private fun executePlayerBehavior(
        playerBehavior: Player.Behavior,
        player: Player,
    ): Boolean {
        when (playerBehavior) {
            Player.Behavior.HIT -> {
                player.pickCard(cardDeck)
                outputView.printPlayerCard(player)
                if (isPlayerBust(player)) return true
            }

            Player.Behavior.STAY -> return true
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
            val dealerResult: GameResult = dealer.updateResult(BUST_SCORE)
            player.updateResult(dealerResult)
        }
        playersWhichNotDying.forEach { player ->
            val dealerResult: GameResult = dealer.updateResult(player.getPlayerScore())
            player.updateResult(dealerResult)
        }
    }

    private fun displayResult(
        players: Players,
        dealer: Dealer,
    ) {
        outputView.printResult(dealer, players)
    }
}
