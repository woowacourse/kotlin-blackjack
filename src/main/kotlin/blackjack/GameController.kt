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
    val participant = BlackjackParticipant.of(
        dealer = Dealer(),
        names = names,
        money = InputView::getBettingMoney
    )
    val game = BlackjackGame()

    game.startGame(participant = participant, outcome = ResultView::printSetUp)
    game.runPlayer(
        players = participant.players,
        isHit = InputView::doesPlayerWantHit,
        outcome = ResultView::printCards
    )
    game.runDealer(dealer = participant.dealer, outcome = ResultView::printDealerHitMessage)

    val bettingResult = BettingResult.of(
        players = participant.players,
        gameResult = BlackjackResult.of(participant.dealer, participant.players)
    )
    ResultView.printResult(dealer = participant.dealer, players = participant.players, bettingResult = bettingResult)
}
