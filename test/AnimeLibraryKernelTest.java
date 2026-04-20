/**
 * JUnit test fixtures for {@code AnimeLibraryKernel}.
 *
 * @author David Yinusa
 */
public class AnimeLibraryKernelTest {

    /**
     *
     * Constructs an AnimeLibrary object from the given arguments. Each section
     * is separated by next.
     *
     * @param args
     *            the shows to be added
     * @return the constructed library
     * @requires every show is unique
     * @ensures Library with the sections from arg separated by "next"
     */
    private AnimeLibrary createFromArgs(String... args) {
        //track the number of times next appears to know where to add
        int count = 0;

        //create AnimeLibrary object
        AnimeLibrary al = new AnimeLibrary();

        //iterate over args
        for (int i = 0; i < args.length; i++) {
            if (count == 0 && !args[i].equals("next")) {
                al.add(args[i]);
            } else if (count == 1 && !args[i].equals("next")) {
                al.addToWatching(args[i]);
            } else if (count == 2 && !args[i].equals("next")) {
                al.addToWatched(args[i]);
            } else {
                count++;
            }
        }

        return al;
    }

    /*
     * Test case for constructor
     */

    @Test
    public final void testNoArgsConstructor() {
        AnimeLibrary test = new AnimeLibrary();

        //ensuring everything is empty
        assertEquals(test.length(Section.Watchlist), 0);
        assertEquals(test.length(Section.currWatch), 0);
        assertEquals(test.length(Section.Watched), 0);
    }

    /*
     * Tests for add
     */

    @Test
    public final void testAddEmpty() {
        AnimeLibrary test = new AnimeLibrary();

        test.add("DBZ");

        int length = test.length(Section.Watchlist);

        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.length(Section.Watchlist), length + 1);
    }

    @Test
    public final void testAddWlNonEmpty() {
        AnimeLibrary test = new AnimeLibrary();

        test.add("DBZ");
        int length = test.length(Section.Watchlist);

        test.add("Naruto");

        assertEquals(test.contains("Naruto"), true);
        assertEquals(test.length(Section.Watchlist), length + 1);
    }

    @Test
    public final void testAddWatchingNonEmpty() {
        AnimeLibrary test = new AnimeLibrary();

        test.add("DBZ");
        test.advance("DBZ");
        int length = test.length(Section.Watchlist);

        test.add("Naruto");

        assertEquals(test.contains("Naruto"), true);
        assertEquals(test.length(Section.Watchlist), length + 1);
    }

    @Test
    public final void testAddWatchedNonEmpty() {
        AnimeLibrary test = this.createFromArgs("next", "next", "DBZ");

        int length = test.length(Section.Watchlist);

        test.add("Naruto");

        assertEquals(test.contains("Naruto"), true);
        assertEquals(test.length(Section.Watchlist), length + 1);
    }

    @Test
    public final void testAddAllMany() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        int length = test.length(Section.Watchlist);

        test.add("AOT");

        assertEquals(test.contains("AOT"), true);
        assertEquals(test.length(Section.Watchlist), length + 1);
    }

    /*
     * Tests for remove
     */

    @Test
    public final void testRemoveLeavingEmpty() {
        AnimeLibrary test = this.createFronArgs("DBZ");

        test.remove("DBZ");

        assertEquals(!test.contains("DBZ"), true);
        assertEquals(test.length(Section.Watchlist), 0);
    }

    @Test
    public final void testRemoveLeavingOne() {
        AnimeLibrary test = this.createFromArgs("DBZ", "AOT");

        test.remove("DBZ");

        assertEquals(!test.contains("DBZ"), true);
        assertEquals(test.contains("AOT"), true);
        assertEquals(test.length(Section.Watchlist), 1);
    }

    @Test
    public final void testRemoveLeavingNonEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        test.remove("DBZ");

        assertEquals(!test.contains("DBZ"), true);
        assertEquals(test.contains("Naruto"), true);
        assertEquals(test.contains("Bleach"), true);
        assertEquals(test.contains("JJK"), true);
        assertEquals(test.length(Section.Watchlist), 2);
    }

    /*
     * Tests for advance
     */

    @Test
    public final void testAdvanceWatchlistToWatchingEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ");

        test.advance("DBZ");

        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.length(Section.currWatch), 1);
        assertEquals(test.length(Section.Watchlist), 0);
    }

    @Test
    public final void testAdvanceWatchingToWatchedEmpty() {
        AnimeLibrary test = this.createFromArgs("next", "DBZ");

        test.advance("DBZ");

        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.length(Section.currWatch), 0);
        assertEquals(test.length(Section.Watched), 1);
    }
}
