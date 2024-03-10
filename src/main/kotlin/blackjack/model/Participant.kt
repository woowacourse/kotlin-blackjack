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

    class Player(name: ParticipantName, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation)

    class Dealer(name: ParticipantName = DEFAULT_DEALER_NAME, gameInformation: GameInformation = GameInformation()) :
        Participant(name, gameInformation) {
        companion object {
            private val DEFAULT_DEALER_NAME = ParticipantName("딜러")
        }
    }
}
