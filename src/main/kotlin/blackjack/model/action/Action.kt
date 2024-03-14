package blackjack.model.action

import blackjack.model.domain.ParticipantInfo
import blackjack.model.entitiy.Card

sealed interface Action {
    data class ReadNames(val names: List<String>) : Action

    data class InitDealerCard(
        val count: Int = 2,
        val onDrawCard: () -> Card,
    ) : Action

    data class InitPlayersCard(
        val count: Int = 2,
        val onDrawCard: () -> Card,
    ) : Action

    data class DrawUntilPlayersSatisfaction(
        val onDrawCard: () -> Card,
        val onPrintCards: (ParticipantInfo) -> Unit,
        val onInputDecision: (String) -> String,
    ) : Action

    data class DrawUntilDealerSatisfaction(
        val onDrawCard: () -> Card,
        val onPrintCards: (ParticipantInfo) -> Unit,
    ) : Action
}
