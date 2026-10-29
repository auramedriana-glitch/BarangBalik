package com.example.barangbalik

data class LoanItem(
    val id: Int,
    val itemName: String,
    val borrowedBy: String,
    val isReturned: Boolean = false
)

