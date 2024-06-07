package com.example.verdemar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Tarefas (
    val id: String? = "",
    val nomePraia: String?,
    val descricao: String?,
    val data: String?,
    val urlFoto: String?,
    val status: String?
): Parcelable


