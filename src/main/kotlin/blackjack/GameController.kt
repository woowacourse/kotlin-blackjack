package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val dealer = Dealer()
        val players: List<Player> = getPlayersWithBets()

        setInitialHands(dealer, players)
        outputView.showInitialCards(dealer, players)

        askPlayerHit(players)

        handleDealerHit(dealer)

        showResult(dealer, players)
    }

    private fun handleDealerHit(dealer: Dealer) {
        if (dealer.canHit()) {
            outputView.printDealerHaveAdditionalCard()
            while (dealer.canHit()) {
                dealer.addCard(deck.draw())
            }
        }
    }

    private fun getPlayersWithBets(): List<Player> {
        val playerNames = InputView.readPlayerNames()

        return playerNames.map { name ->
            val betAmount = InputView.readBetAmounts(name)
            Player(name, BetAmount(betAmount))
        }
    }

    private fun setInitialHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealCards(dealer)
        players.forEach { player -> dealCards(player) }
    }

    private fun dealCards(participant: Participant) {
        repeat(INITIAL_CARD_COUNT) {
            participant.addCard(deck.draw())
        }
    }

    private fun askPlayerHit(players: List<Player>) {
        players.forEach { player ->
            handlePlayerHit(player)
        }
    }

    private fun handlePlayerHit(player: Player) {
        while (player.canHit()) {
            val result = inputView.askPlayerHit(player.name)
            if (result) {
                player.addCard(deck.draw())
                outputView.printPlayerCards(player)
            } else {
                break
            }
        }
    }

    private fun showResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val gameResult = GameResult(dealer, players)
        gameResult.updateGameResult()
        gameResult.calculatePlayerProfit()
        outputView.printFinalCards(dealer, players)
        outputView.printResult(gameResult)
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
