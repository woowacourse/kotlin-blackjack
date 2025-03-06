package blackjack.domain.model

class Player(name: String = DEFAULT_NAME) : GameParticipant(name = name) {
    override fun drawCard() {
        cards += Deck.giveCard()
    }

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
