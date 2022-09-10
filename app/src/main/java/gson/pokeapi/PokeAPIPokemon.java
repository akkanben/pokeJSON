package gson.pokeapi;

public class PokeAPIPokemon {

	private String name;
	private int id;
	private int weight;
	private PokeAPIAbility[] abilities;

	public PokeAPIPokemon() {
		// empty
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setAbilities(PokeAPIAbility[] abilities) {
		this.abilities = abilities;
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name;
	}

}
