package br.com.sgt.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Felipe de Brito Lira
 */
public class Cronometro {

    private Timer timer;

    public void cronometro(JLabel labelCronometro) {
        timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            LocalTime dateTime = LocalTime.of(0, 0, 0);

            @Override
            public void run() {
                try {
                    if (dateTime.getSecond() <= 60) {
                        dateTime = dateTime.plusSeconds(1);
                    }
                    labelCronometro.setText(dateTime.format(DateTimeFormatter.ISO_TIME));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, 1000);
    }

    public void cancelar() {
        timer.cancel();
    }
}
