import java.util.Set;

import components.standard.Standard;

/**
 * AnimeLibrary kernel component with primary methods.
 */
public interface AnimeLibraryKernel extends Standard<AnimeLibrary> {

    /**
     * Adds {@code show} to the watchlist of {@code this}.
     *
     * @param show
     *            the entry to be added
     * @requires show is not in this
     * @updates this
     * @ensures {@code this = <show> * #this}
     */
    void add(String show);

    /**
     * Removes and returns {@code show} from the watchlist of {@code this}.
     *
     * @param show
     *            the entry to be removed
     * @return the entry removed
     * @updates this
     * @requires show is in watchlist and not in currWatched or Watched and
     *           this.length(1) > 0
     * @ensures show is no longer in this
     *
     */
    String remove(String show);

    /**
     * Advances {@code show} to the next in the sequence of watchlist, currently
     * watching, and watched.
     *
     * @param show
     *            the show to be advanced
     * @updates this
     * @requires show is in this and show is not in watched
     * @ensures show is not in what is was previously contained in and is now
     *          contained in whatever was next in the sequence.
     */
    void advance(String show);

    /**
     * Reports whether {@code show} is in {@code this}.
     *
     * @param show
     *            the show to be checked
     * @return true iff show is in {@code this}
     * @ensures contains = (show is in this)
     */
    boolean contains(String show);

    /**
     * Reports the length of a certain section in the sequence.
     *
     * @param section
     *            int values 1, 2, or 3 meaning watchlist, currently watching,
     *            or watched respectively
     * @return number of shows in section
     * @requires section = 1 || 2 || 3
     * @ensures length = the number of shows in the specified section
     */
    int length(int section);

    /**
     * Changes tier of {@code show}.
     *
     * @param show
     *            the show the tier's being changed to
     * @param tier
     *            the tier to be assigned to {@code show}
     * @requires show is in watched and tier is a valid entry(SS, S, A, B, C, D
     *           E, Unranked)
     * @updates this
     * @ensures show is associated this tier
     */
    void changeTier(String show, String tier);

    /**
     * Returns the tier of {@code show}.
     *
     * @param show
     *            the show whose tier is being returned
     * @return tier of show
     * @requires show is in watched
     * @ensures tier = tier associated with show and this is unchanged
     */
    String tier(String show);

    /**
     * Returns the set of shows in {@code tier}.
     *
     * @param tier
     *            tier in watched
     * @return set of shows in tier
     * @ensures every element of showsInTier is in the tier in watched
     */
    Set<String> showsInTier(String tier);

    /**
     * Return the shows in Currently Watching.
     *
     * @return set of shows in currently watched
     * @ensures every element of showsInCurrWatching is in currWatching and
     *          this.length(2) = |showsInCurrWatching|
     */
    Set<String> showsInCurrWatch();

    /**
     * Returns the shows in watchlist.
     *
     * @return set of shows in watchlist
     * @ensures every element of showsInWatchList is in watchlist and
     *          this.length(1) = |showsInWatchlist|
     */
    Set<String> showsInWatchlist();
}
