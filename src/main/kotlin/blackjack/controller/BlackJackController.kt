package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val playersNames: List<String> = inputView.inputPlayerNames()
        val deck: Deck = Deck.create(1)
        val players = Player.createPlayers(playersNames, deck)
        val dealer = Dealer.createDealer(deck)
        outputView.showDivided(dealer.hand.first(), players)
        hitParticipant(players, deck, dealer)
        showScoreBoard(players, dealer)
    }

    private fun hitParticipant(
        players: List<Player>,
        deck: Deck,
        dealer: Dealer,
    ) {
        hitPlayers(players, deck)
        dealer.hitIfConditionTrue(
            deck,
            { dealer.canHit() },
        ) { outputView.showDealerHitCard() }
        outputView.showDealerScore(dealer.hand.cards, dealer.hand.sumOptimized())
    }

    private fun hitPlayers(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.hitIfConditionTrue(
                deck,
                { inputView.inputWhetherHit(player) },
            ) { outputView.showPlayerHandCards(player) }
        }
    }

    private fun showScoreBoard(
        players: List<Player>,
        dealer: Dealer,
    ) {
        players.forEach {
            val name = it.name
            val handCards = it.hand
            outputView.showPlayerScore(name, handCards.cards, handCards.sumOptimized())
        }
        val scoreBoard = dealer.createScoreBoard(players)
        outputView.showScoreBoard(scoreBoard)
    }
}
