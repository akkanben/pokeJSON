package gson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AbilityTest {
	@Test
	void constructorTest() {
		Ability sut = new Ability("Smile", "Boosts charisma");
		assertTrue(sut.getName().equals("Smile"));
		assertTrue(sut.getDescription().equals("Boosts charisma"));
	}

}
