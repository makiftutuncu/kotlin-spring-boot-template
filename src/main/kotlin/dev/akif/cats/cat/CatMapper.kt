package dev.akif.cats.cat

import dev.akif.crud.*
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class CatMapper : CRUDMapper<UUID, CatEntity, Cat, CreateCat, UpdateCat> {
    override fun entityToBeCreatedFrom(createModel: CreateCat, now: Instant): CatEntity =
        CatEntity(
            id = UUID.randomUUID(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: CatEntity): Cat =
        Cat(
            id = requireNotNull(entity.id) { "CatEntity.id was null" },
            name = requireNotNull(entity.name) { "CatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "CatEntity.breed was null" },
            age = requireNotNull(entity.age) { "CatEntity.age was null" },
            version = requireNotNull(entity.version) { "CatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "CatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "CatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: CatEntity, updateModel: UpdateCat) {
        entity.apply {
            name = updateModel.name
            age = updateModel.age
        }
    }
}

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
