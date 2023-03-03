package dev.akif.cats

import dev.akif.crud.CRUDCreateModel

data class CreateCat(val name: String, val breed: String, val age: Int) : CRUDCreateModel
