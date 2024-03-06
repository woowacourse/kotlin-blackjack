package model

enum class Mark(name: String, val order: Int) {
    SPADE("스페이드", 0),
    CLOVER("클로버", 1),
    HEART("하트", 2),
    DIAMOND("다이아몬드", 3),
}
