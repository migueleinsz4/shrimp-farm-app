package com.cargill.challenge.shrimp.model

import javax.persistence.*

@Entity
@Table(name = "farmer")
class Farmer(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farmer_generator")
    @SequenceGenerator(name = "farmer_generator", sequenceName = "farmer_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null,
    @Column(name = "name", nullable = false, length = 256)
    var name: String,
    @Column(name = "username", nullable = false, length = 128, unique = true)
    var username: String,
    @Column(name = "password", nullable = false, length = 128)
    var password: String,
    @OneToMany(mappedBy = "farmer", cascade = [CascadeType.ALL])
    var farms: MutableList<Farm>? = null,
    @Column(name = "deleted")
    var deleted: Boolean = false
)