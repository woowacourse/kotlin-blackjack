package view

import domain.card.Card
import domain.card.ShapeType

enum class CardSymbol(val shapeType: ShapeType) {
    다이아몬드(ShapeType.DIAMOND),
    하트(ShapeType.HEART),
    클로버(ShapeType.CLOVER),
    스페이드(ShapeType.SPADE);

    companion object {
        fun parse(card: Card): String {
            val shapeTypeAsString = values().first {
                it.shapeType == card.shape
            }
            return card.point.toString() + shapeTypeAsString.name
        }
    }
}
