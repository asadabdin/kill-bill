package org.asad.game.entity;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {

    private static final long serialVersionUID = -3148749240710576495L;
    private int x;
    private int y;

    private Location() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static final class Builder {

        private int x;
        private int y =0;


        public Builder(int x) {
            this.x = x;
        }

        public Builder withY(int y) {
            this.y = y;
            return this;
        }

        public Location build() {
            Location location = new Location();
            location.x = this.x;
            location.y = this.y;
            return location;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
