package view.additional

import domain.person.Player

object AdditionalCardView {
    fun requestPlayerDecision(name: String): String {
        AddOutput.printRequestDecision(name)
        return AddInput.inputDecision(name)
    }

    fun printPlayerCards(player: Player) {
        AddOutput.printInitialCards(player)
    }

    fun printDealerGetMoreCard() {
        AddOutput.printDealerOneMoreCard()
        println()
    }

    fun printDealerNoMoreCard() {
        AddOutput.printDealerNoMoreCard()
        println()
    }
}
