package components.animelibrary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code AnimeLibrarySecondary}.
 *
 * @author David Yinusa
 */
public class AnimeLibrarySecondaryTest {

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
        AnimeLibrary al = new AnimeLibrary1();

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
     * Test for lengthOfAll
     */

    @Test
    public final void testLengthOfAllEmpty() {
        AnimeLibrary test = this.createFromArgs();
        AnimeLibrary testCopy = this.createFromArgs();

        int length = test.lengthOfAll();

        assertEquals(length, 0);
        assertEquals(test, testCopy);
    }

    @Test
    public final void testLengthOfAllWatchListEmpty() {
        AnimeLibrary test = this.createFromArgs("next", "DBZ", "Naruto", "next",
                "AOT", "JJK");
        AnimeLibrary testCopy = this.createFromArgs("next", "DBZ", "Naruto",
                "next", "AOT", "JJK");

        int length = test.lengthOfAll();

        assertEquals(length, 4);
        assertEquals(test, testCopy);
    }

    @Test
    public final void testLengthOfAllCurrWatchEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next", "next",
                "AOT", "JJK");
        AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto", "next",
                "next", "AOT", "JJK");

        int length = test.lengthOfAll();

        assertEquals(length, 4);
        assertEquals(test, testCopy);
    }

    @Test
    public final void testLengthOfAllCurrWatchedEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next", "AOT",
                "JJK");
        AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto", "next",
                "AOT", "JJK");

        int length = test.lengthOfAll();

        assertEquals(length, 4);
        assertEquals(test, testCopy);
    }

    @Test
    public final void testLengthOfAllNonEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");
        AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                "One Piece", "next", "Bleach", "JBA", "next", "HxH", "JJK");

        int length = test.lengthOfAll();

        assertEquals(length, 7);
        assertEquals(test, testCopy);
    }

    /*
     * Tests for addToWatching
     */

    @Test
    public final void testAddToWatchingEmpty() {
        AnimeLibrary test = this.createFromArgs();

        int length = test.length(Section.currWatch);

        test.addToWatching("DBZ");

        assertEquals(length + 1, 1);
        assertEquals(test.contains("DBZ"), true);
    }

    @Test
    public final void testAddToWatchingOne() {
        AnimeLibrary test = this.createFromArgs("next", "AOT");

        int length = test.length(Section.currWatch);

        test.addToWatching("DBZ");

        assertEquals(length + 1, 2);
        assertEquals(test.contains("DBZ"), true);
    }

    @Test
    public final void testAddToWatchingNonEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        int length = test.length(Section.currWatch);

        test.addToWatching("FMAB");

        assertEquals(length + 1, 3);
        assertEquals(test.contains("FMAB"), true);
    }

    /*
     * Tests for addToWatched
     */

    @Test
    public final void testAddToWatchedEmpty() {
        AnimeLibrary test = this.createFromArgs();

        int length = test.length(Section.Watched);

        test.addToWatched("DBZ");

        assertEquals(length + 1, 1);
        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.tier("DBZ"), Tier.Unranked);
    }

    @Test
    public final void testAddToWatchedTierEmpty() {
        AnimeLibrary test = this.createFromArgs();

        int length = test.length(Section.Watched);

        test.addToWatched("DBZ", Tier.SS);

        assertEquals(length + 1, 1);
        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.tier("DBZ"), Tier.SS);
    }

    @Test
    public final void testAddToWatchedOne() {
        AnimeLibrary test = this.createFromArgs("next", "next", "AOT");

        int length = test.length(Section.Watched);

        test.addToWatched("DBZ");

        assertEquals(length + 1, 2);
        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.tier("DBZ"), Tier.Unranked);
    }

    @Test
    public final void testAddToWatchedTierOne() {
        AnimeLibrary test = this.createFromArgs("next", "next", "AOT");

        int length = test.length(Section.Watched);

        test.addToWatched("DBZ", Tier.S);

        assertEquals(length + 1, 2);
        assertEquals(test.contains("DBZ"), true);
        assertEquals(test.tier("DBZ"), Tier.S);
    }

    @Test
    public final void testAddToWatchedNonEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        int length = test.length(Section.Watched);

        test.addToWatched("FMAB");

        assertEquals(length + 1, 3);
        assertEquals(test.contains("FMAB"), true);
        assertEquals(test.tier("FMAB"), Tier.Unranked);
    }

    @Test
    public final void testAddToWatchedTierNonEmpty() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        int length = test.length(Section.Watched);

        test.addToWatched("FMAB", Tier.A);

        assertEquals(length + 1, 3);
        assertEquals(test.contains("FMAB"), true);
        assertEquals(test.tier("FMAB"), Tier.A);
    }
}
