package gson;

import java.util.ArrayList;

import gson.Ability;
import gson.pokeapi.PokeAPIAbility;
import gson.pokeapi.PokeAPIPokemon;

public class Pokemon {

	private String name;
	private int id;
	private int weight;
	private ArrayList<Ability> abilities;

	public Pokemon(int id, String name, int weight) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		abilities = new ArrayList<>();
	}

	public Pokemon(PokeAPIPokemon apiPokemon) {
		this.id = apiPokemon.getId();
		this.name = apiPokemon.getName();
		this.weight = apiPokemon.getWeight();
		abilities = new ArrayList<>();
		for (PokeAPIAbility apiAblity : apiPokemon.getAbilities()) {
			String name = apiAblity.getAbility().getName();
			String description = apiAblity.getAbility().getUrl();
			System.out.println(name + " " + description);
			Ability ability = new Ability(name, description);
			abilities.add(ability);
		}
	}

	public void addAbility(Ability ability) {
		abilities.add(ability);
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name;
	}

}
