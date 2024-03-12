package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.GameResult
import blackjack.model.game.Referee
import blackjack.model.game.Result
import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.InputView.DRAW_DECISION
import blackjack.view.InputView.judgePlayersDraw
import blackjack.view.InputView.readPlayersName
import blackjack.view.ProgressView.showDealerDrawMessage
import blackjack.view.ProgressView.showHands
import blackjack.view.ProgressView.showPlayerEntry
import blackjack.view.ProgressView.showPlayerHand
import blackjack.view.ResultView.showFinalWinOrLossResult
import blackjack.view.ResultView.showHandsScore

object BlackJackController {
    fun run() {
        val deck = Deck()
        val playersName = readPlayersName()
        val (dealer, playerEntry) = getDealerAndPlayerEntry(playersName, deck)
        val gameResult = playGame(playerEntry, dealer, deck)
        showGameResult(gameResult)
    }

    private fun showGameResult(gameResult: GameResult) {
        showHandsScore(gameResult.dealer, gameResult.playerEntry)
        showFinalWinOrLossResult(gameResult.results, gameResult.playerEntry)
    }

    private fun judgeOfWinOrLose(
        dealer: Dealer,
        playerEntry: PlayerEntry,
    ): List<Result> {
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        return results
    }

    private fun playGame(
        playerEntry: PlayerEntry,
        dealer: Dealer,
        deck: Deck,
    ): GameResult {
        askPlayersDraw(playerEntry, deck)
        showDealerDraw(dealer, deck)
        val results = judgeOfWinOrLose(dealer, playerEntry)
        return GameResult(dealer, playerEntry, results)
    }

    private fun showDealerDraw(
        dealer: Dealer,
        deck: Deck,
    ) {
        while (dealer.state == State.Running.Hit) {
            dealer.hand.draw(deck.dealCard())
            showDealerDrawMessage(dealer)
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
            val drawOrNot = judgePlayersDraw(player)
            if (drawOrNot == DRAW_DECISION) {
                player.hand.draw(deck.dealCard())
                showPlayerHand(player)
            } else {
                break
            }
        }
    }

    private fun getDealerAndPlayerEntry(
        playersName: List<String>,
        deck: Deck,
    ): Pair<Dealer, PlayerEntry> {
        showPlayerEntry(playersName.joinToString(", "))
        val hands = dealingCards(playersName, deck)
        val dealer = setDealer(hands)
        val playerEntry = setPlayerEntry(playersName, hands)
        showHands(dealer, playerEntry)
        return Pair(dealer, playerEntry)
    }

    private fun setPlayerEntry(
        playersName: List<String>,
        hands: List<Hand>,
    ): PlayerEntry {
        val players =
            setPlayers(playersName, hands)
        val playerEntry = PlayerEntry(players)
        return playerEntry
    }

    private fun setPlayers(
        playersName: List<String>,
        hands: List<Hand>,
    ) = playersName.withIndex().map { (index, playerName) ->
        Player(playerName, hands[index + 1])
    }

    private fun setDealer(hands: List<Hand>): Dealer {
        val dealer = Dealer(hands[0])
        return dealer
    }

    private fun dealingCards(
        playersName: List<String>,
        deck: Deck,
    ): List<Hand> {
        val hands = List(playersName.size + 1) { Hand(mutableListOf()) }
        repeat(2) { hands.forEach { hand -> hand.draw(deck.dealCard()) } }
        return hands
    }
}
