package blackjack.model

sealed class Participant(val name: ParticipantName, val gameInformation: GameInformation) {
    fun draw(card: Card) {
        if (isRunning()) {
            gameInformation.drawCard(card)
        }
    }

    fun isRunning(): Boolean {
        return gameInformation.state is GameState.Running
    }

    class Player(name: ParticipantName, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            readDecision: () -> Boolean,
            output: () -> Unit,
        ) {
            while (isRunning()) {
                if (readDecision()) {
                    draw(cardDeck.pickCard())
                    output()
                } else {
                    gameInformation.changeState(GameState.Finished.STAY)
                }
            }
        }
    }

    class Dealer(name: ParticipantName = DEFAULT_DEALER_NAME, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        fun initialCardDealing(
            participants: Participants,
            cardDeck: CardDeck,
        ) {
            repeat(INITIAL_DEALING_COUNT) {
                participants.participants.forEach { participant ->
                    participant.draw(cardDeck.pickCard())
                }
            }
        }

        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            output: () -> Unit,
        ) {
            while (gameInformation.score <= ADDITIONAL_DRAW_CRITERIA) {
                draw(cardDeck.pickCard())
                output()
            }
            if (gameInformation.score < BLACKJACK_SCORE) {
                gameInformation.changeState(GameState.Finished.STAY)
            }
        }

        companion object {
            private val DEFAULT_DEALER_NAME = ParticipantName("딜러")
            private const val INITIAL_DEALING_COUNT = 2
            private const val ADDITIONAL_DRAW_CRITERIA = 16
            private const val BLACKJACK_SCORE = 21
        }
    }
}
