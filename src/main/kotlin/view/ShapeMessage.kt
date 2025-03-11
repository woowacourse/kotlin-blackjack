package view

import model.Shape

object ShapeMessage {
    private val messages =
        mapOf(
            Shape.HEART to "하트",
            Shape.CLUB to "클로버",
            Shape.SPADE to "스페이드",
            Shape.DIAMOND to "다이아몬드",
        )

    fun getShapeMessage(shape: Shape): String {
        return messages[shape] ?: ""
    }
}
