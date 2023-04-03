package dev.akif.cats.toy

import dev.akif.crud.CRUDRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.lang.UnsupportedOperationException
import java.util.UUID

@Repository
interface ToyRepository : CRUDRepository<Long, ToyEntity> {
    @Deprecated(
        "Use findByIdAndCatIdAndDeletedAtIsNull() instead.",
        ReplaceWith("findByIdAndCatIdAndDeletedAtIsNull(id, catId, pageable)")
    )
    override fun findAllByDeletedAtIsNull(pageable: Pageable): Page<ToyEntity> =
        throw UnsupportedOperationException("Use findByIdAndCatIdAndDeletedAtIsNull() instead.")

    @Deprecated(
        "Use findAllByCatIdAndDeletedAtIsNull() instead.",
        ReplaceWith("findAllByCatIdAndDeletedAtIsNull(catId, pageable)")
    )
    override fun findByIdAndDeletedAtIsNull(id: Long): ToyEntity? =
        throw UnsupportedOperationException("Use findAllByCatIdAndDeletedAtIsNull() instead.")

    fun findByIdAndCatIdAndDeletedAtIsNull(id: Long, catId: UUID): ToyEntity?

    fun findAllByCatIdAndDeletedAtIsNull(catId: UUID, pageable: Pageable): Page<ToyEntity>
}
