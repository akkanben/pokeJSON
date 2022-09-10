package gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.cli.*;

import gson.pokeapi.PokeAPIPokemon;
import gson.Pokemon;

public class Main {

	private static final String baseURL = "https://pokeapi.co/api/v2/pokemon/";

	public static void main(String[] args) {
		// Command line args parsing
		Options options = getCommandLineOptions();
		CommandLine commandLine = getParsedCommandLine(options, args);

		// HTTP request
		String pokemonName = commandLine.getArgs()[0];
		String apiResponseString = getApiLine(baseURL + pokemonName);
		String pokemonJSON = getPokemonJSON(apiResponseString);

		// File Writing
		boolean shouldWriteJSON = !commandLine.hasOption("no-output");
		if (shouldWriteJSON) {
			writeOutPokemonJSON(commandLine, pokemonJSON, pokemonName);
		}

		System.out.println(pokemonJSON);
	}

	private static void writeOutPokemonJSON(CommandLine commandLine, String json, String pokemonName) {
		String outputPath;
		String customOutputPath = commandLine.getOptionValue("output-path");
		String listToAppend = commandLine.getOptionValue("append-list");
		File outputFile;

		if (customOutputPath == null) {
			outputPath = "./";
		} else {
			outputPath = customOutputPath;
		}

		if (listToAppend == null) {
			outputFile = new File(outputPath + pokemonName + ".json");
		} else {
			outputPath = listToAppend;
			// get list, append and update json to write
			try {
				File list = new File(listToAppend);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				List<Pokemon> arrayList;
				if (list.exists()) {
					FileReader reader = new FileReader(list);
					Type listType = new TypeToken<ArrayList<Pokemon>>() {
					}.getType();
					arrayList = gson.fromJson(reader, listType);
					reader.close();
				} else {
					arrayList = new ArrayList<>();
				}
				Pokemon pokemonToAdd = gson.fromJson(json, Pokemon.class);
				arrayList.add(pokemonToAdd);
				json = gson.toJson(arrayList);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			outputFile = new File(outputPath);
		}

		try {
			FileWriter fileWriter = new FileWriter(outputFile);
			fileWriter.write(json);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ioe) {
			System.out.println("Could not write to " + outputPath);
			ioe.printStackTrace();
		}

	}

	private static String getPokemonJSON(String line) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		PokeAPIPokemon apiPokemon = gson.fromJson(line, PokeAPIPokemon.class);
		return gson.toJson(new Pokemon(apiPokemon));
	}

	private static CommandLine getParsedCommandLine(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helpFormatter = new HelpFormatter();
		CommandLine commandLine = null;
		try {
			commandLine = parser.parse(options, args);
			if (commandLine.getArgs().length != 1) {
				throw new ParseException("Missing or multiple [NAME] argument(s).");
			}
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
			helpFormatter.printHelp("PokeJSON [OPTIONS] [NAME]", options);
			System.exit(0);
		}
		return commandLine;
	}

	private static Options getCommandLineOptions() {
		Options options = new Options();

		Option alternateOutputPath = new Option("o", "output-path", true,
				"Alternate output path, default is current directory");
		alternateOutputPath.setRequired(false);
		options.addOption(alternateOutputPath);

		Option supressOutput = new Option("n", "no-output", false, "Use to supress json write");
		supressOutput.setRequired(false);
		options.addOption(supressOutput);

		Option addToList = new Option("a", "append-list", true, "Append to .json file or create new");
		addToList.setRequired(false);
		options.addOption(addToList);

		Option helpMenu = new Option("h", "help", false, "Show this menu");
		helpMenu.setRequired(false);
		options.addOption(helpMenu);

		return options;
	}

	public static String getApiLine(String urlString) {
		String output = "";
		try {
			URL url = new URL(urlString);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = (HttpURLConnection) urlConnection;
			connection.setRequestMethod("GET");
			InputStreamReader streamReader = getInputStreamOrErrorStream(connection);
			BufferedReader apiBufferedReader = new BufferedReader(streamReader);
			output = apiBufferedReader.readLine();
		} catch (IOException ioe) {
			System.out.println("Unable to create connection");
			ioe.printStackTrace();
		}
		return output;
	}

	private static InputStreamReader getInputStreamOrErrorStream(HttpURLConnection connection) throws IOException {
		int status = connection.getResponseCode();
		if (status > 299) {
			System.out.println("Failed to get Pokemon. HTTP status code: " + status);
			System.exit(1);
			return new InputStreamReader(connection.getErrorStream());
		} else {
			return new InputStreamReader(connection.getInputStream());

		}
	}
}
