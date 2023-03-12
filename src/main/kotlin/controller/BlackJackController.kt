package controller

import domain.BlackJackGame
import domain.card.Deck
import domain.person.Participants
import domain.result.GameProfit
import view.RequestView
import view.ResultView

class BlackJackController(
    private val requestView: RequestView,
    private val resultView: ResultView,
) {
    fun runBlackJack() {
        val participants = Participants.from(requestView::requestInputNames)
        val gameProfit = GameProfit.from(participants, requestView::requestBettingMoneys)
        val blackJackGame = BlackJackGame(Deck.create(), participants)

        blackJackGame.handOutCardsInitial(resultView::printInitialSetting)
        blackJackGame.handOutCardsToPlayers(requestView::isMoreCard, resultView::printPlayerCards)
        blackJackGame.handOutCardsToDealer(resultView::printDealerGetCardOrNot)

        blackJackGame.judgeResult(
            gameProfit,
            resultView::printPersonsCardsResult,
            resultView::printFinalResult,
        )
    }
}
