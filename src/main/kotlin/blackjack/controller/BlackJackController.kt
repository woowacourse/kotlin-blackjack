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
    fun start(deck: CardDeck) {
        initBlackJack(deck).start(outputView::printFirstDraw, outputView::printDraw, outputView::printResult)
    }

    private fun initBlackJack(deck: CardDeck): BlackJack = BlackJack(deck, Participants(Dealer(), enrollPlayers()))

    private fun enrollPlayers(): BettingPlayers {
        val players = inputView.inputNames().map { Player(it, inputView::inputDrawCommand) }
        val bettingPlayers = players.map { BettingPlayer(it, inputView.inputBettingMoney(it.name)) }
        return BettingPlayers(bettingPlayers)
    }
}
