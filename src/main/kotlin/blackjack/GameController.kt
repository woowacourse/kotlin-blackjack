package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val game = BlackjackGame(InputView.getNames().map(::Player), Dealer())
    game.startGame(ResultView::printSetUp)
    game.runPlayer(InputView::doesPlayerWantHit, ResultView::printCards)
    game.runDealer(ResultView::printDealerHitMessage)

    val result = BlackjackResult.of(game.dealer, game.players)
    ResultView.printResult(game.dealer, game.players, result)
}
