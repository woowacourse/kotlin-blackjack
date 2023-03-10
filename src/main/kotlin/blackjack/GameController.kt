package blackjack

import blackjack.domain.betting.BettingResult
import blackjack.domain.game.BlackjackGame
import blackjack.domain.game.BlackjackParticipant
import blackjack.domain.participant.Dealer
import blackjack.domain.result.BlackjackResult
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = InputView.getNames()
    val participant = BlackjackParticipant.of(Dealer(), names, InputView::getBettingMoney)
    val game = BlackjackGame()

    game.startGame(participant, ResultView::printSetUp)
    game.runPlayer(participant.players, InputView::doesPlayerWantHit, ResultView::printCards)
    game.runDealer(participant.dealer, ResultView::printDealerHitMessage)

    val bettingResult = BettingResult.of(participant.players, BlackjackResult.of(participant.dealer, participant.players))
    ResultView.printResult(participant.dealer, participant.players, bettingResult)
}
