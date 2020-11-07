package model;

public interface Entity<T> {
    boolean sameEntityAs(T t);
}
