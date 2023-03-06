package view

import model.Rank

class RankModel {
    fun getString(rank: Rank): String = when (rank) {
        Rank.ACE -> ACE
        Rank.KING -> KING
        Rank.JACK -> JACK
        Rank.QUEEN -> QUEEN
        else -> rank.getScore().toString()
    }

    companion object {
        private const val ACE = "A"
        private const val KING = "K"
        private const val JACK = "J"
        private const val QUEEN = "Q"
    }
}
