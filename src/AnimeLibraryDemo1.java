import components.animelibrary.AnimeLibrary;
import components.animelibrary.AnimeLibrary1;
import components.animelibrary.Section;
import components.set.Set;

/**
 * Use cases for {@code AnimeLibrary}. Uses multiple AnimeLibrary objects.
 *
 * @author David Yinusa
 */
public class AnimeLibraryDemo1 {

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        AnimeLibrary lib1 = new AnimeLibrary1();

        //call new instance to make new object
        AnimeLibrary lib2 = lib1.newInstance();

        //array of shows
        String[] showsForLib1 = { "DBZ", "Naruto", "One Piece", "Bleach",
                "Naruto", "JBA", "HxH", "JJK", "One Piece", "FMAB", "DBS" };

        String[] showsForLib2 = { "DBZ", "KNY", "KNB", "OPM", "One Piece",
                "Naruto", "HxH", "Bleach" };

        for (int i = 0; i < showsForLib1.length; i++) {
            String show = showsForLib1[i];
            //checks if it's in lib
            if (!lib1.contains(show)) {
                lib1.add(show);
            }
        }

        for (int i = 0; i < showsForLib2.length; i++) {
            String show = showsForLib2[i];
            //checks if it's in lib
            if (!lib2.contains(show)) {
                lib2.add(show);
            }
        }

        //can check for equality in sections
        Set<String> watchlist1 = lib1.showsInWatchlist();
        Set<String> watchlist2 = lib2.showsInWatchlist();

        boolean sameShows = watchlist1.equals(watchlist2);

        //can also check if a show in one library is in another and get the section
        boolean isInLib1 = lib1.contains("Naruto");
        if (isInLib1) {
            boolean isInLib2 = lib2.contains("Naruto");
            if (isInLib2) {
                Section section = lib2.section("Naruto");
            }
        }

        //compare lengths of all or any sections
        boolean isSameLength = lib1.lengthOfAll() == lib2.lengthOfAll();

        boolean isSameSectionLength = lib1.length(Section.Watchlist) == lib2
                .length(Section.Watchlist);

        //transfer shows in one object to another
        lib1.transferFrom(lib2);
    }
}
