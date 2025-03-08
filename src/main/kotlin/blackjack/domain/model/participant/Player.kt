package blackjack.domain.model.participant

class Player(
    name: String = DEFAULT_NAME,
) : Participant(name = name) {
    override fun isDrawable(): Boolean = handCards.isBurst()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
