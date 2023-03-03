package dev.akif.cats

import dev.akif.crud.CRUDCreateDTO

data class CreateCatDTO(val name: String, val breed: String, val age: Int) : CRUDCreateDTO
