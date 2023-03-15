package study.self.controller/*
package controller

import domain.BlackJackGame
import domain.person.Persons
import domain.result.Casino
import view.draw.DrawView
import view.onboarding.OnboardingView
import view.result.ResultView

class BlackJackController {
    fun runBlackJack() {
        val blackJackGame: BlackJackGameBluePrint = BlackJackGame()
        val persons = blackJackGame.makePersons(OnboardingView.requestInputNames())
        val bets = OnboardingView.requestInputBets(persons.players.map { it.name })

        OnboardingView.printInitialSetting(persons)
        drawPhase(blackJackGame, persons)
        ResultView.printResult(Casino(persons, bets))
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
*/
