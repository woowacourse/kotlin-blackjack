package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
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
            dealer.drawCard(Deck.pick())
            players.dealCards()
        }
        outputView.printDealingResult(dealer, players)
    }

    private fun playTurns(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach { drawCard(it) }
        while (dealer.canHit()) {
            dealer.drawCard(Deck.pick())
        }
        val hitCount = dealer.countCards() - INITIAL_CARD_COUNT
        outputView.printDealerHit(hitCount)
    }

    private fun drawCard(player: Player) {
        while (player.canHit() && inputView.readHitOrStay(player) == Action.HIT) {
            player.drawCard(Deck.pick())
            outputView.printPlayerCards(player)
        }
        if (!player.canHit()) {
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
