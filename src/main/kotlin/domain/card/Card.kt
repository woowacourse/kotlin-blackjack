package domain.card

data class Card(val number: NumberType, val shape: ShapeType) {
    val point = number.point
    val specialPoint = number.specialPoint
}