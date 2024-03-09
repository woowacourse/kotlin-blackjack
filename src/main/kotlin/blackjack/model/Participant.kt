package blackjack.model

sealed class Participant(val name: ParticipantName, val gameInformation: GameInformation) {
    class Player(name: ParticipantName, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation)

    class Dealer(name: ParticipantName = DEFAULT_DEALER_NAME, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        fun initialCardDealing(
            participants: Participants,
            cardDeck: CardDeck,
        ) {
            repeat(INITIAL_DEALING_COUNT) {
                dealing(participants, cardDeck)
            }
            changeStateToHit(participants)
        }

        private fun dealing(
            participants: Participants,
            cardDeck: CardDeck,
        ) {
            dealCard(participants.dealer, cardDeck.pickCard())
            participants.players.forEach { player ->
                dealCard(player, cardDeck.pickCard())
            }
        }

        private fun dealCard(
            participant: Participant,
            card: Card,
        ) {
            if (participant.gameInformation.state is GameState.Running) {
                participant.gameInformation.drawCard(card)
            }
        }

        private fun changeStateToHit(participants: Participants) {
            participants.dealer.gameInformation.changeState(GameState.Running.HIT)
            participants.players.forEach { player ->
                player.gameInformation.changeState(GameState.Running.HIT)
            }
        }

        companion object {
            private val DEFAULT_DEALER_NAME = ParticipantName("딜러")
            private const val INITIAL_DEALING_COUNT = 2
        }
    }
}
