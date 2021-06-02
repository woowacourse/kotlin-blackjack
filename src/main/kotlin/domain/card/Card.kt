package domain.card

data class Card(private val number: NumberType, val shape: ShapeType) {
    val point = number.point
    val specialPoint = number.specialPoint
}
