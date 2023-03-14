package view.onboarding

import domain.person.Persons

object OnboardingView {
    fun requestInputNames(): List<String> {
        OnboardingOutput.printRequestInputName()
        return OnboardingInput.inputNames { OnboardingOutput.printBlankError() }
    }

    fun requestInputBet(name: String): Double {
        println()
        OnboardingOutput.printRequestInputBet(name)
        return OnboardingInput.inputBets { OnboardingOutput.printNotDoubleError() }
    }

    fun printInitialSetting(persons: Persons) {
        println()
        OnboardingOutput.printShareTwoCards(persons)
        OnboardingOutput.printDealerCards(persons.dealer)
        OnboardingOutput.printPlayerCards(persons.players)
        println()
    }
}
