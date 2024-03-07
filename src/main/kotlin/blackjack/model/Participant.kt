package blackjack.model

abstract class Participant(
    private val name : String,
    private val blackJack: BlackJack = BlackJack()
) {

    fun draw(card: Card){
        blackJack.addCard(card)
    }

    fun checkHitState(): Boolean{
        return blackJack.checkDrawState()
    }

    fun getName(): String{
        return name
    }

    fun getCards(): Set<Card>{
        return blackJack.getCards()
    }

    fun getBlackJackScore(): Int {
        return blackJack.getHandCardScore()
    }
}
