package blackjackgame.model.player

import blackjackgame.model.card.Card
import blackjackgame.model.card.Cards
import blackjackgame.model.result.LOSE
import blackjackgame.model.result.Result
import blackjackgame.model.result.WIN

const val BLACKJACK_SCORE = 21

class Player(name:String = "any", initialMoney:Int = 0) : Participant(name, initialMoney) {

    override fun getInitCards(): List<Card> {
        return this.cards.subList(0, 2)
    }

    private fun isBlackjack(): Boolean {
        return cards.isInitSize() && cards.calculateScore() == BLACKJACK_SCORE
    }

    override fun isPlayer(): Boolean {
        return true
    }

    fun earnMoney(result: Result) {
        if (isBlackjack() && result == WIN) {
            finalMoney = (initialMoney * 1.5).toInt()
            return
        }
        if (result == LOSE) {
            finalMoney = 0
            return
        }
        finalMoney = (initialMoney * 1.0).toInt()
    }

}
