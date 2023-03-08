package dev.akif.cats.toy

import dev.akif.crud.*
import dev.akif.crud.common.Parameters
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class ToyMapper : CRUDMapper<UUID, ToyEntity, Toy, CreateToy, UpdateToy> {
    override fun entityToBeCreatedFrom(createModel: CreateToy, now: Instant): ToyEntity =
        ToyEntity(
            id = null,
            catId = createModel.catId,
            name = createModel.name,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: ToyEntity): Toy =
        Toy(
            id = requireNotNull(entity.id) { "id was null." },
            name = requireNotNull(entity.name) { "name was null." },
            version = requireNotNull(entity.version) { "version was null." },
            createdAt = requireNotNull(entity.createdAt) { "createdAt was null." },
            updatedAt = requireNotNull(entity.updatedAt) { "updatedAt was null." },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: ToyEntity, updateModel: UpdateToy) {
        entity.apply {
            name = updateModel.name
        }
    }
}

@Component
class ToyDTOMapper : CRUDDTOMapper<UUID, Toy, ToyDTO, CreateToy, UpdateToy, CreateToyDTO, UpdateToyDTO> {
    override fun createDTOToCreateModel(createDTO: CreateToyDTO, parameters: Parameters): CreateToy =
        CreateToy(
            catId = requireNotNull(parameters.pathVariable("catId") { UUID.fromString(it) }) { "catId path parameter is missing." },
            name = createDTO.name
        )

    override fun modelToDTO(model: Toy, parameters: Parameters): ToyDTO =
        ToyDTO(
            id = model.id,
            name = model.name,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: UpdateToyDTO, parameters: Parameters): UpdateToy =
        UpdateToy(
            catId = requireNotNull(parameters.pathVariable("catId") { UUID.fromString(it) }) { "catId path parameter is missing." },
            name = updateDTO.name
        )
}
