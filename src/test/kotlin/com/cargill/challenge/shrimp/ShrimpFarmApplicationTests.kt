package com.cargill.challenge.shrimp

import com.cargill.challenge.shrimp.app.ShrimpFarmApplication
import com.cargill.challenge.shrimp.dto.FarmProj
import com.cargill.challenge.shrimp.dto.FarmerProj
import com.cargill.challenge.shrimp.dto.PondProj
import com.cargill.challenge.shrimp.service.ShrimpFarmService
import com.cargill.challenge.shrimp.util.getJsonString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [ShrimpFarmApplication::class])
class ShrimpFarmApplicationTests(
		@Autowired
		private val shrimpFarmService: ShrimpFarmService
) {

	@Test
	@Transactional(readOnly = true)
	fun findAllFarmersTest() {
		val result = this.shrimpFarmService.findAllFarmers(FarmerProj::class.java)
		println("Result:")
		println(getJsonString(result))
		val jon = generateFarmerJon()
		val sansa = generateFarmerJSansa()
		Assertions.assertEquals(jon.id, result.content!![0].id)
		Assertions.assertEquals(jon.name, result.content!![0].name)
		Assertions.assertEquals(jon.username, result.content!![0].username)
		Assertions.assertEquals(sansa.id, result.content!![1].id)
		Assertions.assertEquals(sansa.name, result.content!![1].name)
		Assertions.assertEquals(sansa.username, result.content!![1].username)
	}

	@Test
	@Transactional(readOnly = true)
	fun findAllFarmsByFarmerTest() {
		val result = this.shrimpFarmService.findAllFarmsByFarmer(FarmProj::class.java, 2L)
		println("Result:")
		println(getJsonString(result))
	}

	@Test
	@Transactional(readOnly = true)
	fun findAllPondsByFarmTest() {
		val result = this.shrimpFarmService.findAllPondsByFarm(PondProj::class.java, 3L)
		println("Result:")
		println(getJsonString(result))
	}

	fun generateFarmerJon(): FarmerProj {
		return object: FarmerProj {
			override val id: Long
				get() = 1L
			override val name: String
				get() = "Jon Snow"
			override val username: String
				get() = "jsnow"

		}
	}

	fun generateFarmerJSansa(): FarmerProj {
		return object: FarmerProj {
			override val id: Long
				get() = 2L
			override val name: String
				get() = "Sansa Stark"
			override val username: String
				get() = "sstark"

		}
	}
}
