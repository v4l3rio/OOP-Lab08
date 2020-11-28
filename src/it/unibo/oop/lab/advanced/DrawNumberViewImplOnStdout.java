package it.unibo.oop.lab.advanced;

public class DrawNumberViewImplOnStdout implements DrawNumberView{
    private static final String NEW_GAME = ": a new game starts!";
    private DrawNumberViewObserver observer;

    @Override
    public void setObserver(DrawNumberViewObserver observer) {
      this.observer=observer;
        
    }

    @Override
    public void start() {
        System.out.println("--View on stdout started!--");
        
    }

    @Override
    public void numberIncorrect() {
        System.out.println("Numero incorretto!");
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            System.out.println(res.getDescription());
            return;
        case YOU_WON:
            System.out.println(res.getDescription() + NEW_GAME);
            break;
        }
        
    }

    @Override
    public void limitsReached() {
        System.out.println("You lost"+NEW_GAME);
        
    }

    @Override
    public void displayError(String message) {
       System.out.println(message);
    }

}
