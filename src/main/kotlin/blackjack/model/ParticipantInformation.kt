package blackjack.model

sealed class ParticipantInformation(val name: ParticipantName) {
    class PlayerInformation(name: ParticipantName, val bettingAmount: BettingAmount) : ParticipantInformation(name)

    class DealerInformation(name: ParticipantName = ParticipantName(DEFAULT_DEALER_NAME)) :
        ParticipantInformation(name) {
        companion object {
            private const val DEFAULT_DEALER_NAME = "딜러"
        }
    }
}
