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
            getCardsToPlayer(players.value)
            getCardsToDealer(dealer)
        }
        outputView.printPlayersCards(dealer, players.value)
    }

    private fun getCardsToDealer(dealer: Dealer) {
        val card = cardDeck.pickCard()
        dealer.appendCard(card)
    }

    private fun getCardsToPlayer(players: List<Player>) {
        players.forEach { player ->
            val card = cardDeck.pickCard()
            player.appendCard(card)
        }
    }

    private fun playGames(
        players: Players,
        dealer: Dealer,
    ) {
        if (dealer.isBlackjack(true)) {
            val blackjackPlayers: List<Player> = players.findBlackjackPlayer()
            players.value.forEach { player ->
                if (player.cards.isBlackjack(true)) {
                    player.updateResult(GameResult.PUSH)
                    dealer.updateResult(player.cards.calculateScore())
                    return@forEach
                }
                player.updateResult(GameResult.WIN)
                dealer.updateResult(player.cards.calculateScore())
            }
            outputView.printDealerBlackjackMessage(dealer, blackjackPlayers)
            displayResult(players, dealer)
            return
        }
        players.value.forEach { player ->
            playGame(player, dealer)
        }
        displayResult(players, dealer)
    }

    private fun playGame(
        player: Player,
        dealer: Dealer,
    ) {
        if (player.isBlackjack(true)) {
            val dealerResult: GameResult = dealer.updateResult(CardsStatus.BLACKJACK_SCORE)
            player.updateResult(dealerResult)
            return
        }
        while (!player.cards.isBust(false)) {
            outputView.printPlayerBehaviorGuide(player)
            val playerBehavior: PlayerBehavior = inputView.readPlayerBehavior()

            when (playerBehavior) {
                PlayerBehavior.HIT -> {
                    player.appendCard(cardDeck.pickCard())
                    outputView.printPlayerCard(player)
                    if (player.cards.isBust(false)) {
                        outputView.printBust(player)
                        break
                    }
                }

                PlayerBehavior.STAY -> break
            }
        }
        val dealerResult: GameResult = dealer.updateResult(0)
        player.updateResult(dealerResult)
    }

    private fun displayResult(
        players: Players,
        dealer: Dealer,
    ) {
        outputView.printResult(dealer, players)
    }
}
