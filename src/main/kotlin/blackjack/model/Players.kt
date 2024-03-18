package blackjack.model

class Players(val players: List<Player>) {
    fun hitOrStay(
        dealingShoe: DealingShoe,
        askPickAgain: (String) -> Boolean,
        printCards: (Participant) -> Unit,
    ) {
        players.forEach { player ->
            player.hitOrStay(dealingShoe, askPickAgain, printCards)
        }
    }

    fun initCard(dealingShoe: DealingShoe) {
        players.forEach { player ->
            player.pickCard(dealingShoe, 2)
        }
    }

    fun getAmountStatistics(dealer: Dealer): Map<String, Long> {
        val dealerAmount = getDealerSum(dealer)
        val playerAmount = getEachPlayersAmount(dealer)
        return dealerAmount + playerAmount
    }

    private fun getDealerSum(dealer: Dealer): Map<String, Long> {
        val amount =
            players.sumOf { player ->
                player.calculateBetAmount(dealer)
            }
        return mapOf("딜러" to -amount)
    }

    private fun getEachPlayersAmount(dealer: Dealer): Map<String, Long> {
        return players.associate { player ->
            player.name to player.calculateBetAmount(dealer)
        }
    }

    companion object {
        fun of(
            names: List<String>,
            betAmount: List<Long>,
        ): Players {
            val players =
                names.zip(betAmount) { name, amount ->
                    Player(name, amount)
                }
            return Players(players)
        }
    }
}
