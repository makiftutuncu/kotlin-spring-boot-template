package dev.akif.cats.toy

import dev.akif.crud.InMemoryCRUDRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

object InMemoryToyRepository: InMemoryCRUDRepository<Long, ToyEntity, CreateToy, ToyTestData>(ToyTestData), ToyRepository {
    override fun findAllByDeletedAtIsNull(pageable: Pageable): Page<ToyEntity> =
        super<ToyRepository>.findAllByDeletedAtIsNull(pageable)

    override fun findByIdAndDeletedAtIsNull(id: Long): ToyEntity? =
        super<ToyRepository>.findByIdAndDeletedAtIsNull(id)

    override fun findByIdAndCatIdAndDeletedAtIsNull(id: Long, catId: UUID): ToyEntity? =
        entities[id]?.takeIf { it.catId == catId && it.deletedAt == null }?.let(testData::copy)

    override fun findAllByCatIdAndDeletedAtIsNull(catId: UUID, pageable: Pageable): Page<ToyEntity> =
        PageImpl(
            entities
                .values
                .stream()
                .filter { it.catId == catId && it.deletedAt == null }
                .skip(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .map(testData::copy)
                .toList(),
            pageable,
            entities.count { (_, e) -> e.catId == catId && e.deletedAt == null }.toLong()
        )
}
