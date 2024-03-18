package model.participants

data class Wallet(val idCard: IdCard = IdCard.fromInput("None"), val money: Money = Money())
