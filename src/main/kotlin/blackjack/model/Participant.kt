package blackjack.model

sealed class Participant(val name: ParticipantName, val gameInformation: GameInformation) {
    fun draw(card: Card) {
        if (gameInformation.state is GameState.Running) {
            gameInformation.drawCard(card)
        }
    }

    fun changeStateToHit() {
        gameInformation.changeState(GameState.Running.HIT)
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
            private const val ADDITIONAL_DRAW_CRITERIA = 16
        }
    }
}
