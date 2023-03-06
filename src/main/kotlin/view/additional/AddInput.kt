package view.additional

import domain.Decision

object AddInput {
    fun inputDecision(name: String): String {
        val input = readln().trim()
        if (Decision.of(input) == null) return AdditionalCardView.requestPlayerDecision(name)
        return input
    }
}
