package blackjack.domain.model.participant

class Player(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun play() {
        // todo
    }

    override fun isDrawFinish(): Boolean = handCards.getStatus() == CardStatus.BUST

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
