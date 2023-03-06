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
        if (dealerScore > MAX_SCORE_CONDITION) return Consequence.WIN
        if (playerScore > MAX_SCORE_CONDITION) return Consequence.LOSE
        if (dealerScore == MAX_SCORE_CONDITION && playerScore != MAX_SCORE_CONDITION) return Consequence.LOSE
        if (playerScore > dealerScore) return Consequence.WIN
        if (playerScore == dealerScore) return Consequence.DRAW
        return Consequence.LOSE
    }

    companion object {
        private const val ADD_CARD_CONDITION = 17
    }
}
