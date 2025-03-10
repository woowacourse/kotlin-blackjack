package blackjack.domain.model.participant

import blackjack.domain.model.progress.Rule

class Player(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun play() {
        // todo
    }

    override fun isDrawFinish(): Boolean = Rule.isBurst(showCards())

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
