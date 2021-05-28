package domain.card

class CardDeck(private val deck:MutableList<Card> = mutableListOf()){

    init {
        for(shape in ShapeType.values()){
            for(number in NumberType.values()){
                deck.add(Card(number, shape))
            }
        }
        deck.shuffle()
    }

    fun draw() : Card{
        require(deck.isNotEmpty()) { "카드가 더 이상 존재하지 않습니다." }
        return deck.removeFirst()
    }

    fun contains(card:Card):Boolean{
        return deck.contains(card)
    }

    fun size() :Int{
        return deck.size
    }
}