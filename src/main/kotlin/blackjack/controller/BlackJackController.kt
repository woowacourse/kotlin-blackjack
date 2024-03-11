package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Participant
import blackjack.model.ParticipantsHand
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val playersNames: List<String> = inputView.inputPlayerNames()
        val deck: Deck = Deck.create()
        val (dealer, players) = spreadCards(deck, playersNames)
        hitParticipant(players, deck, dealer)
        showScoreBoard(players, dealer)
    }

    private fun hitParticipant(
        players: List<Player>,
        deck: Deck,
        dealer: Dealer,
    ) {
        hitPlayers(players, deck)
        dealer.hitUntilBust(deck) { outputView.showDealerHitCard() }
        outputView.showDealerScore(dealer.hand.cards, dealer.hand.sumOptimized())
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

    private fun hitPlayers(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            hitPlayer(player, deck)
        }
    }

    private fun hitPlayer(
        player: Player,
        deck: Deck,
    ) {
        while (inputView.inputWhetherHit(player)) {
            player.hit(deck.pull())
            outputView.showPlayerHandCards(player)
        }
    }

    private fun spreadCards(
        deck: Deck,
        playersNames: List<String>,
    ): Participant {
        val (playerHand, dealerHand) =
            ParticipantsHand.from(deck.spread(playersNames.size))
        val dealer = Dealer(dealerHand)
        val players: List<Player> = Player.createPlayers(playersNames, playerHand)
        outputView.showDivided(dealerHand.first(), players)
        return Participant(dealer, players)
    }
}
