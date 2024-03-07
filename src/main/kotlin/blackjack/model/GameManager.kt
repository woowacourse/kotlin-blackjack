package blackjack.model

class GameManger {
    private val cardDeck = CardDeck()

    fun setGame(participants: Participants) {
        repeat(INIT_HAND_CARD_COUNT) {
            participants.getParticipants().forEach { participant ->
                participant.draw(cardDeck.draw())
            }
        }
    }

    fun applyUserDrawDecision(participant: Participant) {
        participant.draw(cardDeck.draw())
    }

    companion object {
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
