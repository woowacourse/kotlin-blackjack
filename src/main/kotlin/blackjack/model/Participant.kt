package blackjack.model

sealed class Participant(val name: ParticipantName, val gameInformation: GameInformation) {
    fun draw(card: Card) {
        if (gameInformation.state is GameState.Running) {
            gameInformation.drawCard(card)
        }
    }

    abstract fun additionalDraw(
        cardDeck: CardDeck,
        output: (Participant) -> Unit,
    )

    class Player(name: ParticipantName, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        override fun additionalDraw(
            cardDeck: CardDeck,
            output: (Participant) -> Unit,
        ) {
            draw(cardDeck.pickCard())
            output(this)
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

        override fun additionalDraw(
            cardDeck: CardDeck,
            output: (Participant) -> Unit,
        ) {
            while (gameInformation.score <= ADDITIONAL_DRAW_CRITERIA) {
                draw(cardDeck.pickCard())
                output(this)
            }
            if (gameInformation.score < 21) {
                gameInformation.changeState(GameState.Finished.STAY)
            }
        }

        companion object {
            private val DEFAULT_DEALER_NAME = ParticipantName("딜러")
            private const val INITIAL_DEALING_COUNT = 2
            private const val ADDITIONAL_DRAW_CRITERIA = 16
        }
    }
}
