package blackjack.domain.player

class Dealer(name: String) : Player(name) {

    fun checkMustGenerateCard(): Boolean {
        if (cards.sumCardsNumber() <= MIN_SUM_NUMBER) return true
        return false
    }

    fun decideParticipantsResult(participants: Participants) {
        participants.values.forEach {
            it.updateResult(cards.sumCardsNumber())
        }
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
