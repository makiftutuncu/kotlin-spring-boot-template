package dev.akif.cats.toy

import dev.akif.crud.CRUDRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ToyRepository : CRUDRepository<UUID, ToyEntity>
