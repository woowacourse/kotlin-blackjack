package blackjack.model

interface EventProvider {
    fun getIsDrawMore(name:String):Boolean
}
