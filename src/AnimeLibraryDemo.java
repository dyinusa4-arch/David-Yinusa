import components.set.Set;

/**
 * Use cases for {@code AnimeLibrary}.
 *
 * @author David Yinusa
 */
public class AnimeLibraryDemo {

    /**
     * Main method.
     *
     * @param args
     */
    public final void main(String[] args) {
        //array of shows
        String[] shows = { "DBZ", "Naruto", "One Piece", "Bleach", "Naruto",
                "JBA", "HxH", "JJK", "One Piece", "FMAB", "DBS" };

        AnimeLibrary lib = new AnimeLibrary();

        for (int i = 0; i < shows.length; i++) {
            String show = shows[i];
            //checks if it's in lib
            if (!lib.contains(show)) {
                lib.add(show);
            }
        }

        //all shows in array ignoring duplicates in watchlist.
        Set<String> showsInWatchlist = lib.showsInWatchlist();

        lib.advance("Bleach");
        lib.advance("DBZ");

        //contains Bleach and DBZ
        Set<String> showsInCurrWatch = lib.showsInCurrWatch();

        //removed from watchlist
        lib.remove("DBS");

        //adding to currWatch and Watched
        lib.addToWatching("Demon Slayer");
        lib.addToWatched("KNB"); //in Unranked tier

        //changing tier
    }
}
