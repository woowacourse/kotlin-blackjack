package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Result
import blackjack.domain.Rule
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var deck: Deck

    fun play() {
        val dealer = Dealer()
        val players = getPlayers()
        deck = Deck()

        dealInitialCards(dealer, players)
        playTurns(dealer, players)

        outputView.printBlackjackScore(dealer, players)
        calculateResult(dealer, players)
    }

    private fun getPlayers(): Players {
        val playerNames = inputView.readPlayerNames()
        val players = playerNames.map { Player(it) }
        return Players(players)
    }

    private fun dealInitialCards(
        dealer: Dealer,
        players: Players,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.addCard(deck.pick())
            players.dealCards(deck)
        }
        outputView.printDealingResult(dealer, players)
    }

    private fun playTurns(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach { drawCard(it) }
        dealer.drawCard(deck)
        val hitCount = dealer.hand.cards.size - INITIAL_CARD_COUNT
        outputView.printDealerHit(hitCount)
    }

    private fun drawCard(player: Player) {
        while (!Rule.isBust(player.hand) && inputView.readHitOrStay(player) == Action.HIT) {
            player.addCard(deck.pick())
            outputView.printPlayerCards(player)
        }
        if (Rule.isBust(player.hand)) {
            outputView.printBust(player)
        }
    }

    private fun calculateResult(
        dealer: Dealer,
        players: Players,
    ) {
        val playerResult = players.calculateResult(dealer)
        val dealerResult = getDealerResult(playerResult)
        outputView.printMatchResult(dealerResult, playerResult)
    }

    private fun getDealerResult(playerResult: Map<Player, Result>): Map<Result, Int> =
        playerResult.values
            .groupingBy {
                when (it) {
                    Result.WIN -> Result.LOSE
                    Result.LOSE -> Result.WIN
                    Result.PUSH -> Result.PUSH
                }
            }.eachCount()
            .withDefault { 0 }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
