package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun canHit(): Boolean = cardBunch.getSumOfCards() < ADD_CARD_CONDITION

    override fun getScore(): Int = cardBunch.getSumOfCards()

    private fun versusPlayer(player: Player): Consequence {
        return getPlayerConsequence(player)
    }

    fun versusPlayers(players: List<Player>): Map<Player, Consequence> {
        val gameResult = mutableMapOf<Player, Consequence>()
        players.forEach { player ->
            gameResult[player] = versusPlayer(player)
        }
        return gameResult
    }

    private fun getPlayerConsequence(player: Player): Consequence {
        return when {
            isPush(player) -> Consequence.DRAW
            this.isBlackjack() && !player.isBlackjack() -> Consequence.LOSE
            isDealerBurst() -> Consequence.WIN
            isPlayerBurst(player) -> Consequence.LOSE
            isDraw(player) -> Consequence.DRAW
            isDealerWin(player) -> Consequence.LOSE
            else -> Consequence.WIN
        }
    }

    private fun isPlayerBurst(player: Player): Boolean = player.getScore() > MAX_SCORE_CONDITION

    private fun isDealerBurst(): Boolean = this.getScore() > MAX_SCORE_CONDITION

    private fun isPush(player: Player): Boolean = this.isBlackjack() && player.isBlackjack()

    private fun isDraw(player: Player): Boolean = this.getScore() == player.getScore()

    private fun isDealerWin(player: Player): Boolean = this.getScore() > player.getScore()

    companion object {
        private const val ADD_CARD_CONDITION = 17
    }
}
