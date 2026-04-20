/*
* Since duplicate entries are not allowed, the watchlist and currently watching
* sections will be represented using sets and watched will be use maps with show
* being key and rank being value. Some additional info for this is that add only
* lets you add to the watchlist and nothing else and you can only remove from the
* watchlist (you can't necessarily unwatch a show). Also, whenever you advance
* to "watched" it automatically sets it to unranked.
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class animelibrary {
    private Set<String> watchlist;

    private Set<String> currWatching;

    private Map<String, String> watched;

    //set containing all valid tiers
    private Set<String> tiers = new HashSet<>();

    private void createNewRep() {
        this.watchlist = new HashSet<>();
        this.currWatching = new HashSet<>();
        this.watched = new HashMap<String, String>();
    }

    public animelibrary() {
        this.createNewRep();
    }

    /*
     * kernel method implementation
     */

    public final void add(String show) {
        //assert show is not contained and is not null
        this.watchlist.add(show);
    }

    public final String remove(String show) {
        //assert is show is contained and is not null
        this.watchlist.remove(show);

        return show;
    }

    public final void advance(String show) {
        //assert show is contained and not null

        if (this.watchlist.contains(show)) {
            this.watchlist.remove(show);
            this.currWatching.add(show);
        } else if (this.currWatching.contains(show)) {
            this.currWatching.remove(show);
            this.watched.put(show, "Unranked");
        }
    }

    public final boolean contains(String show) {
        return this.watchlist.contains(show) || this.currWatching.contains(show)
                || this.watched.containsKey(show);
    }

    public final int length(int section) {
        //asert section == 1, 2, or 3
        int length = 0;

        if (section == 1) {
            length = this.watchlist.size();
        } else if (section == 2) {
            length = this.currWatching.size();
        } else {
            length = this.watched.size();
        }

        return length;
    }

    public final void changeTier(String show, String tier) {
        //assert is in watched
        this.watched.remove(show);
        this.watched.put(show, tier);
    }

    public final String tier(String show) {
        //assert show is in watched
        return this.watched.get(show);
    }

    public final int section(String show) {
        int section = 0;

        if (this.watchlist.contains(show)) {
            section = 1;
        } else if (this.currWatching.contains(show)) {
            section = 2;
        } else if (this.watched.containsKey(show)) {
            section = 3;
        }

        return section;
    }

    /*
     * Secondary method implementation
     */

    public final int lengthOfAll() {
        return this.length(1) + this.length(2) + this.length(3);
    }

    public final void moveToWatched(String show) {
        //assert show is in watchlist
        this.advance(show);
        this.advance(show);
    }
}
