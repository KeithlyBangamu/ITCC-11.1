package activity1;
import java.util.*;

class Movies {
    String title, genre;

    public Movies(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
}

class Account {

    String name;

    List<String> genres = new ArrayList<>();

    public Account(String name) {
        this.name = name;
    }

    public void watchMovie(Movies movies) {
        watchedGenres.add(movies.genre);
        System.out.println("Watching: " + movies.title);
    }

    public void recommendMovies(List<Movies> movies) {
        if (genres.isEmpty()) {
            System.out.println("You havent watched anything yet ");
            return;
        }
        
        String mostGenre = getFavoriteGenre();

        System.out.println("Most viewed" + mostGenre + " movies. Try these:");
        
        for (Movies movie : movies) {
            if (movie.genre.equals(mostGenre)) {
                System.out.println("- " + movie.title);
            }
        }
    }

    private String getFavoriteGenre() {
        Map<String, Integer> count = new HashMap<>();
        for (String genre : genres) {
            count.put(genre, count.getOrDefault(genre, 0) + 1);
        }
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}

public class activity1netflix {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Movies> movies = Arrays.asList(
            new Movies("Divergent", "Sci-Fi"),
            new Movies("High School Musical", "Romance"),
            new Movies("Fast & Furious", "Action"),
            new Movies("Geo Storm", "Sci-Fi"),
            new Movies("All At Once", "Horror"),
            new Movies("When we meet", "Romance"),
            new Movies("SkyFall", "Action"),
            new Movies("Under The Deep", "Horror")
        );

        System.out.print("Enter your account name: ");

        Account user = new Account(scanner.nextLine());
        
        while (true) {
            System.out.println("\n1. Select a movie\n2. Recommended movies to watch\n3. Exit");

            System.out.print("Select: ");

            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.println("Select a movie:");

                for (int i = 0; i < movies.size(); i++) {
                    System.out.println((i + 1) + ". " + movies.get(i).title);

                }
                int movieIndex = scanner.nextInt() - 1;
                
                if (movieIndex >= 0 && movieIndex < movies.size()) {
                    user.watchMovie(movies.get(movieIndex));

                } else {
                    System.out.println("Invalid choice.");

                }

            } else if (choice == 2) {
                user.recommendMovies(movies);

            } else if (choice == 3) {
                System.out.println("Bye!");

                break;

            } else {
                System.out.println("Invalid ");
            }
        }
        scanner.close();
    }
}
