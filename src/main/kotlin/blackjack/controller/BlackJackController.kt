package blackjack.controller

import blackjack.domain.BettingPlayer
import blackjack.domain.BettingPlayers
import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        with(initBlackJack()) {
            setUpCard(this)
            takeTurns(this)
            takeDealerTurn(this)

            OutputView.printScores(getCards(), getTotalScores())
            OutputView.printResults(getGameResults())
        }
    }

    private fun initBlackJack(): BlackJack = BlackJack(CardDeck(), Participants(Dealer(), enrollPlayers()))

    private fun enrollPlayers(): BettingPlayers {
        val players = InputView.inputNames().map(::Player)
        val bettingPlayers = players.map { BettingPlayer(it, 0) }
        return BettingPlayers(bettingPlayers)
    }

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        OutputView.printFirstOpenCards(blackJack.getFirstOpenCards())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }

    private fun takeTurns(blackJack: BlackJack) {
        blackJack.getPlayers().forEach { player ->
            takePlayerTurn(blackJack, player)
        }
        OutputView.printInterval()
    }

    private fun takePlayerTurn(blackJack: BlackJack, player: BettingPlayer) {
        if (!player.canDraw()) {
            val isDraw = InputView.inputDrawCommand(player.getName())
            if (!isDraw) return printPlayerCards(player)

            drawCard(blackJack, player)
            takePlayerTurn(blackJack, player)
        }
    }

    private fun drawCard(blackJack: BlackJack, player: BettingPlayer) {
        blackJack.drawPlayer(player)
        printPlayerCards(player)
    }

    private fun printPlayerCards(player: BettingPlayer) {
        val participantCards = player.getCards()
        OutputView.printCards(participantCards.name, participantCards.cards)
    }

    private fun takeDealerTurn(blackJack: BlackJack) {
        blackJack.drawDealer { isHit ->
            if (isHit) OutputView.printDealerHit()
        }
        OutputView.printInterval()
    }
}
