package controller

import domain.BlackJackGame
import domain.person.Persons
import view.draw.DrawView
import view.onboarding.OnboardingView
import view.result.ResultView

class BlackJackController {
    fun runBlackJack() {
        val blackJackGame: BlackJackGameBluePrint = BlackJackGame()
        val persons = blackJackGame.makePersons(OnboardingView.requestInputNames())

        OnboardingView.printInitialSetting(persons)
        drawPhase(blackJackGame, persons)
        ResultView.printResult(persons, blackJackGame.drawResult(persons))
    }

    private fun drawPhase(blackJackGame: BlackJackGameBluePrint, persons: Persons) {
        runCatching {
            blackJackGame.handOutCardsToPlayers(
                persons.players,
                { DrawView.askPlayerDraw(it) },
                { DrawView.printPlayerCards(it) },
            )
            blackJackGame.handOutCardsToDealer(
                persons.dealer,
                { DrawView.printDealerDrew() },
                { DrawView.printDealerDidNotDrew() },
            )
        }.onFailure {
            println(it.message)
            println()
        }
    }
}
