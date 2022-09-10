package gson.pokeapi;

public class PokeAPIEffectEntry {
	private PokeAPILanguage language;
	private String effect;

	public PokeAPIEffectEntry(PokeAPILanguage language, String effect) {
		this.language = language;
		this.effect = effect;
	}

	public PokeAPILanguage getLanguage() {
		return language;
	}

	public String getEffect() {
		return effect;
	}
}
