# PokeJSON CLI Tool

The PokeJSON CLI Tool makes use of the [](https://pokeapi.co/) API. Queries can be made for Pokemon by name and are displayed in the console as well as saved to a local .json file. There is an option to append a given .json file to build up a list of PokeJSON objects. 

Check releases for the latest version. Or build the project with using Gradle: `./gradlew build` and then look in the `app/build/distributions/` folder.


## Example Usage:

```bash
user@computer:~/pokeJSON/bin$ ./pokeJSON --help
usage: PokeJSON [OPTIONS] [NAME]
 -a,--append-list <arg>   Append to .json file or create new
 -h,--help                Show this menu
 -n,--no-output           Use to supress json write
 -o,--output-path <arg>   Alternate output path, default is current
                          directory
user@computer:~/pokeJSON/bin$ ./pokeJSON pikachu
{
  "name": "pikachu",
  "id": 25,
  "weight": 60,
  "abilities": [
    {
      "name": "static",
      "description": "Whenever a move makes contact with this Pokémon, the move\u0027s user has a 30% chance of being paralyzed.\n\nPokémon that are immune to electric-type moves can still be paralyzed by this ability.\n\nOverworld: If the lead Pokémon has this ability, there is a 50% chance that encounters will be with an electric Pokémon, if applicable."
    },
    {
      "name": "lightning-rod",
      "description": "All other Pokémon\u0027s single-target electric-type moves are redirected to this Pokémon if it is an eligible target.  Other Pokémon\u0027s Electric moves raise this Pokémon\u0027s Special Attack one stage, negating any other effect on it, and cannot miss it.\n\nIf the move\u0027s intended target also has this ability, the move is not redirected.  When multiple Pokémon with this ability are possible targets for redirection, the move is redirected to the one with the highest Speed stat, or, in the case of a tie, to a random tied Pokémon.  follow me takes precedence over this ability.\n\nIf the Pokémon is a ground-type and thus immune to Electric moves, its immunity prevents the Special Attack boost."
    }
  ]
}

```

## Updates

- Version 0.0.1
  - Adds basic functionality for looking up pokemon.
  - Uses the default ability url to fetch the actual ability description in English.
  - Allows the user to skip writing to disk via the --no-output option.
  - Allows the user to specify the output directory path.
  - Add option to append a .json file (or create new file) with the --append-list option. 
