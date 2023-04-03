package dev.akif.cats.toy

import dev.akif.crud.CRUDController
import dev.akif.crud.CRUDController.*
import dev.akif.crud.common.Paged
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cats/{catId}/toys")
@Tag(name = "Toys", description = "CRUD operations for toys of cats")
class ToyController(service: ToyService, mapper: ToyDTOMapper) :
    CRUDController<Long, ToyEntity, Toy, ToyDTO, CreateToy, UpdateToy, CreateToyDTO, UpdateToyDTO, ToyMapper, ToyDTOMapper, ToyRepository, ToyService>(
        typeName = "Toy",
        service = service,
        mapper = mapper
    ) {
    @Parameter(
        name = "catId",
        `in` = ParameterIn.PATH,
        description = "Id of the cat to which the toy belongs",
        required = true,
        schema = Schema(type = "string", format = "uuid")
    )
    override fun create(
        @Parameter(
            name = "createDTO",
            description = CREATE_DTO_DESCRIPTION,
            required = true
        )
        @RequestBody
        createDTO: CreateToyDTO,
        @Parameter(hidden = true)
        @PathVariable
        pathVariables: Map<String, String>,
        request: HttpServletRequest
    ): ToyDTO =
        super.create(createDTO, pathVariables, request)

    @Parameter(
        name = "catId",
        `in` = ParameterIn.PATH,
        description = "Id of the cat to which the toy belongs",
        required = true,
        schema = Schema(type = "string", format = "uuid")
    )
    override fun list(
        @Parameter(
            name = "page",
            description = PAGE_DESCRIPTION,
            required = false
        )
        @RequestParam(
            name = "page",
            required = false,
            defaultValue = "0"
        )
        page: Int,
        @Parameter(
            name = "perPage",
            description = PER_PAGE_DESCRIPTION,
            required = false
        )
        @RequestParam(
            name = "perPage",
            required = false,
            defaultValue = "20"
        )
        perPage: Int,
        @Parameter(hidden = true)
        @PathVariable
        pathVariables: Map<String, String>,
        request: HttpServletRequest
    ): Paged<ToyDTO> =
        super.list(page, perPage, pathVariables, request)

    @Parameter(
        name = "catId",
        `in` = ParameterIn.PATH,
        description = "Id of the cat to which the toy belongs",
        required = true,
        schema = Schema(type = "string", format = "uuid")
    )
    override fun get(
        @Parameter(
            name = "id",
            `in` = ParameterIn.PATH,
            description = GET_ID_DESCRIPTION,
            required = true
        )
        @PathVariable
        id: Long,
        @Parameter(hidden = true)
        @PathVariable
        pathVariables: Map<String, String>,
        request: HttpServletRequest
    ): ToyDTO =
        super.get(id, pathVariables, request)

    @Parameter(
        name = "catId",
        `in` = ParameterIn.PATH,
        description = "Id of the cat to which the toy belongs",
        required = true,
        schema = Schema(type = "string", format = "uuid")
    )
    override fun update(
        @Parameter(
            name = "id",
            `in` = ParameterIn.PATH,
            description = UPDATE_ID_DESCRIPTION,
            required = true
        )
        @PathVariable
        id: Long,
        @Parameter(
            name = "updateDTO",
            description = UPDATE_DTO_DESCRIPTION,
            required = true
        )
        @RequestBody
        updateDTO: UpdateToyDTO,
        @Parameter(hidden = true)
        @PathVariable
        pathVariables: Map<String, String>,
        request: HttpServletRequest
    ): ToyDTO =
        super.update(id, updateDTO, pathVariables, request)

    @Parameter(
        name = "catId",
        `in` = ParameterIn.PATH,
        description = "Id of the cat to which the toy belongs",
        required = true,
        schema = Schema(type = "string", format = "uuid")
    )
    override fun delete(
        @Parameter(
            name = "id",
            `in` = ParameterIn.PATH,
            description = DELETE_ID_DESCRIPTION,
            required = true
        )
        @PathVariable
        id: Long,
        @Parameter(hidden = true)
        @PathVariable
        pathVariables: Map<String, String>,
        request: HttpServletRequest
    ) {
        super.delete(id, pathVariables, request)
    }
}
