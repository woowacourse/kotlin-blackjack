package blackjack.view

interface BlackJackInputView {
    fun getNames(): List<String>

    fun getBetAmount(name: String): Int

    fun getIsHit(name: String): Boolean
}
