package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.BettingPlayer
import blackjack.domain.participant.BettingPlayers
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        with(initBlackJack()) {
            setUpCard(this)
            takeTurns(this)
            takeDealerTurn(this)

            val results = getGameResults()
            outputView.printResult(getCards(), getTotalScores(), results, results.calculateProfits())
        }
    }

    private fun initBlackJack(): BlackJack = BlackJack(CardDeck(), Participants(Dealer(), enrollPlayers()))

    private fun enrollPlayers(): BettingPlayers {
        val players = inputView.inputNames().map(::Player)
        val bettingPlayers = players.map { BettingPlayer(it, inputView.inputBettingMoney(it.name)) }
        return BettingPlayers(bettingPlayers)
    }

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        outputView.printFirstOpenCards(blackJack.getFirstOpenCards())
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }

    private fun takeTurns(blackJack: BlackJack) {
        blackJack.getPlayers().forEach { player ->
            takePlayerTurn(blackJack, player)
        }
    }

    private fun takePlayerTurn(blackJack: BlackJack, player: BettingPlayer) {
        if (!player.canDraw()) {
            val isDraw = inputView.inputDrawCommand(player.getName())
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
        outputView.printCards(participantCards.name, participantCards.cards)
    }

    private fun takeDealerTurn(blackJack: BlackJack) {
        blackJack.drawDealer { isHit ->
            if (isHit) outputView.printDealerHit()
        }
    }
}
