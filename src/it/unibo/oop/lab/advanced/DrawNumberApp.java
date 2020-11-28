package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    /*
     * private static final int MIN = 0; private static final int MAX = 100; private
     * static final int ATTEMPTS = 10;
     */
    private final DrawNumber model;
    private final DrawNumberView view;
    private final DrawNumberView view2;

    /**
     * 
     */
    public DrawNumberApp() {
        BufferedReader yml =new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/config.yml")));
        Map<String, Integer> mappaValori = new HashMap<>();
        try {
            StringTokenizer st;
            String s;
            while((s = yml.readLine()) != null ) {
               st = new StringTokenizer(s, ": ");
               String key=st.nextToken();
               int value = Integer.parseInt(st.nextToken());
               mappaValori.put(key, value);
            }
        } catch (IOException e) {
            System.out.println("Errore nel tokenizer, yml");
            e.printStackTrace();
        }
        
        this.model = new DrawNumberImpl(mappaValori.get("minimum"), mappaValori.get("maximum"), mappaValori.get("attempts"));
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view2 = new DrawNumberViewImplOnStdout();
        this.view2.setObserver(this);
        this.view2.start();
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
            this.view2.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
            this.view2.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
            view2.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
