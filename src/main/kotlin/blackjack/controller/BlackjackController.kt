package blackjack.controller

import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.state.Finished
import blackjack.state.Hit
import blackjack.state.Running
import blackjack.state.Stay
import blackjack.view.IsAddCardInputView
import blackjack.view.OutputView
import blackjack.view.PlayersInputView

class BlackjackController(
    private val playersInputView: PlayersInputView = PlayersInputView(),
    private val isAddCardInputView: IsAddCardInputView = IsAddCardInputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val deck: Deck = Deck()
    private val dealer: Dealer = Dealer(deck)
    private lateinit var players: Players

    fun play() {
        setUpGame()
        gamePlayersTurn()
        dealerTurn()
        showResult()
    }

    private fun setUpGame() {
        players = playersInputView.readPlayerNames(deck)
        dealer.initializeCards()
        outputView.printInitCard(dealer, players)
    }

    private fun gamePlayersTurn() {
        players.gamePlayers.forEach { player: Player ->
            playerTurn(player)
        }
    }

    private fun playerTurn(player: Player) {
        while (player.state !is Finished) {
            processPlayerStateDecision(player)
        }

        if (player.isBust()) {
            outputView.printBustMessage()
        }
        outputView.printPlayerCard(player)
    }

    private fun processPlayerStateDecision(player: Player) {
        val userWantsHit = isAddCardInputView.readIsAddCard(player.name)
        if (userWantsHit && player.state is Running) {
            player.state = Hit(player).drawCard()
        } else if (!userWantsHit) {
            player.state = Stay(player).drawCard()
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
        outputView.printGameResult(dealer.gameResult(players.gamePlayers))
    }
}
