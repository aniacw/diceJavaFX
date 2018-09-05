package main.ProgrammableDice.Dice;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Integer> history = new ArrayList<>();

    void add(int n) {
        history.add(n);
    }

    public int last() {
        return history.get(history.size() - 1);
    }

    public int size(){
        return history.size();
    }

    public List<Integer> last(int n) {
        if (n > history.size())
            return new ArrayList<>(history);
        return history.subList(history.size() - n, history.size());
    }

    public void clear() {
        history.clear();
    }

    public List<Integer> getElements() {
        return history;
    }

    @Override
    public String toString() {
        return "History{" +
                "history=" + history +
                '}';
    }
}
