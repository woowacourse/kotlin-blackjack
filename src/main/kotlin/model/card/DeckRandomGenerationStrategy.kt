package model.card

class DeckRandomGenerationStrategy : DeckGenerationStrategy {
    override fun generate(): List<Card> {
        return MarkType.entries.flatMap { markType ->
            associateMark(markType) // List<Card>
        }
            .shuffled()
            .toMutableList()
    }

    private fun associateMark(markType: MarkType): List<Card> {
        return ValueType.entries.map { valueType ->
            Card(valueType, markType)
        }
    }
}
