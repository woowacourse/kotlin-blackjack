package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.CardDeck
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

    private fun initBlackJack(deck: CardDeck): BlackjackGame = BlackjackGame(deck, Participants(Dealer(), enrollPlayers()))

    private fun enrollPlayers(): BettingPlayers {
        val names = inputView.inputNames()
        val players = names.map { Player(it, inputView::inputDrawCommand) }
        val money: Map<Player, Int> = players.associateWith { inputView.inputBettingMoney(it.name) }
        return BettingPlayers(players, money)
    }
}
