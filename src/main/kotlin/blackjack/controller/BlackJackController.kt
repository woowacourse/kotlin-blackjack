package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.GameResult
import blackjack.model.game.Referee
import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.InputView
import blackjack.view.ProgressView
import blackjack.view.ResultView

object BlackJackController {
    fun run() {
        val deck = Deck()
        val playersName = InputView.readPlayersName()
        val playerEntry = makePlayerEntry(playersName, deck)
        val dealer = makeDealer(makingInitialHand(deck))
        ProgressView.showPlayerEntry(playersName.joinToString(", "))
        ProgressView.showHands(dealer, playerEntry)
        val gameResult = playGame(playerEntry, dealer, deck)
        showGameResult(gameResult)
    }

    private fun makePlayerEntry(
        playersName: List<String>,
        deck: Deck,
    ): PlayerEntry {
        val players =
            playersName.map { playerName ->
                val bettingMoney = InputView.readBettingMoney(playerName)
                val hand = makingInitialHand(deck)
                Player(playerName, hand, bettingMoney)
            }
        val playerEntry = PlayerEntry(players)
        return playerEntry
    }

    private fun makingInitialHand(deck: Deck) = Hand(deck.doubleDealCard().toMutableList())

    private fun showGameResult(gameResult: GameResult) {
        ResultView.showResult(gameResult)
    }

    private fun playGame(
        playerEntry: PlayerEntry,
        dealer: Dealer,
        deck: Deck,
    ): GameResult {
        askPlayersDraw(playerEntry, deck)
        makeDealerDraw(dealer, deck)
        val referee = Referee(dealer, playerEntry)
        return referee.judgeGame()
    }

    private fun makeDealerDraw(
        dealer: Dealer,
        deck: Deck,
    ) {
        while (dealer.isRunning()) {
            dealer.hand.draw(deck.dealCard())
            ProgressView.showDealerDrawMessage(dealer)
        }
    }

    private fun askPlayersDraw(
        playerEntry: PlayerEntry,
        deck: Deck,
    ) {
        playerEntry.players.forEach { player -> askPlayerDraw(player, deck) }
    }

    private fun askPlayerDraw(
        player: Player,
        deck: Deck,
    ) {
        while (player.state is State.Running.Hit) {
            val drawOrNot = InputView.judgePlayersDraw(player)
            if (drawOrNot == InputView.DRAW_DECISION) {
                player.hand.draw(deck.dealCard())
                ProgressView.showPlayerHand(player)
            } else {
                player.hand.decideStay()
                break
            }
        }
    }

    private fun makeDealer(hand: Hand): Dealer {
        return Dealer(hand)
    }
}
