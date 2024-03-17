package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.model.ScoreBoard
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val playersNames: List<String> = inputView.inputPlayerNames()
        val deck = Deck()
        val players = Player.createPlayers(playersNames, deck)
        val playersBetAmounts: List<Int> = inputView.inputPlayerBetAmount(playersNames)
        val dealer = Dealer.createDealer(deck)
        outputView.showDivided(dealer.hand.first(), players)
        hitParticipant(players, deck, dealer)
        val scoreBoard = makeAndShowScoreBoard(players, dealer)
        dealer.giveAmountsToPlayer(scoreBoard, playersBetAmounts)
        dealer.updateProfit(players)
        outputView.showProfits(dealer, players)
    }

    private fun hitParticipant(
        players: List<Player>,
        deck: Deck,
        dealer: Dealer,
    ) {
        hitPlayers(players, deck)
        dealer.hitWhileConditionTrue(
            deck,
        ) { outputView.showDealerHitCard() }
        outputView.showDealerScore(dealer.hand.cards, dealer.hand.sumOptimized())
    }

    private fun hitPlayers(
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.hitWhileConditionTrue(
                deck,
                { inputView.inputWhetherHit(player) },
            ) { outputView.showPlayerHandCards(player) }
        }
    }

    private fun makeAndShowScoreBoard(
        players: List<Player>,
        dealer: Dealer,
    ): ScoreBoard {
        players.forEach {
            val name = it.name
            val handCards = it.hand
            outputView.showPlayerScore(name, handCards.cards, handCards.sumOptimized())
        }
        val scoreBoard = dealer.createScoreBoard(players)
        outputView.showScoreBoard(scoreBoard)
        return scoreBoard
    }
}
