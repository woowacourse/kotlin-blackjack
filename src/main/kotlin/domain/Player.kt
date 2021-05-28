package domain

import domain.card.Cards

class Player(val name: String, val bettingMoney: Int) {

    val cards = Cards()

    fun draw(vararg additionCards : String){
        for(card in additionCards){
//            cards.add(card)
        }
    }
}
