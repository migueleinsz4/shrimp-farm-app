package com.cargill.challenge.shrimp.model

import javax.persistence.*

@Entity
@Table(name = "farm")
class Farm (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_generator")
    @SequenceGenerator(name = "farm_generator", sequenceName = "farm_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null,
    @Column(name = "name", nullable = false, length = 256)
    var name: String,
    @ManyToOne
    @JoinColumn(name = "id_farmer", nullable = false)
    var farmer: Farmer,
    @OneToMany(mappedBy = "farm", cascade = [CascadeType.ALL])
    var ponds: MutableList<Pond>? = null,
    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false
)