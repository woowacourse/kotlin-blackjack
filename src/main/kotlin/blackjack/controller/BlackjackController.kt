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
        val playerNames = inputView.readPlayerNames()
        val dealer = Dealer()
        val players = playerNames.map { Player(it) }
        dealCards(dealer, players)
        outputView.printDealingResult(dealer, players)
        players.forEach { dealMoreCard(it) }

        dealer.dealCards()
        val hitCount = dealer.hand.cards.size - INITIAL_CARD_COUNT
        outputView.printDealerHit(hitCount)

        outputView.printBlackjackResult(dealer, players)
        val playerResult = getPlayerResult(dealer, players)
        val dealerResult = getDealerResult(playerResult)
        outputView.printMatchResult(dealerResult, playerResult)
    }

    private fun dealCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.addCard(Deck.pick())
            players.forEach { it.addCard(Deck.pick()) }
        }
    }

    private fun dealMoreCard(player: Player) {
        if (player.isBust()) {
            outputView.printBust()
            return
        }
        val action = inputView.readHitOrStay(player)
        if (action == Action.HIT) {
            player.addCard(Deck.pick())
            outputView.printPlayerCards(player)
            dealMoreCard(player)
        }
    }

    private fun getPlayerResult(
        dealer: Dealer,
        players: List<Player>,
    ): Map<String, Result> = players.associateBy({ it.name }, { it.getResult(dealer) })

    private fun getDealerResult(playerResult: Map<String, Result>): Map<Result, Int> =
        playerResult.values
            .groupingBy {
                when (it) {
                    Result.WIN -> Result.LOSE
                    Result.LOSE -> Result.WIN
                    Result.DRAW -> Result.DRAW
                }
            }.eachCount()
            .withDefault { 0 }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
