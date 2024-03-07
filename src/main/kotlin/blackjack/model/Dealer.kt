package blackjack.model

class Dealer(deck: Deck) {
    private val handCards = HandCards(deck)

    fun getFirstCard() =
        with(handCards.cards.first()) {
            "${cardNumber.value}${pattern.shape}"
        }

    fun getAllCard() = handCards.cards.joinToString(", ") { "${it.cardNumber.value}${it.pattern.shape}" }

    fun isBust(): Boolean = handCards.calculateCardScore() > 21

    fun addCard(): Boolean =
        if (isAdd()) {
            handCards.add()
            true
        } else {
            false
        }

    private fun isAdd(): Boolean = handCards.calculateCardScore() <= 16

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
}
