import components.set.Set;

/**
 * JUnit test fixtures for {@code AnimeLibraryKernel}.
 *
 * @author David Yinusa
 */
public class AnimeLibraryKernelTest {

        /**
         *
         * Constructs an AnimeLibrary object from the given arguments. Each
         * section is separated by next.
         *
         * @param args
         *                the shows to be added
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
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

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

                String show = test.remove("DBZ");

                assertEquals(!test.contains("DBZ"), true);
                assertEquals(test.length(Section.Watchlist), 0);
                assertEquals(show, "DBZ");
        }

        @Test
        public final void testRemoveLeavingOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "AOT");

                String show = test.remove("DBZ");

                assertEquals(!test.contains("DBZ"), true);
                assertEquals(test.contains("AOT"), true);
                assertEquals(test.length(Section.Watchlist), 1);
                assertEquals(show, "DBZ");
        }

        @Test
        public final void testRemoveLeavingNonEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                String show = test.remove("DBZ");

                assertEquals(!test.contains("DBZ"), true);
                assertEquals(test.contains("Naruto"), true);
                assertEquals(test.contains("Bleach"), true);
                assertEquals(test.contains("JJK"), true);
                assertEquals(test.length(Section.Watchlist), 2);
                assertEquals(show, "DBZ");
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

        @Test
        public final void testAdvanceWatchlistToWatchNonEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "AOT");

                test.advance("DBZ");

                assertEquals(test.contains("DBZ"), true);
                assertEquals(test.length(Section.currWatch), 2);
                assertEquals(test.length(Section.Watchlist), 1);
        }

    @Test
    public final void testAdvanceWatchToWatchedMany() {
        AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "One Piece",
                "next", "Bleach", "JBA", "next", "HxH", "JJK");

        test.advance("JBA");

        assertEquals(test.contains("JBA"), true);
        assertEquals(test.length(Section.currWatch), 1);
        assertEquals(test.length(Section.Watchlist), 3);
        assertEquals(test.length(Section.Watched), 3)
    }

        /*
         * Test for contains
         */

        @Test
        public final void testContainsEmpty() {
                AnimeLibrary test = this.createFromArgs();
                AnimeLibrary testCopy = this.createFromArgs();

                boolean contains = test.contains("DBZ");

                assertEquals(contains, false);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testContainsWatchlistTrue() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                boolean contains = test.contains("DBZ");

                assertEquals(contains, true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testContainWatchingTrue() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                boolean contains = test.contains("JBA");

                assertEquals(contains, true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testContainsWatchedTrue() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                boolean contains = test.contains("JJK");

                assertEquals(contains, true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testContainsFalse() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                boolean contains = test.contains("FMAB");

                assertEquals(contains, true);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for length
         */

        @Test
        public final void testLengthEmpty() {
                AnimeLibrary test = this.createFromArgs();
                AnimeLibrary testCopy = this.createFromArgs();

                int length1 = test.length(Section.Watchlist);
                int length2 = test.length(Section.currWatch);
                int length3 = test.length(Section.Watched);

                assertEquals(length1, 0);
                assertEquals(length2, 0);
                assertEquals(length3, 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testLengthWatchlistEmpty() {
                AnimeLibrary test = this.createFromArgs("next", "DBZ", "Naruto",
                                "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("next", "DBZ",
                                "Naruto", "next", "AOT", "JJK");

                int length = test.length(Section.Watchlist);

                assertEquals(length, 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testLengthCurrWatchEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "next", "next", "AOT", "JJK");

                int length = test.length(Section.currWatch);

                assertEquals(length, 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testLengthWatchedEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "next", "AOT", "JJK");

                int length = test.length(Section.Watched);

                assertEquals(length, 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testLengthNonEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                int length1 = test.length(Section.Watchlist);
                int length2 = test.length(Section.currWatch);
                int length3 = test.length(Section.Watched);

                assertEquals(length1, 3);
                assertEquals(length2, 2);
                assertEquals(length3, 2);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for change tier
         */

        @Test
        public final void testChangeTierLeavingEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH");

                test.changeTier("HxH", Tier.SS);

                assertEquals(test.contains("HxH"), true);
                assertEquals(test.tier("HxH"), Tier.SS);
        }

        @Test
        public final void testChangeTierLeavingNonEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                test.changeTier("HxH", Tier.SS);

                assertEquals(test.contains("HxH"), true);
                assertEquals(test.tier("HxH"), Tier.SS);
        }

        /*
         * Tests for tier
         */

        @Test
        public final void testTierUnranked() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                Tier tier = test.tier("HxH");

                assertEquals(tier, Tier.Unranked);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierSS() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("JJK", Tier.SS);
                Tier tier = test.tier("JJK");

                assertEquals(tier, Tier.SS);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierS() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("FMAB", Tier.S);
                Tier tier = test.tier("FMAB");

                assertEquals(tier, Tier.S);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierA() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("OPM", Tier.A);
                Tier tier = test.tier("OPM");

                assertEquals(tier, Tier.A);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierB() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("DBS", Tier.B);
                Tier tier = test.tier("DBS");

                assertEquals(tier, Tier.B);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierC() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("Hell's Paradise", Tier.C);
                Tier tier = test.tier("Hell's Paradise");

                assertEquals(tier, Tier.C);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierD() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("Demon Slayer", Tier.D);
                Tier tier = test.tier("Demon Slayer");

                assertEquals(tier, Tier.D);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testTierE() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK", "FMAB", "OPM", "DBS",
                                "Hell's Paradise", "Demon Slayer", " KNB");

                test.changeTier("KNB", Tier.E);
                Tier tier = test.tier("KNB");

                assertEquals(tier, Tier.E);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for showsInTier
         */

        @Test
        public final void testShowsInTierEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "next", "AOT", "JJK");

                Set<String> set = test.showsInTier(Tier.Unranked);

                assertEquals(set.size(), 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowInTierOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "AOT");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "next", "AOT");

                Set<String> set = test.showsInTier(Tier.Unranked);

                assertEquals(set.contains("AOT"), true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowsInTierMoreThanOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Set<String> set = test.showsInTier(Tier.Unranked);

                assertEquals(set.contains("HxH") && set.contains("JJK"), true);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for showsInCurrWatch
         */

        @Test
        public final void testShowsInCurrWatchEmpty() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "next", "next", "AOT", "JJK");

                Set<String> set = test.showsInCurrWatch();

                assertEquals(set.size(), 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowsInCurrWatchOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto", "next",
                                "FMAB", "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "FMAB", "next", "next", "AOT", "JJK");

                Set<String> set = test.showsInCurrWatch();

                assertEquals(set.contains("FMAB"), true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowsInCurrWatchMoreThanOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Set<String> set = test.showsInCurrWatch();

                assertEquals(set.contains("Bleach") && set.contains("JBA"),
                                true);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for showsInWatchlist
         */

        @Test
        public final void testShowsInWatchlistEmpty() {
                AnimeLibrary test = this.createFromArgs("next", "DBZ", "Naruto",
                                "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("next", "DBZ",
                                "Naruto", "next", "AOT", "JJK");

                Set<String> set = test.showsInWatchlist();

                assertEquals(set.size(), 0);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowsInWatchlistOne() {
                AnimeLibrary test = this.createFromArgs("FMAB", "next", "DBZ",
                                "Naruto", "next", "AOT", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("FMAB", "next",
                                "DBZ", "Naruto", "next", "AOT", "JJK");

                Set<String> set = test.showsInWatchlist();

                assertEquals(set.contains("FMAB"), true);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testShowsInWatchlistMoreThanOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Set<String> set = test.showsInWatchlist();

                assertEquals(set.contains("DBZ") && set.contains("Naruto")
                                && set.contains("One Piece"), true);
                assertEquals(test, testCopy);
        }

        /*
         * Tests for section
         */

        @Test
        public final void testSectionInWatchlist() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Section section = test.section("DBZ");

                assertEquals(section, Section.Watchlist);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testSectionInCurrWatch() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Section section = test.section("Bleach");

                assertEquals(section, Section.currWatch);
                assertEquals(test, testCopy);
        }

        @Test
        public final void testSectionInWatched() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testCopy = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");

                Section section = test.section("JJK");

                assertEquals(section, Section.Watched);
                assertEquals(test, testCopy);
        }

        /*
         * Standard Methods
         */

        /*
         * Test for newInstance
         */

        @Test
        public final void testNewInstance() {
                AnimeLibrary test = this.createFromArgs();

                AnimeLibrary testCopy = test.newInstance();

                assertEquals(test, testCopy);
        }

        /*
         * Tests for clear
         */

        @Test
        public final void testClearEmpty() {
                AnimeLibrary test = this.createFromArg();
                AnimeLibrary testRef = this.createFromArgs();

                test.clear();

                assertEquals(test, testRef);
        }

        @Test
        public final void testClearOne() {
                AnimeLibrary test = this.createFromArg("DBZ");
                AnimeLibrary testRef = this.createFromArgs();

                test.clear();

                assertEquals(test, testRef);
        }

        @Test
        public final void testClearMoreThanOne() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testRef = this.createFromArgs();

                test.clear();

                assertEquals(test, testRef);
        }

        /*
         * Tests for transferFrom
         */

        @Test
        public final void testTransferFromBothEmpty() {
                AnimeLibrary test = this.createFromArg();
                AnimeLibrary testRef = this.createFromArg();
                AnimeLibrary testEmpty = this.createFromArgs();

                int length = testRef.lengthOfAll();

                test.transferFrom(testRef);

                assertEquals(testRef, testEmpty);
                assertEquals(test.lengthOfAll(), length);
        }

        @Test
        public final void testTransferFromOne() {
                AnimeLibrary test = this.createFromArgs("DBZ");
                AnimeLibrary testRef = this.createFromArg("AOT");
                AnimeLibrary testEmpty = this.createFromArgs();

                int length = testRef.lengthOfAll();

                test.transferFrom(testRef);

                assertEquals(testRef, testEmpty);
                assertEquals(test.lengthOfAll(), length);
        }

        @Test
        public final void testTransferFromMany() {
                AnimeLibrary test = this.createFromArgs("DBZ", "Naruto",
                                "One Piece", "next", "Bleach", "JBA", "next",
                                "HxH", "JJK");
                AnimeLibrary testRef = this.createFromArgs("DBZ", "Naruto",
                                "next", "AOT", "JJK");
                AnimeLibrary testEmpty = this.createFromArgs();

                int length = testRef.lengthOfAll();

                test.transferFrom(testRef);

                assertEquals(testRef, testEmpty);
                assertEquals(test.lengthOfAll(), length);
        }
}
