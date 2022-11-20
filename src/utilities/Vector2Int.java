package utilities;

public class Vector2Int {
    public int x, y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Int(Vector2Int v) {
        this.x = v.x;
        this.y = v.y;
    }

    public static Vector2Int zero() {
        return new Vector2Int(0, 0);
    }

    public static Vector2Int up() {
        return new Vector2Int(0, 1);
    }

    public static Vector2Int down() {
        return new Vector2Int(0, -1);
    }

    public static Vector2Int right() {
        return new Vector2Int(1, 0);
    }

    public static Vector2Int left() {
        return new Vector2Int(-1, 0);
    }

    public Vector2Int clamped(int min, int max) {
        return new Vector2Int(
                Math.max(min, Math.min(max, this.x)),
                Math.max(min, Math.min(max, this.y)));
    }

    public Vector2Int clamped() {
        return new Vector2Int(
                Math.max(-1, Math.min(1, this.x)),
                Math.max(-1, Math.min(1, this.y)));
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Vector2Int v) {
        return this.x == v.x && this.y == v.y;
    }

    public Vector2Int valueOnly() {
        return new Vector2Int(this);
    }
}
