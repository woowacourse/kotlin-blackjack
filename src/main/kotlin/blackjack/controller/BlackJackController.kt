package blackjack.controller

import Player
import blackjack.model.card.Card
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
        val (dealer, playerEntry) = getDealerAndPlayerEntry(playersName, deck)
        val gameResult = playGame(playerEntry, dealer, deck)
        showGameResult(gameResult)
    }

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
        while (dealer.state == State.Running.Hit) {
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
                break
            }
        }
    }

    private fun getDealerAndPlayerEntry(
        playersName: List<String>,
        deck: Deck,
    ): Pair<Dealer, PlayerEntry> {
        ProgressView.showPlayerEntry(playersName.joinToString(", "))
        val hands = dealingCards(playersName, deck)
        val dealer = setDealer(hands)
        val playerEntry = makePlayerEntry(playersName, hands)
        ProgressView.showHands(dealer, playerEntry)
        return Pair(dealer, playerEntry)
    }

    private fun makePlayerEntry(
        playersName: List<String>,
        hands: List<Hand>,
    ): PlayerEntry {
        val players =
            makePlayers(playersName, hands)
        val playerEntry = PlayerEntry(players)
        return playerEntry
    }

    private fun dealingCards(
        playersName: List<String>,
        deck: Deck,
    ): List<Hand> {
        val allDrawnCards = mutableListOf<List<Card>>()
        repeat(playersName.size + 1) {
            val drawnCards = mutableListOf<Card>()
            repeat(2) { drawnCards.add(deck.dealCard()) }
            allDrawnCards.add(drawnCards)
        }
        val hands = allDrawnCards.map { drawnCards -> Hand(drawnCards.toMutableList()) }
        return hands
    }

    private fun makePlayers(
        playersName: List<String>,
        hands: List<Hand>,
    ) = playersName.withIndex().map { (index, playerName) ->
        Player(playerName, hands[index + 1])
    }

    private fun setDealer(hands: List<Hand>): Dealer {
        val dealer = Dealer(hands[0])
        return dealer
    }
}
