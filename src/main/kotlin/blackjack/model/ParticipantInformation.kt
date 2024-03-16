package blackjack.model

sealed class ParticipantInformation(open val name: ParticipantName) {
    data class PlayerInformation(override val name: ParticipantName, val bettingAmount: BettingAmount) : ParticipantInformation(name)

    data class DealerInformation(override val name: ParticipantName = ParticipantName(DEFAULT_DEALER_NAME)) :
        ParticipantInformation(name) {
        companion object {
            private const val DEFAULT_DEALER_NAME = "딜러"
        }
    }
}
