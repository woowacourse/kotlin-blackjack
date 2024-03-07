package blackjack.model

class Dealer(deck: Deck) {
    private val handCards = HandCards(deck)

    fun getFirstCard() = with(handCards.cards.first()){
        "${cardNumber}${pattern}"
    }

    fun getAllCard() = handCards.cards.joinToString(", ") { "${it.cardNumber}${it.pattern}" }

    fun isAdd(): Boolean = handCards.calculateCardScore() <= 16

    fun isBust(): Boolean = handCards.calculateCardScore() > 21

    fun add() = handCards.add()

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
                player.handCards.calculateCardScore() < getScore() -> CompetitionResult.LOSE
                player.handCards.calculateCardScore() > getScore() -> CompetitionResult.WIN
                else -> CompetitionResult.SAME
            }
        return result
    }
}
