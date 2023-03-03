package dev.akif.cats

import dev.akif.crud.CRUDRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CatRepository : CRUDRepository<UUID, CatEntity>
