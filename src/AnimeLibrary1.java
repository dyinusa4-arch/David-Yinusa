import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * {@code AnimeLibrary} represented with two sets and a map of tiers and set of
 * the shows in that tier.
 *
 * @convention <pre>
 * this.watched.size = 8 and this.watched can only contain
 * the key values of the enums defined in the Tiers class
 * and if an element in this.watchlist, this.currWatch, or
 * is in the set of any tier in this.watched, then it can't
 * be in any of the other ones (a show can only be in one
 * place at a time) and the sequence a show can follow goes
 * from watchlist --> currWatch --> watchlist and cannot move
 * backwards and once a show is advanced to watched, it is
 * automatically assigned "Unranked" to which it can be changed
 * and a show can only be removed from watchlist.
 * </pre>
 * @correspondence <pre>
 * this = union of all (tiers, shows) in this.watched union
 * elements of this.currWatch union  elements of this.watchlist.
 * </pre>
 */
public class AnimeLibrary1 extends AnimeLibrary {
    /*
     * Private members
     */

    /**
     * Set containing shows in watchlist.
     */
    private Set<String> watchlist;

    /**
     * Set containing shows that are currently being watched.
     */
    private Set<String> currWatch;

    /**
     * Set containing shows that have been watched.
     */
    private Map<Tier, Set<String>> watched;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.watchlist = new Set1L<>();
        this.currWatch = new Set1L<>();

        this.watched = new Map1L<>();
        this.watched.add(Tier.SS, new Set1L<String>());
        this.watched.add(Tier.S, new Set1L<String>());
        this.watched.add(Tier.A, new Set1L<String>());
        this.watched.add(Tier.B, new Set1L<String>());
        this.watched.add(Tier.C, new Set1L<String>());
        this.watched.add(Tier.D, new Set1L<String>());
        this.watched.add(Tier.E, new Set1L<String>());
        this.watched.add(Tier.Unranked, new Set1L<String>());
    }

    /*
     * Constructor
     */

    /**
     * No-argument constructor.
     */
    public AnimeLibrary1() {
        this.createNewRep();
    }

    /*
     * Standard methods
     */

    @Override
    public final AnimeLibrary1 newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(AnimeLibrary source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        AnimeLibrary1 localSource = (AnimeLibrary1) source;
        this.watchlist = localSource.watchlist;
        this.currWatch = localSource.currWatch;
        this.watched = localSource.watched;
        localSource.createNewRep();
    }

    /*
     * Kernel methods
     */

    @Override
    public final void add(String show) {
        assert show != null : "Violation of: show is not null";
        assert !this.contains(show) : "Violation of: x is not in this";

        this.watchlist.add(show);
    }

    @Override
    public final String remove(String show) {
        assert show != null : "Violation of: x is not null";
        assert this.contains(show) : "Violation of: x is in this";

        this.watchlist.remove(show);

        return show;
    }

    @Override
    public final void advance(String show) {
        assert show != null : "Violation of: x is not null";
        assert this.contains(show) : "Violation of: x is in this";

        //After assert we know show is in this so if show is in watched, nothing
        //happens.
        if (this.watchlist.contains(show)) {
            this.watchlist.remove(show);
            this.currWatch.add(show);
        } else if (this.currWatch.contains(show)) {
            this.currWatch.remove(show);
            Map.Pair<Tier, Set<String>> pair = this.watched
                    .remove(Tier.Unranked);
            pair.value().add(show);
            this.watched.add(pair.key(), pair.value());
        }
    }

    @Override
    public final boolean contains(String show) {
        assert show != null : "Violation of: x is not null";

        boolean contains = this.watchlist.contains(show);

        if (!contains) {
            contains = this.currWatch.contains(show);
        }

        if (!contains) {
            for (Map.Pair<Tier, Set<String>> pair : this.watched) {
                if (pair.value().contains(show)) {
                    contains = true;
                }
            }
        }

        return contains;
    }

    @Override
    public final int length(Section section) {

        int length = 0;
        if (section == Section.Watchlist) {
            length = this.watchlist.size();
        } else if (section == Section.currWatch) {
            length = this.currWatch.size();
        } else {
            for (Map.Pair<Tier, Set<String>> pair : this.watched) {
                length += pair.value().size();
            }
        }

        return length;
    }

    @Override
    public final void changeTier(String show, Tier tier) {
        //ensures shows is in watched
        boolean inWatched = false;
        Tier oldTier;
        for (Map.Pair<Tier, Set<String>> pair : this.watched) {
            if (pair.value().contains(show)) {
                inWatched = true;
                oldTier = pair.key();
            }
        }
        assert show != null : "Violation of: x is not null";
        assert inWatched : "Violation of: show is not in watched";

        //removing from old tier
        Map.Pair<Tier, Set<String>> pair = this.watched.remove(oldTier);
        pair.value().remove(show);
        this.watched.add(pair.key(), pair.value());

        //adding to new tier
        Map.Pair<Tier, Set<String>> newPair = this.watched.remove(tier);
        pair.value().add(show);
        this.watched.add(pair.key(), pair.value());

    }

    @Override
    public final Tier tier(String show) {
        //ensures shows is in watched
        boolean inWatched = false;
        Tier oldTier;
        for (Map.Pair<Tier, Set<String>> pair : this.watched) {
            if (pair.value().contains(show)) {
                inWatched = true;
                oldTier = pair.key();
            }
        }
        assert show != null : "Violation of: x is not null";
        assert inWatched : "Violation of: show is not in watched";

        return oldTier;
    }

    @Override
    public final Set<String> showsInTier(Tier tier) {
        Map.Pair<Tier, Set<String>> pair = this.watched.remove(tier);
        //avoid aliasing
        Set<String> copy = pair.value().newInstance();
        Set<String> temp = copy.newInstance();

        while (pair.value().size() > 0) {
            String next = pair.value().removeAny();
            copy.add(next);
            temp.add(next);
        }

        pair.value().transferFrom(temp);

        this.watched.add(pair.key(), pair.value());

        return copy;

    }

}
