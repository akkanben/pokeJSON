package gson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PokemonTest {

	@Test
	void constructorTest() {
		Pokemon sut = new Pokemon(1001, "Potato", 45);
		assertTrue(sut.getId() == 1001);
		assertTrue(sut.getName().equals("Potato"));
		assertTrue(sut.getWeight() == 45);
	}

	@Test
	void addSingleAbility() {
		Pokemon sut = new Pokemon(1001, "Potato", 45);
		sut.addAbility(new Ability("Jump", "Jump super high"));
		assertTrue(sut.getAbilities().get(0).getName().equals("Jump"));
		sut.addAbility(new Ability("Smile", "Boosts charisma"));
		assertTrue(sut.getAbilities().get(1).getDescription().equals("Boosts charisma"));
		assertTrue(sut.getAbilities().size() == 2);
	}


}
