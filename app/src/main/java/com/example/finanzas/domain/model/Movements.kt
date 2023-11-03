package com.example.finanzas.domain.model

import java.util.Date

data class Movements(
    val id: Int = 0,
    val value: String,
    val category_id: String,
    val user_id: String,
)