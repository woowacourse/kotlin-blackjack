package model.card

data class Card(val valueType: ValueType, val markType: MarkType) {
    companion object {
        const val ACE_ADDITIONAL_POINT = 10
    }
}
