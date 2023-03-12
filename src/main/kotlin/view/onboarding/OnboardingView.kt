package view.onboarding

import domain.person.Persons

object OnboardingView {
    fun requestInputNames(): List<String> {
        OnboardingOutput.printRequestInputName()
        return OnboardingInput.inputNames { OnboardingOutput.printBlankError() }
    }

    fun requestInputBets(names: List<String>): List<Double> {
        val bets = mutableListOf<Double>()
        names.forEach {
            println()
            OnboardingOutput.printRequestInputBet(it)
            bets.add(OnboardingInput.inputBets { OnboardingOutput.printNotDoubleError() })
        }
        return bets
    }

    fun printInitialSetting(persons: Persons) {
        println()
        OnboardingOutput.printShareTwoCards(persons)
        OnboardingOutput.printDealerCards(persons.dealer)
        OnboardingOutput.printPlayerCards(persons.players)
        println()
    }
}
