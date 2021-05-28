package domain.player

import domain.card.Card

data class PlayerCards(val cards: List<Card> = listOf()) {

    val score = Score(cards)
    val type = score.type

    fun add(card: Card): PlayerCards {
        return PlayerCards(cards.plus(card))
    }

    fun isWin(other: PlayerCards): Boolean {
        if(this.type.isBlackJack() && !other.type.isBlackJack()){
            return true
        }

        if(!this.type.isOver() && other.type.isOver()){
            return true
        }

        if(this.type.isUnder() && other.type.isUnder()){
            return this.score.value > other.score.value
        }

        return false
    }

    fun isLose(other: PlayerCards) :Boolean{
        return other.isWin(this)
    }
}