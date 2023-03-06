package model

class CardPack(cards: List<Card>) : Cards(cards) {
    fun pop(): Card {
        require(cards.isNotEmpty()) { OUT_OF_INDEX_CARDS_CURSOR }
        return cards.pop()
    }

    fun shuffled() = CardPack(cards.shuffled())

    companion object {
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
