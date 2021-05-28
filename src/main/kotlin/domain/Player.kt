package domain

class Player(val name: String, val bettingMoney: Int) {

    val cards = mutableListOf<String>()

    fun draw(vararg additionCards : String){
        for(card in additionCards){
            cards.add(card)
        }
    }
}
