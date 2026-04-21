package components.animelibrary;

import components.set.Set;

/**
 * Layered implementations of secondary methods for {@code AnimeLibrary}.
 *
 * @author David Yinusa
 */
public abstract class AnimeLibrarySecondary implements AnimeLibrary {

    /*
     * Secondary Methods
     */

    @Override
    public int lengthOfAll() {
        return this.length(Section.Watchlist) + this.length(Section.currWatch)
                + this.length(Section.Watched);
    }

    @Override
    public void addToWatching(String show) {
        this.add(show);
        this.advance(show);
    }

    @Override
    public void addToWatched(String show) {
        this.add(show);
        this.advance(show);
        this.advance(show);
    }

    @Override
    public void addToWatched(String show, Tier tier) {
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
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AnimeLibrary)) {
            return false;
        }
        AnimeLibrary object = (AnimeLibrary) obj;

        boolean equal = true;

        //first checks length
        if (this.length(Section.Watchlist) != object.length(Section.Watchlist)
                || this.length(Section.currWatch) != object
                        .length(Section.currWatch)
                || this.length(Section.Watched) != object
                        .length(Section.Watched)) {
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
            Set<String> thisSSTier = this.showsInTier(Tier.SS);
            Set<String> objSSTier = object.showsInTier(Tier.SS);

            equal = thisSSTier.equals(objSSTier);
        }

        if (equal) {
            Set<String> thisSTier = this.showsInTier(Tier.S);
            Set<String> objSTier = object.showsInTier(Tier.S);

            equal = thisSTier.equals(objSTier);
        }

        if (equal) {
            Set<String> thisATier = this.showsInTier(Tier.A);
            Set<String> objATier = object.showsInTier(Tier.A);

            equal = thisATier.equals(objATier);
        }

        if (equal) {
            Set<String> thisBTier = this.showsInTier(Tier.B);
            Set<String> objBTier = object.showsInTier(Tier.B);

            equal = thisBTier.equals(objBTier);
        }

        if (equal) {
            Set<String> thisCTier = this.showsInTier(Tier.C);
            Set<String> objCTier = object.showsInTier(Tier.C);

            equal = thisCTier.equals(objCTier);
        }

        if (equal) {
            Set<String> thisDTier = this.showsInTier(Tier.D);
            Set<String> objDTier = object.showsInTier(Tier.D);

            equal = thisDTier.equals(objDTier);
        }

        if (equal) {
            Set<String> thisETier = this.showsInTier(Tier.E);
            Set<String> objETier = object.showsInTier(Tier.E);

            equal = thisETier.equals(objETier);
        }

        if (equal) {
            Set<String> thisURTier = this.showsInTier(Tier.Unranked);
            Set<String> objURTier = object.showsInTier(Tier.Unranked);

            equal = thisURTier.equals(objURTier);
        }
        return equal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("SS: ");

        Set<String> sSTier = this.showsInTier(Tier.SS);
        while (sSTier.size() > 0) {
            String next = sSTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; S: ");

        Set<String> sTier = this.showsInTier(Tier.S);
        while (sTier.size() > 0) {
            String next = sTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; A: ");

        Set<String> aTier = this.showsInTier(Tier.A);
        while (aTier.size() > 0) {
            String next = aTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; B: ");

        Set<String> bTier = this.showsInTier(Tier.B);
        while (bTier.size() > 0) {
            String next = bTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; C: ");

        Set<String> cTier = this.showsInTier(Tier.C);
        while (cTier.size() > 0) {
            String next = cTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; D: ");

        Set<String> dTier = this.showsInTier(Tier.D);
        while (dTier.size() > 0) {
            String next = dTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; E: ");

        Set<String> eTier = this.showsInTier(Tier.E);
        while (eTier.size() > 0) {
            String next = eTier.removeAny();
            sb.append(next + ", ");
        }

        sb.append("; Unranked: ");

        Set<String> uRTier = this.showsInTier(Tier.Unranked);
        while (uRTier.size() > 0) {
            String next = uRTier.removeAny();
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