package controller

import domain.BlackJackGame
import view.draw.DrawView
import view.onboarding.OnboardingView
import view.result.ResultView

class BlackJackController {
    fun runBlackJack() {
        val blackJackGame: BlackJackGameBluePrint = BlackJackGame()
        val persons = blackJackGame.makePersons(OnboardingView.requestInputNames())
        OnboardingView.printInitialSetting(persons)
        blackJackGame.handOutCardsToPlayers(persons.players, { DrawView.askPlayerDraw(it) }, { DrawView.printPlayerCards(it) })
        blackJackGame.handOutCardsToDealer(persons.dealer, { DrawView.printDealerDrew() }, { DrawView.printDealerDidNotDrew() })
        ResultView.printResult(persons, blackJackGame.drawResult(persons))
    }
}
