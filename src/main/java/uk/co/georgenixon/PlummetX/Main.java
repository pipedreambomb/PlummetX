package uk.co.georgenixon.PlummetX;

class Main {

    public static void main(String[] args) {
	    // write your code here
        View view = new ConsoleView(System.out, new Board(7, 7, 7));
        view.render();
    }
}
