package dev.akif.cats

import dev.akif.crud.CRUDUpdateModel

data class UpdateCat(val name: String, val age: Int) : CRUDUpdateModel
