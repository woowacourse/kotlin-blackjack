package controller

import domain.BlackJackBuilder
import domain.BlackJackGame
import domain.card.Deck
import view.RequestView
import view.ResultView

class BlackJackController(
    private val requestView: RequestView,
    private val resultView: ResultView,
) {
    fun runBlackJack() {
        val deck = Deck.create()
        val blackJackBuilder = BlackJackBuilder(deck)

        val participants =
            blackJackBuilder.getInitialPersons(requestView::requestInputNames)

        val playerBettingMoneys =
            blackJackBuilder.getPlayerBettingMoneys(participants, requestView::requestBettingMoneys)

        blackJackBuilder.handOutInitialCards(participants, resultView::printInitialSetting)

        val blackJackGame = BlackJackGame(deck, participants)

        blackJackGame.handOutCardsToPlayers(requestView::requestPlayerDecision, resultView::printPlayerCards)
        blackJackGame.handOutCardsToDealer(resultView::printDealerGetCardOrNot)
        blackJackGame.judgeResult(
            playerBettingMoneys,
            resultView::printPersonsCardsResult,
            resultView::printFinalResult,
        )
    }
}
