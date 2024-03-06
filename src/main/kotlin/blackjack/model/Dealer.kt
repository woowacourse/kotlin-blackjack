package blackjack.model

class Dealer(deck: Deck) {
    private val playerCards = PlayerCards(deck)

    fun getFirstCard() = playerCards.cards.first()

    fun getAllCard() = playerCards.cards

    fun isAdd(): Boolean = playerCards.calculateCardScore() <= 16

    fun isBust(): Boolean = playerCards.calculateCardScore() > 21

    fun add() = playerCards.add()

    fun getScore() = playerCards.calculateCardScore()

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
                player.playerCards.calculateCardScore() < getScore() -> CompetitionResult.LOSE
                player.playerCards.calculateCardScore() > getScore() -> CompetitionResult.WIN
                else -> CompetitionResult.SAME
            }
        return result
    }
}
