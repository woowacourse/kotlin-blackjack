package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun canHit(): Boolean = cardBunch.getSumOfCards() < ADD_CARD_CONDITION

    override fun getScore(): Int = cardBunch.getSumOfCards()

    fun versusPlayers(players: List<Player>): Map<Player, Consequence> {
        return players.associateWith { player -> this versusPlayer player }
    }

    private infix fun Dealer.versusPlayer(other: Player): Consequence {
        return getPlayerConsequence(other)
    }

    private fun getPlayerConsequence(player: Player): Consequence {
        return when {
            isPush(player) -> Consequence.DRAW
            this.isBlackjack() && !player.isBlackjack() -> Consequence.LOSE
            isPlayerBurst(player) && isDealerBurst() -> Consequence.LOSE
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
