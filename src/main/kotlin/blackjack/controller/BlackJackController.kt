package blackjack.controller

import blackjack.model.Cards
import blackjack.model.Dealer
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
        val deck: Cards = Cards.createDeck()
        val (playerHandCards, dealerHandCards) = ParticipantsHandCards.from(deck.take(2 * (1 + playersNames.size)))
        val dealer = Dealer(dealerHandCards)
        val players: List<Player> = playersNames.zip(playerHandCards).map(::Player)
        outputView.showDivided(dealerHandCards.cards.first(), players)

        players.forEach { player ->
            while (inputView.inputWhetherHit(player)) {
                player.hit(deck.shuffled().first())
                outputView.showPlayerHandCards(player)
            }
        }

        while (dealer.canHit()) {
            outputView.showDealerHitCard()
            dealer.hit(deck.shuffled().first())
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
