package blackjack.model

interface EventProvider {
    fun getIsDrawMore(name: String): Boolean

    fun getBetAmount(name: String): Int

    fun getNames(): List<String>
}
