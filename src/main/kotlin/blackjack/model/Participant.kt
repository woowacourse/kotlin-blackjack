package blackjack.model

sealed class Participant(val name: ParticipantName, val gameInformation: GameInformation) {
    class Player(name: ParticipantName, gameInformation: GameInformation = GameInformation()) : Participant(name, gameInformation)

    class Dealer(name: ParticipantName = DEFAULT_DEALER_NAME, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        fun initialCardDealing(
            participants: Participants,
            cardDeck: CardDeck,
        ) {
            val initialParticipants = makeInitialParticipants(participants)
            repeat(INITIAL_DEALING_COUNT) {
                initialParticipants.forEach { participant ->
                    dealCard(participant, cardDeck.pickCard())
                }
            }
        }

        private fun makeInitialParticipants(participants: Participants): MutableList<Participant> {
            val initialParticipants = mutableListOf<Participant>()
            initialParticipants.add(participants.dealer)
            initialParticipants.addAll(participants.players)
            return initialParticipants
        }

        private fun dealCard(
            participant: Participant,
            card: Card,
        ) {
            if (participant.gameInformation.state is GameState.Running.Hit) {
                participant.gameInformation.drawCard(card)
            }
        }

        companion object {
            private val DEFAULT_DEALER_NAME = ParticipantName("딜러")
            private const val INITIAL_DEALING_COUNT = 2
        }
    }
}
