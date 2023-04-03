package dev.akif.cats.cat

import dev.akif.cats.toy.ToyDTOMapper
import dev.akif.cats.toy.ToyMapper
import dev.akif.crud.*
import dev.akif.crud.common.Parameters
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class CatMapper(private val toyMapper: ToyMapper) : CRUDMapper<UUID, CatEntity, Cat, CreateCat, UpdateCat> {
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
            id = requireNotNull(entity.id) { "id was null." },
            name = requireNotNull(entity.name) { "name was null." },
            breed = requireNotNull(entity.breed) { "breed was null." },
            age = requireNotNull(entity.age) { "age was null." },
            toys = entity.toys.map { toyMapper.entityToModel(it) },
            version = requireNotNull(entity.version) { "version was null." },
            createdAt = requireNotNull(entity.createdAt) { "createdAt was null." },
            updatedAt = requireNotNull(entity.updatedAt) { "updatedAt was null." },
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
class CatDTOMapper(private val toyDTOMapper: ToyDTOMapper) : CRUDDTOMapper<UUID, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO> {
    override fun createDTOToCreateModel(createDTO: CreateCatDTO, parameters: Parameters): CreateCat =
        CreateCat(
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age
        )

    override fun modelToDTO(model: Cat, parameters: Parameters): CatDTO =
        CatDTO(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            toys = model.toys.map { toyDTOMapper.modelToDTO(it, parameters) },
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: UpdateCatDTO, parameters: Parameters): UpdateCat =
        UpdateCat(
            name = updateDTO.name,
            age = updateDTO.age
        )
}
