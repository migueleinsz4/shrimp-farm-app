package com.cargill.challenge.shrimp.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pond")
class Pond (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pond_generator")
    @SequenceGenerator(name = "pond_generator", sequenceName = "pond_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null,
    @Column(name = "name", nullable = false, length = 256)
    var name: String,
    @Column(name = "size", nullable = false, precision = 10, scale = 2)
    var size: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "id_farm", nullable = false)
    var farm: Farm,
    @Column(name = "stock_date")
    var stockDate: LocalDateTime? = null,
    @Column(name = "last_feed_date")
    var lastFeedDate: LocalDateTime? = null,
    @Column(name = "harvest_date")
    var harvestDate: LocalDateTime? = null,
    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false
)