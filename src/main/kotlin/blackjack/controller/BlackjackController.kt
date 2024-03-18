package blackjack.controller

import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Judge
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.state.State
import blackjack.view.BettingAmountInputView
import blackjack.view.IsAddCardInputView
import blackjack.view.OutputView
import blackjack.view.PlayersInputView

class BlackjackController(
    private val playersInputView: PlayersInputView = PlayersInputView(),
    private val bettingAmountInputView: BettingAmountInputView = BettingAmountInputView(),
    private val isAddCardInputView: IsAddCardInputView = IsAddCardInputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val deck: Deck = Deck()
    private val dealer: Dealer = Dealer(deck)
    private val judge = Judge()
    private lateinit var players: Players

    fun play() {
        setUpGame()
        setBettingAmount()
        gamePlayersTurn()
        dealerTurn()
        showResult()
    }

    private fun setUpGame() {
        players = playersInputView.readPlayerNames(deck)
        outputView.printInitCard(dealer, players)
    }

    private fun setBettingAmount() {
        players.gamePlayers.forEach { player: Player ->
            player.bettingAmount = bettingAmountInputView.readBettingAmount(player.name)
        }
    }

    private fun gamePlayersTurn() {
        players.gamePlayers.forEach { player: Player ->
            playerTurn(player)
        }
    }

    private fun playerTurn(player: Player) {
        while (player.state !is State.Finished) {
            processPlayerStateDecision(player)
        }

        if (player.isBust()) {
            outputView.printBustMessage()
        }
        outputView.printPlayerCard(player)
    }

    private fun processPlayerStateDecision(player: Player) {
        val userWantsHit = isAddCardInputView.readIsAddCard(player.name)
        if (userWantsHit && player.state is State.Running) {
            player.state = State.Hit(player).decisionState()
        } else if (!userWantsHit) {
            player.state = State.Finished.Stay
        }
    }

    private tailrec fun dealerTurn() {
        if (dealer.isAddCard()) {
            dealer.addCard()
            outputView.printDealerAddCard()
            dealerTurn()
        }
    }

    private fun showResult() {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(judge.gameResult(dealer, players.gamePlayers))
    }
}
