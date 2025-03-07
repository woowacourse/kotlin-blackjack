package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.enums.Action
import blackjack.enums.Result
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val dealer = Dealer()
        val players = getPlayers()

        dealInitialCards(dealer, players)
        playTurns(dealer, players)

        outputView.printBlackjackScore(dealer, players)
        calculateResult(dealer, players)
    }

    private fun getPlayers(): List<Player> {
        val playerNames = inputView.readPlayerNames()
        return playerNames.map { Player(it) }
    }

    private fun dealInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.addCard(Deck.pick())
            players.forEach { it.addCard(Deck.pick()) }
        }
        outputView.printDealingResult(dealer, players)
    }

    private fun playTurns(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { drawCard(it) }
        dealer.drawCard()
        val hitCount = dealer.hand.cards.size - INITIAL_CARD_COUNT
        outputView.printDealerHit(hitCount)
    }

    private fun drawCard(player: Player) {
        while (!player.isBust() && inputView.readHitOrStay(player) == Action.HIT) {
            player.addCard(Deck.pick())
            outputView.printPlayerCards(player)
        }
        if (player.isBust()) {
            outputView.printBust(player)
        }
    }

    private fun calculateResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playerResult = getPlayerResult(dealer, players)
        val dealerResult = getDealerResult(playerResult)
        outputView.printMatchResult(dealerResult, playerResult)
    }

    private fun getPlayerResult(
        dealer: Dealer,
        players: List<Player>,
    ): Map<String, Result> = players.associate { it.name to it.getResult(dealer) }

    private fun getDealerResult(playerResult: Map<String, Result>): Map<Result, Int> =
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
