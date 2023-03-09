package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun canGetCard(): Boolean = cardBunch.getSumOfCards() < ADD_CARD_CONDITION

    override fun getScore(): Int = cardBunch.getSumOfCards()

    private fun versusPlayer(player: Player): Consequence {
        val dealerScore = cardBunch.getSumOfCards()
        val playerScore = player.getScore()
        return getPlayerConsequence(dealerScore, playerScore)
    }

    fun versusPlayers(players: List<Player>): Map<String, Consequence> {
        val gameResult = mutableMapOf<String, Consequence>()
        players.forEach { player ->
            gameResult[player.name] = versusPlayer(player)
        }
        return gameResult
    }

    private fun getPlayerConsequence(dealerScore: Int, playerScore: Int): Consequence {
        return when {
            isDealerBurst(dealerScore) -> Consequence.WIN
            isPlayerBurst(playerScore) -> Consequence.LOSE
            isPush(dealerScore, playerScore) -> Consequence.DRAW
            isDraw(dealerScore, playerScore) -> Consequence.DRAW
            isDealerBlackjack(dealerScore) && !isPlayerBlackjack(playerScore) -> Consequence.LOSE
            isDealerWin(dealerScore, playerScore) -> Consequence.LOSE
            else -> Consequence.WIN
        }
    }

    private fun isPlayerBurst(playerScore: Int): Boolean = playerScore > MAX_SCORE_CONDITION

    private fun isDealerBurst(dealerScore: Int): Boolean = dealerScore > MAX_SCORE_CONDITION

    private fun isPush(dealerScore: Int, playerScore: Int): Boolean =
        isDealerBlackjack(dealerScore) && isPlayerBlackjack(playerScore)

    private fun isDraw(dealerScore: Int, playerScore: Int): Boolean = dealerScore == playerScore

    private fun isDealerBlackjack(dealerScore: Int): Boolean = dealerScore == MAX_SCORE_CONDITION

    private fun isPlayerBlackjack(playerScore: Int): Boolean = playerScore == MAX_SCORE_CONDITION

    private fun isDealerWin(dealerScore: Int, playerScore: Int): Boolean = dealerScore > playerScore

    companion object {
        private const val ADD_CARD_CONDITION = 17
    }
}
