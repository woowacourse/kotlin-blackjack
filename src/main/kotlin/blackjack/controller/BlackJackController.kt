package blackjack.controller

import Player
import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.BettingMoney
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
        val bettingAmounts = mutableMapOf<String, BettingMoney>()
        playersName.forEach { playerName ->
            val bettingMoney = InputView.readBettingMoney(playerName) // 배팅 금액 입력 받기
            bettingAmounts[playerName] = bettingMoney
        }
        val (dealer, playerEntry) = getDealerAndPlayerEntry(playersName, deck, bettingAmounts)
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

    private fun getDealerAndPlayerEntry(
        playersName: List<String>,
        deck: Deck,
        bettingAmounts: Map<String, BettingMoney>,
    ): Pair<Dealer, PlayerEntry> {
        ProgressView.showPlayerEntry(playersName.joinToString(", "))
        val hands = dealingCards(playersName.size + 1, deck)
        val dealer = makeDealer(hands.first())
        val players =
            makePlayers(playersName, hands, bettingAmounts)
        return Pair(dealer, PlayerEntry(players))
    }

    private fun makePlayers(
        playersName: List<String>,
        hands: List<Hand>,
        bettingAmounts: Map<String, BettingMoney>,
    ): List<Player> {
        val players =
            playersName.mapIndexed { index, playerName ->
                Player(playerName, hands[index + 1], bettingAmounts[playerName]!!)
            }
        return players
    }

    private fun dealingCards(
        playersSize: Int,
        deck: Deck,
    ): List<Hand> {
        val allDrawnCards = mutableListOf<List<Card>>()
        repeat(playersSize) {
            val drawnCards = mutableListOf<Card>()
            repeat(2) { drawnCards.add(deck.dealCard()) }
            allDrawnCards.add(drawnCards)
        }
        val hands = allDrawnCards.map { drawnCards -> Hand(drawnCards.toMutableList()) }
        return hands
    }

    private fun makeDealer(hand: Hand): Dealer {
        val dealer = Dealer(hand)
        return dealer
    }
}
