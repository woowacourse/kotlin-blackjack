package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.GameResult
import blackjack.model.ParticipantsHandCards
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
        val (playerHand, dealerHand) =
            ParticipantsHandCards.from(deck.spread(playersNames.size))
        val dealer = Dealer(dealerHand)
        val players: List<Player> = Player.createPlayers(playersNames, playerHand)
        outputView.showDivided(dealerHand.first(), players)

        players.forEach { player ->
            while (inputView.inputWhetherHit(player)) {
                player.hit(deck.pull())
                outputView.showPlayerHandCards(player)
            }
        }

        while (dealer.canHit()) {
            outputView.showDealerHitCard()
            dealer.hit(deck.pull())
        }

        outputView.showDealerScore(dealer.handCards.cards, dealer.handCards.sumOptimized())
        players.forEach {
            val name = it.name
            val handCards = it.handCards
            outputView.showPlayerScore(name, handCards.cards, handCards.sumOptimized())
        }

        val scoreBoard = GameResult(dealer, players).createScoreBoard()

        outputView.showScoreBoard(scoreBoard)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
