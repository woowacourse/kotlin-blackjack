package controller

import domain.BlackJackGame
import domain.ParticipantsBuilder
import domain.card.Deck
import view.RequestView
import view.ResultView

class BlackJackController(
    private val requestView: RequestView,
    private val resultView: ResultView,
) {
    fun runBlackJack() {
        val deck = Deck.create()
        val participants =
            ParticipantsBuilder(deck).getInitialPersons(requestView::requestInputNames, resultView::printInitialSetting)
        val blackJackGame = BlackJackGame(deck, participants)

        blackJackGame.handOutCardsToPlayers(requestView::requestPlayerDecision, resultView::printPlayerCards)
        blackJackGame.handOutCardsToDealer(resultView::printDealerGetCardOrNot)
        blackJackGame.judgeResult(resultView::printPersonsCardsResult, resultView::printFinalResult)
    }
}
