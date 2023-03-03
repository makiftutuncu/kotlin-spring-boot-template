package dev.akif.cats

import dev.akif.crud.CRUDDTOMapper
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CatDTOMapper : CRUDDTOMapper<UUID, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO> {
    override fun createDTOToCreateModel(createDTO: CreateCatDTO): CreateCat =
        CreateCat(
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age
        )

    override fun modelToDTO(model: Cat): CatDTO =
        CatDTO(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: UpdateCatDTO): UpdateCat =
        UpdateCat(
            name = updateDTO.name,
            age = updateDTO.age
        )
}
