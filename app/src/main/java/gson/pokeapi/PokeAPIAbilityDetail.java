package gson.pokeapi;

import gson.pokeapi.*;

public class PokeAPIAbilityDetail {
	private int id;
	private PokeAPIEffectEntry[] effect_entries;

	public PokeAPIAbilityDetail(int id, PokeAPIEffectEntry[] effect_entries) {
		this.id = id;
		this.effect_entries = effect_entries;
	}

	public PokeAPIEffectEntry[] getEffectEntries() {
		return effect_entries;
	}
}
