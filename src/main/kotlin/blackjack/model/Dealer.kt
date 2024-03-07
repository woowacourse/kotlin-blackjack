package blackjack.model

class Dealer(deck: Deck) {
    private val handCards = HandCards(deck)

    fun getFirstCard() =
        with(handCards.cards.first()) {
            "${cardNumber.value}${pattern.shape}"
        }

    fun getAllCard() = handCards.cards.joinToString(SPLIT_DELIMITER) { "${it.cardNumber.value}${it.pattern.shape}" }

    private fun isBust(): Boolean = handCards.calculateCardScore() > BLACKJACK_NUMBER

    fun addCard(): Boolean =
        if (isAdd()) {
            handCards.add()
            true
        } else {
            false
        }

    private fun isAdd(): Boolean = handCards.calculateCardScore() < DEALER_HIT_THRESHOLD

    fun getScore() = handCards.calculateCardScore()

    fun gameResult(players: List<Player>): Map<String, CompetitionResult> =
        players.associate { player ->
            val result = competitionResult(player)
            player.name to result
        }

    private fun competitionResult(player: Player): CompetitionResult {
        val result =
            when {
                player.isBust() -> CompetitionResult.LOSE
                isBust() -> CompetitionResult.WIN
                player.getScore() < getScore() -> CompetitionResult.LOSE
                player.getScore() > getScore() -> CompetitionResult.WIN
                else -> CompetitionResult.SAME
            }
        return result
    }

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val BLACKJACK_NUMBER = 21
        private const val DEALER_HIT_THRESHOLD = 17
    }
}
