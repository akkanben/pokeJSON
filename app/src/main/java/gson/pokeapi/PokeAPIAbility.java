package gson.pokeapi;

public class PokeAPIAbility {

	private PokeAPINamedAbility ability;

	public PokeAPIAbility() {
		// empty
	}

	public void setAbility(PokeAPINamedAbility ability) {
		this.ability = ability;
	}

	public PokeAPINamedAbility getAbility() {
		return ability;
	}

}
