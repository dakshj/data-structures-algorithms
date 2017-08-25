import java.util.ArrayList;
import java.util.List;

public class LambdaCountries {

    public static void main(String[] args) {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Asia", 100));
        countries.add(new Country("Europe", 300));
        countries.add(new Country("Asia", 200));
        countries.add(new Country("Europe", 450));
        countries.add(new Country("North America", 250));
        countries.add(new Country("Asia", 990));
        countries.add(new Country("Asia", 655));

        System.out.println(getPopulation(countries, "Asia"));
    }

    private static int getPopulation(List<Country> countries, String continent) {
        return countries.stream().map(c -> c.continent.equals(continent) ? c.population : 0)
                .reduce(0, (a, b) -> a + b);
    }

    private static class Country {
        String continent;
        int population;

        Country(String continent, int population) {
            this.continent = continent;
            this.population = population;
        }
    }
}
