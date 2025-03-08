package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.ShuffledCardGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val dealer = Dealer()
        val players = getPlayers()
        val deck = Deck(ShuffledCardGenerator().generate())

        dealInitialCards(dealer, players, deck)
        playTurns(dealer, players, deck)

        outputView.printBlackjackScore(dealer, players)
        showGameResult(dealer, players)
    }

    private fun getPlayers(): Players {
        val playerNames = inputView.readPlayerNames()
        val players = playerNames.map { Player(it) }
        return Players(players)
    }

    private fun dealInitialCards(
        dealer: Dealer,
        players: Players,
        deck: Deck,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.drawCard(deck.pick())
            players.dealCards(deck)
        }
        outputView.printDealingResult(dealer, players)
    }

    private fun playTurns(
        dealer: Dealer,
        players: Players,
        deck: Deck,
    ) {
        players.players.forEach { drawCard(it, deck) }
        while (dealer.canHit()) {
            dealer.drawCard(deck.pick())
        }
        val hitCount = dealer.countCards() - INITIAL_CARD_COUNT
        outputView.printDealerHit(hitCount)
    }

    private fun drawCard(
        player: Player,
        deck: Deck,
    ) {
        while (player.canHit() && inputView.readPlayerHit(player)) {
            player.drawCard(deck.pick())
            outputView.printPlayerCards(player)
        }
        if (!player.canHit()) {
            outputView.printBust(player)
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach {
            outputView.printPlayerResult(
                it.name,
                it.getResult(dealer.calculateScore()),
            )
        }
        val dealerResult =
            players.players
                .map { dealer.getResult(it.calculateScore()) }
                .groupingBy { it }
                .eachCount()
                .withDefault { 0 }
        outputView.printDealerResult(dealerResult)
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
