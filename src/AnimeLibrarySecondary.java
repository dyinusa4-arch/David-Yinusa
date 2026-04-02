import components.set.Set;

/**
 * Layered implementations of secondary methods for {@code AnimeLibrary}.
 */
public abstract class AnimeLibrarySecondary implements AnimeLibrary {

    /*
     * Secondary Methods
     */

    public int lengthOfAll() {
        return this.length(1) + this.length(2) + this.length(3);
    }

    public void addToWatching(String show) {
        this.add(show);
        this.advance(show);
    }

    public void addToWatched(String show) {
        this.add(show);
        this.advance(show);
        this.advance(show);
    }

    public void addToWatched(String show, String tier) {
        this.add(show);
        this.advance(show);
        this.advance(show);
        this.changeTier(show, tier);
    }

    /*
     * Common methods (from Object)
     */

    @Override
    public boolean equals(Object obj) {
        AnimeLibrary object = (AnimeLibrary) obj;

        boolean equal = true;

        //first checks length
        if (this.length(1) != object.length(1)
                || this.length(2) != object.length(2)
                || this.length(3) != object.length(3)) {
            equal = false;
        }

        //checks for equality in watchlist
        if (equal) {
            Set<String> thisWatchList = this.showsInWatchlist();
            Set<String> objWatchList = object.showsInWatchlist();

            equal = thisWatchList.equals(objWatchList);
        }

        //then checks equality in currently watching
        if (equal) {
            Set<String> thisCurrWatch = this.showsInCurrWatch();
            Set<String> objCurrWatch = object.showsInCurrWatch();

            equal = thisCurrWatch.equals(objCurrWatch);
        }

        //finally check for equality in watched
        if (equal) {
            Set<String> thisSSTier = this.showsInTier("SS");
            Set<String> objSSTier = object.showsInTier("SS");

            equal = thisSSTier.equals(objSSTier);
        }

        if (equal) {
            Set<String> thisSTier = this.showsInTier("S");
            Set<String> objSTier = object.showsInTier("S");

            equal = thisSTier.equals(objSTier);
        }

        if (equal) {
            Set<String> thisATier = this.showsInTier("A");
            Set<String> objATier = object.showsInTier("A");

            equal = thisATier.equals(objATier);
        }

        if (equal) {
            Set<String> thisBTier = this.showsInTier("B");
            Set<String> objBTier = object.showsInTier("B");

            equal = thisBTier.equals(objBTier);
        }

        if (equal) {
            Set<String> thisCTier = this.showsInTier("C");
            Set<String> objCTier = object.showsInTier("C");

            equal = thisCTier.equals(objCTier);
        }

        if (equal) {
            Set<String> thisCTier = this.showsInTier("C");
            Set<String> objCTier = object.showsInTier("C");

            equal = thisCTier.equals(objCTier);
        }

        if (equal) {
            Set<String> thisDTier = this.showsInTier("D");
            Set<String> objDTier = object.showsInTier("D");

            equal = thisDTier.equals(objDTier);
        }

        if (equal) {
            Set<String> thisETier = this.showsInTier("E");
            Set<String> objETier = object.showsInTier("E");

            equal = thisETier.equals(objETier);
        }

        if (equal) {
            Set<String> thisURTier = this.showsInTier("Unranked");
            Set<String> objURTier = object.showsInTier("Unranked");

            equal = thisURTier.equals(objURTier);
        }
        return equal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("SS: ");

        Set<String> SSTier = this.showsInTier("SS");
        while (SSTier.size() > 0) {
            String next = SSTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; S: ");

        Set<String> STier = this.showsInTier("S");
        while (STier.size() > 0) {
            String next = STier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; A: ");

        Set<String> ATier = this.showsInTier("A");
        while (ATier.size() > 0) {
            String next = ATier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; B: ");

        Set<String> BTier = this.showsInTier("B");
        while (BTier.size() > 0) {
            String next = BTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; C: ");

        Set<String> CTier = this.showsInTier("C");
        while (CTier.size() > 0) {
            String next = CTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; D: ");

        Set<String> DTier = this.showsInTier("D");
        while (DTier.size() > 0) {
            String next = DTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; E: ");

        Set<String> ETier = this.showsInTier("E");
        while (ETier.size() > 0) {
            String next = ETier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; Unranked: ");

        Set<String> URTier = this.showsInTier("Unranked");
        while (URTier.size() > 0) {
            String next = URTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; Currently Watching: ");

        Set<String> currWatch = this.showsInCurrWatch();
        while (currWatch.size() > 0) {
            String next = currWatch.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; Watchlist: ");

        Set<String> watchlist = this.showsInWatchlist();
        while (watchlist.size() > 0) {
            String next = watchlist.removeAny();
            sb.append(next + ", ");
        }

        return sb.toString();
    }
}