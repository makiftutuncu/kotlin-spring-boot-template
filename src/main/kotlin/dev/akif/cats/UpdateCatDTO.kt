package dev.akif.cats

import dev.akif.crud.CRUDUpdateDTO

data class UpdateCatDTO(val name: String, val age: Int) : CRUDUpdateDTO
