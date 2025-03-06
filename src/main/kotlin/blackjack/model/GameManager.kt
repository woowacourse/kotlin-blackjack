package blackjack.model

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun dealInitialCardWithCount(count: Int) {
        dealer.addCards(Deck.drawWithCount(count))
        players.forEach { player -> player.addCards(Deck.drawWithCount(count)) }
    }

    fun calculateResultMap(): Map<Player, String> {
        val dealerScore = dealer.sumScore()
        val resultMap = mutableMapOf<Player, String>()

        players.forEach { player ->
            if (player.adjustScore() > dealerScore) resultMap[player] = "승"
            else resultMap[player] = "패"
        }

        return resultMap.toMap()
    }
}
