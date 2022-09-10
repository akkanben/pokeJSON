package gson;

import java.util.ArrayList;

public class Pokemon {

	private String name;
	private int id;
	private int weight;
	private ArrayList<String> abilities;

	public Pokemon(int id, String name, int weight) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		abilities = new ArrayList<>();
	}

	public void addAbility(String ability) {
		abilities.add(ability);
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name;
	}

}
