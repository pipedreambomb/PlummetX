package uk.co.georgenixon;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        View view = new ConsoleView(System.out, new Board());
        view.render();
    }
}
