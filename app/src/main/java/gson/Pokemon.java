package gson;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gson.Ability;
import gson.pokeapi.PokeAPIAbility;
import gson.pokeapi.PokeAPIAbilityDetail;
import gson.pokeapi.PokeAPIEffectEntry;
import gson.pokeapi.PokeAPIPokemon;
import gson.pokeapi.PokeAPIType;

public class Pokemon {

	private String name;
	private int id;
	private int weight;
	private ArrayList<Ability> abilities;
	private ArrayList<String> types;

	public Pokemon(int id, String name, int weight) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		abilities = new ArrayList<>();
		types = new ArrayList<>();
	}

	public Pokemon(PokeAPIPokemon apiPokemon) {
		this.id = apiPokemon.getId();
		this.name = apiPokemon.getName();
		this.weight = apiPokemon.getWeight();
		abilities = new ArrayList<>();
		for (PokeAPIAbility apiAblity : apiPokemon.getAbilities()) {
			String name = apiAblity.getAbility().getName();
			String description = convertAbilityURLToDescription(apiAblity.getAbility().getUrl());
			Ability ability = new Ability(name, description);
			abilities.add(ability);
		}
		types = new ArrayList<>();
		for (PokeAPIType type : apiPokemon.getTypes()) {
			types.add(type.getType().getName());
		}
	}

	private String convertAbilityURLToDescription(String url) {
		String apiLine = Main.getApiLine(url);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		PokeAPIAbilityDetail abilityDetail = gson.fromJson(apiLine, PokeAPIAbilityDetail.class);
		String description = "none";
		for (PokeAPIEffectEntry entry : abilityDetail.getEffectEntries()) {
			if (entry.getLanguage().getName().equals("en")) {
				description = entry.getEffect();
				break;
			}
		}
		return description;
	}

	public void addAbility(Ability ability) {
		abilities.add(ability);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name;
	}

}
