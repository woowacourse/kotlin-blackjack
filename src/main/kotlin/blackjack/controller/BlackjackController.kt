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

        drawPlayerCards(players, deck)
        drawDealerCards(dealer, deck)
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
            players.drawCard(deck)
        }
        outputView.printCardInfo(dealer, players)
    }

    private fun drawPlayerCards(
        players: Players,
        deck: Deck,
    ) {
        players.players.forEach { player ->
            while (player.canHit() && inputView.readPlayerHit(player)) {
                player.drawCard(deck.pick())
                outputView.printPlayerCards(player)
            }
            if (!player.canHit()) {
                outputView.printBust(player)
            }
        }
    }

    private fun drawDealerCards(
        dealer: Dealer,
        deck: Deck,
    ) {
        while (dealer.canHit()) {
            dealer.drawCard(deck.pick())
        }
        outputView.printDealerHit(dealer)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printParticipantScore(dealer, players)

        val dealerResult =
            players.players
                .map { dealer.getResult(it.getScore()) }
                .groupingBy { it }
                .eachCount()
        outputView.printDealerResult(dealer, dealerResult)
        players.players.forEach {
            outputView.printPlayerResult(
                it.name,
                it.getResult(dealer.getScore()),
            )
        }
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
