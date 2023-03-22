package Othello;

import java.util.ArrayList;
import java.util.List;

public class Testing {

    private Testing parent;
    
    private final List<Testing> a;
    char c;
    String name;
    boolean n;

    public static void main(String[] args) {
        Testing t = new Testing(new ArrayList<>());
        System.out.println("char: " + t.c);
        System.out.println(t.name);
        System.out.println("boolean: " + t.n);
    }

    Testing(List<Testing> a, Testing parent){
        this.a = new ArrayList<>(a);
        this.parent = parent;
    }
    Testing (List<Testing> a){
        this(a, null);
        // Comparator.comparingInt()
    }

    public void moceInto(Testing testing){
        testing.parent.a.remove(testing);
        testing.parent = this;
        this.a.add(testing);
    }
}
