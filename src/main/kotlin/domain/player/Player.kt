package domain.player

class Player(val name: String, val bettingMoney: Int) {

    val cards = PlayerCards()

    fun draw(vararg additionCards : String){
        for(card in additionCards){
//            cards.add(card)
        }
    }
}
